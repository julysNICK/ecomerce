package com.julys.eccomerce.eccomerce.request;

@lombok.Data
public class RequestProductOrder {
  private Long idOrder;
  private Long idProduct;
  private int quantity;
}