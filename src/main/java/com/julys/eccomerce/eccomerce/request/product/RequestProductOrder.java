package com.julys.eccomerce.eccomerce.request.product;

@lombok.Data
public class RequestProductOrder {
  private Long idOrder;

  private Long idProduct;
  private int quantity;
}