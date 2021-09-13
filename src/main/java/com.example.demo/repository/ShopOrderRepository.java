package com.example.demo.repository;

import com.example.demo.domain.ShopOrderEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ShopOrderRepository extends CrudRepository<ShopOrderEntity, Long>
{
    Optional<ShopOrderEntity> findByHash(String hash);
}
