package com.julys.eccomerce.eccomerce.service.productOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.julys.eccomerce.eccomerce.dao.order.OrderDAO;
import com.julys.eccomerce.eccomerce.dao.product.ProductDAO;
import com.julys.eccomerce.eccomerce.dao.productOrder.ProductOrderDAO;
import com.julys.eccomerce.eccomerce.entity.Order;
import com.julys.eccomerce.eccomerce.entity.Product;
import com.julys.eccomerce.eccomerce.entity.ProductOrder;
import com.julys.eccomerce.eccomerce.request.product.RequestProductOrder;

@Component
public class ProductOrderServiceImpl implements ProductOrderService {

  @Autowired
  private ProductOrderDAO productOrderDAO;

  @Autowired
  private ProductDAO productService;

  @Autowired
  private OrderDAO orderService;

  @Override
  public ResponseEntity<?> createProductOrder(RequestProductOrder requestProductOrder) {

    try {

      if (requestProductOrder.getIdOrder() == null) {
        return ResponseEntity.badRequest().body("idOrder is null");
      }

      if (requestProductOrder.getIdProduct() == null) {
        return ResponseEntity.badRequest().body("idProduct is null");
      }

      if (requestProductOrder.getQuantity() == 0) {
        return ResponseEntity.badRequest().body("Quantity is 0");
      }

      Product productFind = productService.findById(requestProductOrder.getIdProduct());

      if (productFind == null) {
        return ResponseEntity.notFound().build();
      }

      Order orderFind = orderService.findById(requestProductOrder.getIdOrder());

      if (orderFind == null) {
        return ResponseEntity.notFound().build();
      }

      ProductOrder productOrder = new ProductOrder();

      productOrder.setOrder(orderFind);

      productOrder.setProduct(productFind);

      productOrder.setQuantity(requestProductOrder.getQuantity());

      return ResponseEntity.status(201).body(productOrderDAO.createProductOrder(productOrder));
    } catch (DataIntegrityViolationException ex) {

      return ResponseEntity.badRequest().body("Error creating Product Order");

    } catch (NullPointerException ex) {

      return ResponseEntity.badRequest().body("Error creating Product Order");

    }

    catch (IllegalArgumentException ex) {

      return ResponseEntity.badRequest().body("Error creating Product Order");

    } catch (Exception e) {

      return ResponseEntity.badRequest().body("Error creating Product Order");
    }
  }

  @Override
  public ResponseEntity<?> findById(Long idOrder) {

    try {

      ProductOrder productOrder = productOrderDAO.findById(idOrder);

      if (productOrder == null) {
        return ResponseEntity.notFound().build();
      }

      return ResponseEntity.ok(productOrder);
    } catch (Exception e) {

      return ResponseEntity.badRequest().body("Error finding Product Order");

    }
  }

  @Override
  public ResponseEntity<?> findAll() {

    try {
      return ResponseEntity.ok(productOrderDAO.findAll());
    } catch (Exception e) {

      return ResponseEntity.badRequest().body("Error finding Product Order");
    }
  }

  @Override
  public ResponseEntity<?> deleteById(Long idOrder) {
    try {
      productOrderDAO.deleteProductOrder(idOrder);
      return ResponseEntity.ok("Product Order deleted");
    } catch (Exception e) {

      return ResponseEntity.badRequest().body("Error deleting Product Order");
    }
  }

  @Override
  public ResponseEntity<?> findByOrder(Long idOrder) {
    try {
      return ResponseEntity.ok(productOrderDAO.findByOrder(idOrder));
    } catch (Exception e) {

      return ResponseEntity.badRequest().body("Error finding Product Order");
    }
  }

  @Override
  public ResponseEntity<?> findByProduct(Long idProduct) {
    try {
      return ResponseEntity.ok(productOrderDAO.findByProduct(idProduct));
    } catch (Exception e) {

      return ResponseEntity.badRequest().body("Error finding Product Order");
    }
  }

  @Override
  public ResponseEntity<?> updateProductOrder(Long idOrder, RequestProductOrder requestProductOrder) {

    try {
      ProductOrder productOrder = productOrderDAO.findById(idOrder);

      if (productOrder == null) {
        return ResponseEntity.notFound().build();
      }

      Product productFind = productService.findById(requestProductOrder.getIdProduct());

      if (productFind == null) {
        return ResponseEntity.notFound().build();
      }

      Order orderFind = orderService.findById(requestProductOrder.getIdOrder());

      if (orderFind == null) {
        return ResponseEntity.notFound().build();
      }

      productOrder.setOrder(orderFind);

      productOrder.setProduct(productFind);

      productOrder.setQuantity(requestProductOrder.getQuantity());

      return ResponseEntity.status(201).body(productOrderDAO.updateProductOrder(productOrder));
    } catch (Exception e) {

      return ResponseEntity.badRequest().build();
    }
  }
}