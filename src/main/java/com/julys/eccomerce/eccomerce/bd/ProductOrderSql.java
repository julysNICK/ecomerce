package com.julys.eccomerce.eccomerce.bd;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface ProductOrderSql extends
        org.springframework.data.jpa.repository.JpaRepository<com.julys.eccomerce.eccomerce.entity.ProductOrder, Long> {

    List<com.julys.eccomerce.eccomerce.entity.ProductOrder> findByOrder_Id(Long id);

    List<com.julys.eccomerce.eccomerce.entity.ProductOrder> findByProduct_Id(Long id);
}