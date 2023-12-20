package com.julys.eccomerce.eccomerce.service.productOrder;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.julys.eccomerce.eccomerce.entity.ProductOrder;
import com.julys.eccomerce.eccomerce.request.product.RequestProductOrder;

public interface ProductOrderService {
  public ResponseEntity<?> createProductOrder(RequestProductOrder requestProductOrder);

  public ResponseEntity<?> findById(Long idOrder);

  public ResponseEntity<?> findAll();

  public ResponseEntity<?> deleteById(Long idOrder);

  public ResponseEntity<?> findByOrder(Long idOrder);

  public ResponseEntity<?> findByProduct(Long idProduct);

  public ResponseEntity<?> updateProductOrder(Long idOrder, RequestProductOrder requestProductOrder);
}