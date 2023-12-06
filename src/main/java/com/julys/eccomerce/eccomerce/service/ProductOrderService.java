package com.julys.eccomerce.eccomerce.service;

import java.util.List;

import com.julys.eccomerce.eccomerce.entity.ProductOrder;

public interface ProductOrderService {
  public String createProductOrder(ProductOrder productOrder);

  public ProductOrder findById(Long idOrder);

  public List<ProductOrder> findAll();

  public String deleteById(Long idOrder);

  public List<ProductOrder> findByOrder(Long idOrder);

  public List<ProductOrder> findByProduct(Long idProduct);

  public ProductOrder updateProductOrder(ProductOrder productOrder);
}