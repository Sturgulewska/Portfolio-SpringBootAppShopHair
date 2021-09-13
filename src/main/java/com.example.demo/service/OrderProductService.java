package com.example.demo.service;


import com.example.demo.domain.ShopOrderEntity;
import com.example.demo.domain.OrderProductEntity;
import com.example.demo.domain.ProductEntity;
import com.example.demo.repository.OrderProductRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderProductService {
    private final OrderProductRepository orderProductRepository;

    public OrderProductService(OrderProductRepository orderProductRepository) {
        this.orderProductRepository = orderProductRepository;
    }

    public OrderProductEntity createOrderProduct(ProductEntity product, ShopOrderEntity shopOrderEntity){
        OrderProductEntity orderProductEntity = new OrderProductEntity();
        orderProductEntity.setProduct(product);
        orderProductEntity.setShopOrderEntity(shopOrderEntity);
        return saveOrder(orderProductEntity);
    }

    public void deleteProductFromOrder(ShopOrderEntity shopOrderEntity, ProductEntity product){
        orderProductRepository.deleteByShopOrderAndProduct(shopOrderEntity, product);
    }

    private OrderProductEntity saveOrder(OrderProductEntity orderProductEntity) {
        return orderProductRepository.save(orderProductEntity);
    }
}
