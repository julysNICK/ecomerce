package com.julys.eccomerce.eccomerce.dao.productOrder;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import com.julys.eccomerce.eccomerce.bd.ProductOrderSql;
import com.julys.eccomerce.eccomerce.entity.ProductOrder;
import com.julys.eccomerce.eccomerce.util.Util;

@Component
public class ProductOrderDAOImpl implements ProductOrderDAO {
  @Autowired
  private ProductOrderSql productOrderSql;

  @Override
  public ProductOrder createProductOrder(ProductOrder productOrder) {
    try {

      return productOrderSql.save(productOrder);
    } catch (DataIntegrityViolationException e) {

      throw new DataIntegrityViolationException("Error creating product order: " + e.getMessage());

    } catch (NullPointerException e) {

      throw new NullPointerException("Error creating product order: " + e.getMessage());

    } catch (Exception e) {

      throw new RuntimeException("Error creating product order: " + e.getMessage());
    }
  }

  @Override
  public ProductOrder findById(Long idOrder) {
    try {
      return productOrderSql.findById(idOrder).orElse(null);
    } catch (DataIntegrityViolationException e) {
      throw new DataIntegrityViolationException("Error finding product order: " + e.getMessage());
    } catch (EmptyResultDataAccessException e) {
      throw new EmptyResultDataAccessException("Error finding product order: " + e.getMessage(), 1);

    } catch (Exception e) {

      throw new RuntimeException("Error finding product order: " + e.getMessage());
    }
  }

  @Override
  public List<ProductOrder> findAll() {
    try {
      return productOrderSql.findAll();
    } catch (DataIntegrityViolationException e) {
      throw new DataIntegrityViolationException("Error finding product order: " + e.getMessage());
    } catch (EmptyResultDataAccessException e) {
      throw new EmptyResultDataAccessException("Error finding product order: " + e.getMessage(), 1);

    } catch (Exception e) {

      throw new RuntimeException("Error finding product order: " + e.getMessage());
    }
  }

  @Override
  public String deleteProductOrder(Long idOrder) {
    try {
      productOrderSql.deleteById(idOrder);
      return "Product order deleted successfully";
    } catch (Exception e) {

      throw new RuntimeException("Error deleting product order: " + e.getMessage());
    }
  }

  @Override
  public List<ProductOrder> findByOrder(Long idOrder) {
    try {
      return productOrderSql.findByOrder_Id(idOrder);
    } catch (DataIntegrityViolationException e) {
      throw new DataIntegrityViolationException("Error finding product order: " + e.getMessage());
    } catch (EmptyResultDataAccessException e) {
      throw new EmptyResultDataAccessException("Error finding product order: " + e.getMessage(), 1);
    } catch (Exception e) {

      throw new RuntimeException("Error finding product order: " + e.getMessage());
    }
  }

  @Override
  public List<ProductOrder> findByProduct(Long idProduct) {
    try {
      return productOrderSql.findByProduct_Id(idProduct);
    } catch (DataIntegrityViolationException e) {
      throw new DataIntegrityViolationException("Error finding product order: " + e.getMessage());

    } catch (Exception e) {

      throw new RuntimeException("Error finding product order: " + e.getMessage());

    }
  }

  @Override
  public ProductOrder updateProductOrder(ProductOrder productOrder) {
    try {
      ProductOrder productOrderToUpdate = productOrderSql.findById(productOrder.getId()).orElse(null);

      if (productOrderToUpdate == null) {
        throw new RuntimeException("Product order not found");
      }

      Util.copyNonNullProperties(productOrder, productOrderToUpdate);

      return productOrderSql.save(productOrderToUpdate);

    } catch (DataIntegrityViolationException e) {

      throw new DataIntegrityViolationException("Error updating product order: " + e.getMessage());

    } catch (Exception e) {

      throw new RuntimeException("Error updating product order: " + e.getMessage());
    }
  }

}