package com.julys.eccomerce.eccomerce.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

import com.julys.eccomerce.eccomerce.bd.OrderSql;
import com.julys.eccomerce.eccomerce.entity.Order;
import com.julys.eccomerce.eccomerce.entity.Product;
import com.julys.eccomerce.eccomerce.entity.ProductOrder;
import com.julys.eccomerce.eccomerce.request.RequestProductOrder;
import com.julys.eccomerce.eccomerce.service.ProductOrderService;
import com.julys.eccomerce.eccomerce.service.ProductService;

@RestController
@RequestMapping("/api/productOrder")
public class ProductOrderController {
  @Autowired
  private ProductOrderService productOrderService;

  @Autowired
  private ProductService productService;

  @Autowired
  private OrderSql orderService;

  @GetMapping("/hello")
  public String hello() {
    return "Hello World Product Order";
  }

  @PostMapping("/create")
  public String createProductOrder(@RequestBody RequestProductOrder requestProductOrder) {

    try {
      System.out.println("idOrder");

      if (requestProductOrder.getIdOrder() == null) {
        return "idOrder is null";
      }

      if (requestProductOrder.getIdProduct() == null) {
        return "idProduct is null";
      }

      if (requestProductOrder.getQuantity() == 0) {
        return "Quantity is null";
      }

      Product productFind = productService.findById(requestProductOrder.getIdProduct());

      if (productFind == null) {
        return "Product not found";
      }

      Order orderFind = orderService.findById(requestProductOrder.getIdOrder()).orElse(null);

      if (orderFind == null) {
        return "Order not found";
      }

      ProductOrder productOrder = new ProductOrder();

      productOrder.setOrder(orderFind);

      productOrder.setProduct(productFind);

      productOrder.setQuantity(requestProductOrder.getQuantity());

      return productOrderService.createProductOrder(productOrder);
    } catch (DataIntegrityViolationException ex) {

      return "Error creating Product Order";

    } catch (NullPointerException ex) {

      return "Error creating Product Order";

    }

    catch (IllegalArgumentException ex) {

      return "Error creating Product Order";

    } catch (Exception e) {

      return "Error creating Product Order";
    }
  }

  @GetMapping("/{idOrder}")
  public ResponseEntity<?> findById(@PathVariable Long idOrder) {

    try {

      ProductOrder productOrder = productOrderService.findById(idOrder);

      if (productOrder == null) {
        return ResponseEntity.notFound().build();
      }

      return ResponseEntity.ok(productOrderService.findById(idOrder));
    } catch (Exception e) {
      System.out.println(e.getMessage());

      return ResponseEntity.badRequest().body("Error finding Product Order");

    }
  }

  @GetMapping("/")
  public List<ProductOrder> findAll() {
    try {
      return productOrderService.findAll();
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return null;
    }
  }

  @DeleteMapping("/{idOrder}")
  public String deleteById(@PathVariable Long idOrder) {
    try {
      return productOrderService.deleteById(idOrder);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return "Error deleting Product Order";
    }
  }

  @GetMapping("/order/{idOrder}")
  public List<ProductOrder> findByOrder(@PathVariable Long idOrder) {
    try {
      return productOrderService.findByOrder(idOrder);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return null;
    }
  }

  @GetMapping("/product/{idProduct}")
  public List<ProductOrder> findByProduct(@PathVariable Long idProduct) {
    try {
      return productOrderService.findByProduct(idProduct);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return null;
    }
  }

  @PatchMapping("/update/{idOrder}")
  public ResponseEntity<?> updateProductOrder(@PathVariable Long idOrder,
      @RequestBody RequestProductOrder requestProductOrder) {
    try {
      ProductOrder productOrder = productOrderService.findById(idOrder);

      if (productOrder == null) {
        return ResponseEntity.notFound().build();
      }

      Product productFind = productService.findById(requestProductOrder.getIdProduct());

      if (productFind == null) {
        return ResponseEntity.notFound().build();
      }

      Order orderFind = orderService.findById(requestProductOrder.getIdOrder()).orElse(null);

      if (orderFind == null) {
        return ResponseEntity.notFound().build();
      }

      productOrder.setOrder(orderFind);

      productOrder.setProduct(productFind);

      productOrder.setQuantity(requestProductOrder.getQuantity());

      return ResponseEntity.status(201).body(productOrderService.updateProductOrder(productOrder));
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return ResponseEntity.badRequest().build();
    }
  }
}