package com.example.demo.service;


import com.example.demo.domain.ShopOrder;
import com.example.demo.domain.OrderProduct;
import com.example.demo.domain.Product;
import com.example.demo.repository.OrderProductRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderProductService {
    private final OrderProductRepository orderProductRepository;

    public OrderProductService(OrderProductRepository orderProductRepository) {
        this.orderProductRepository = orderProductRepository;
    }

    public OrderProduct createOrderProduct(Product product, ShopOrder shopOrder){
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setProduct(product);
        orderProduct.setShopOrder(shopOrder);
        return orderProductRepository.save(orderProduct);
    }

    public void deleteProductFromOrder(ShopOrder shopOrder, Product product){
        orderProductRepository.deleteByShopOrderAndProduct(shopOrder, product);
    }
}
