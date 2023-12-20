package com.julys.eccomerce.eccomerce.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.julys.eccomerce.eccomerce.request.product.RequestProductOrder;
import com.julys.eccomerce.eccomerce.service.productOrder.ProductOrderService;

@RestController
@RequestMapping("/api/productOrder")
public class ProductOrderController {
  @Autowired
  private ProductOrderService productOrderService;

  @GetMapping("/hello")
  public String hello() {
    return "Hello World Product Order";
  }

  @PostMapping("/create")
  public ResponseEntity<?> createProductOrder(@RequestBody RequestProductOrder requestProductOrder) {

    return productOrderService.createProductOrder(requestProductOrder);

  }

  @GetMapping("/{idOrder}")
  public ResponseEntity<?> findById(@PathVariable Long idOrder) {

    return productOrderService.findById(idOrder);

  }

  @GetMapping("/")
  public ResponseEntity<?> findAll() {

    return productOrderService.findAll();

  }

  @DeleteMapping("/{idOrder}")
  public ResponseEntity<?> deleteById(@PathVariable Long idOrder) {

    return productOrderService.deleteById(idOrder);

  }

  @GetMapping("/order/{idOrder}")
  public ResponseEntity<?> findByOrder(@PathVariable Long idOrder) {

    return productOrderService.findByOrder(idOrder);

  }

  @GetMapping("/product/{idProduct}")
  public ResponseEntity<?> findByProduct(@PathVariable Long idProduct) {

    return productOrderService.findByProduct(idProduct);

  }

  @PatchMapping("/update/{idOrder}")
  public ResponseEntity<?> updateProductOrder(@PathVariable Long idOrder,
      @RequestBody RequestProductOrder requestProductOrder) {

    return productOrderService.updateProductOrder(idOrder, requestProductOrder);

  }
}