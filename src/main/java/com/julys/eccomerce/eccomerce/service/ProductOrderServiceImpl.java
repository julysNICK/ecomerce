package com.julys.eccomerce.eccomerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.julys.eccomerce.eccomerce.dao.ProductOrderDAO;
import com.julys.eccomerce.eccomerce.entity.ProductOrder;

@Component
public class ProductOrderServiceImpl implements ProductOrderService {

  @Autowired
  private ProductOrderDAO productOrderDAO;

  @Override
  public ProductOrder createProductOrder(ProductOrder productOrder) {
    try {

      return productOrderDAO.createProductOrder(productOrder);
    } catch (Exception e) {

      throw new RuntimeException("Error creating product order: " + e.getMessage());
    }
  }

  @Override
  public ProductOrder findById(Long idOrder) {

    return productOrderDAO.findById(idOrder);

  }

  @Override
  public List<ProductOrder> findAll() {
    try {
      return productOrderDAO.findAll();
    } catch (Exception e) {

      return null;
    }
  }

  @Override
  public String deleteById(Long idOrder) {
    try {
      productOrderDAO.deleteProductOrder(idOrder);
      return "Product order deleted successfully";
    } catch (Exception e) {

      return "Error deleting product order: " + e.getMessage();
    }
  }

  @Override
  public List<ProductOrder> findByOrder(Long idOrder) {
    try {
      return productOrderDAO.findByOrder(idOrder);
    } catch (Exception e) {

      return null;
    }
  }

  @Override
  public List<ProductOrder> findByProduct(Long idProduct) {
    try {
      return productOrderDAO.findByProduct(idProduct);
    } catch (Exception e) {

      return null;
    }
  }

  @Override
  public ProductOrder updateProductOrder(ProductOrder productOrder) {
    try {
      return productOrderDAO.updateProductOrder(productOrder);
    } catch (Exception e) {

      return null;
    }
  }
}