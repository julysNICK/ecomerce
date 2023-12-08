package com.julys.eccomerce.eccomerce.dao.productOrder;

import java.util.List;
import com.julys.eccomerce.eccomerce.entity.ProductOrder;

public interface ProductOrderDAO {

  ProductOrder createProductOrder(ProductOrder productOrder);

  ProductOrder findById(Long idOrder);

  List<ProductOrder> findAll();

  String deleteProductOrder(Long idOrder);

  List<ProductOrder> findByOrder(Long idOrder);

  List<ProductOrder> findByProduct(Long idProduct);

  ProductOrder updateProductOrder(ProductOrder productOrder);
}