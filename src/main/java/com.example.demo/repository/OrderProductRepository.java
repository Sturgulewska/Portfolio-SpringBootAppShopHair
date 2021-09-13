package com.example.demo.repository;

import com.example.demo.domain.ShopOrderEntity;
import com.example.demo.domain.OrderProductEntity;
import com.example.demo.domain.ProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface OrderProductRepository extends CrudRepository<OrderProductEntity, Long> {
     void deleteByShopOrderAndProduct(ShopOrderEntity shopOrderEntity, ProductEntity product);
}
