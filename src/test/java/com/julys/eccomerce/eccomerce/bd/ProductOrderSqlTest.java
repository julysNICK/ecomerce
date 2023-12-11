package com.julys.eccomerce.eccomerce.bd;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class ProductOrderSqlTest {
  @MockBean
  private ProductOrderSql productOrderSql;

  @MockBean
  private OrderSql orderSql;

  @MockBean
  private ProductSql productSql;

  @Test
  public void testCreateProductOrder() {

    com.julys.eccomerce.eccomerce.entity.ProductOrder productOrder = new com.julys.eccomerce.eccomerce.entity.ProductOrder();

    productOrder.setOrder(null);
    productOrder.setProduct(null);
    productOrder.setQuantity(10);

    org.mockito.Mockito.when(productOrderSql.save(productOrder)).thenReturn(productOrder);

    assert (productOrderSql.save(productOrder).equals(productOrder));

  }

  @Test
  public void testProductOrderGetById() {

    com.julys.eccomerce.eccomerce.entity.ProductOrder productOrder = new com.julys.eccomerce.eccomerce.entity.ProductOrder();

    productOrder.setOrder(null);
    productOrder.setProduct(null);
    productOrder.setQuantity(10);

    org.mockito.Mockito.when(productOrderSql.findById(1L)).thenReturn(java.util.Optional.of(productOrder));

    assert (productOrderSql.findById(1L).get().equals(productOrder));

  }

  @Test
  public void testProductOrderDelete() {
    Mockito.doNothing().when(productOrderSql).deleteById(1L);
    productOrderSql.deleteById(1L);

    Mockito.verify(productOrderSql, Mockito.times(1)).deleteById(1L);

  }

  @Test
  public void testProductOrderFindByOrder_Id() {

    com.julys.eccomerce.eccomerce.entity.ProductOrder productOrder = new com.julys.eccomerce.eccomerce.entity.ProductOrder();

    productOrder.setOrder(null);
    productOrder.setProduct(null);
    productOrder.setQuantity(10);

    org.mockito.Mockito.when(productOrderSql.findByOrder_Id(1L)).thenReturn(java.util.Arrays.asList(productOrder));

    assert (productOrderSql.findByOrder_Id(1L).equals(java.util.Arrays.asList(productOrder)));

  }

  @Test
  public void testProductOrderFindByProduct_Id() {
    com.julys.eccomerce.eccomerce.entity.ProductOrder productOrder = new com.julys.eccomerce.eccomerce.entity.ProductOrder();

    productOrder.setOrder(null);
    productOrder.setProduct(null);
    productOrder.setQuantity(10);

    org.mockito.Mockito.when(productOrderSql.findByProduct_Id(1L)).thenReturn(java.util.Arrays.asList(productOrder));

    assert (productOrderSql.findByProduct_Id(1L).equals(java.util.Arrays.asList(productOrder)));
  }

}