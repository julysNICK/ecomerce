package com.julys.eccomerce.eccomerce.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return "Error creating Product Order";
    }
  }

  @GetMapping("/{idOrder}")
  public ProductOrder findById(@PathVariable Long idOrder) {
    try {
      return productOrderService.findById(idOrder);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return null;
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
}