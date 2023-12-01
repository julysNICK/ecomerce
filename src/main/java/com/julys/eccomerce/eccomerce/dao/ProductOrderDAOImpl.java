package com.julys.eccomerce.eccomerce.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.julys.eccomerce.eccomerce.bd.ProductOrderSql;
import com.julys.eccomerce.eccomerce.entity.ProductOrder;

@Component
public class ProductOrderDAOImpl implements ProductOrderDAO {
  @Autowired
  private ProductOrderSql productOrderSql;

  @Override
  public String createProductOrder(ProductOrder productOrder) {
    try {
      productOrderSql.save(productOrder);
      return "Product order created successfully";
    } catch (Exception e) {

      System.out.println(e.getMessage());
      return "Error creating product order: " + e.getMessage();
    }
  }

  @Override
  public ProductOrder findById(Long idOrder) {
    try {
      return productOrderSql.findById(idOrder).orElse(null);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return null;
    }
  }

  @Override
  public List<ProductOrder> findAll() {
    try {
      return productOrderSql.findAll();
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return null;
    }
  }

  @Override
  public String deleteProductOrder(Long idOrder) {
    try {
      productOrderSql.deleteById(idOrder);
      return "Product order deleted successfully";
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return "Error deleting product order: " + e.getMessage();
    }
  }

  @Override
  public List<ProductOrder> findByOrder(Long idOrder) {
    try {
      return productOrderSql.findByOrder_Id(idOrder);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return null;
    }
  }

  @Override
  public List<ProductOrder> findByProduct(Long idProduct) {
    try {
      return productOrderSql.findByProduct_Id(idProduct);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return null;
    }
  }

}