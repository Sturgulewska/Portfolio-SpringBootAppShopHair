package com.example.demo.repository;

import com.example.demo.domain.ShopOrder;
import com.example.demo.domain.OrderProduct;
import com.example.demo.domain.Product;
import org.springframework.data.repository.CrudRepository;

public interface OrderProductRepository extends CrudRepository<OrderProduct, Long> {
     void deleteByShopOrderAndProduct(ShopOrder shopOrder, Product product);
}
