package com.example.demo.repository;

import com.example.demo.domain.ShopOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ShopOrderRepository extends CrudRepository<ShopOrder, Long>
{
    Optional<ShopOrder> findByHash(String hash);
}
