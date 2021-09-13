package com.example.demo.service;

import com.example.demo.domain.PaymentEntity;
import com.example.demo.domain.ShopOrderEntity;
import com.example.demo.domain.dto.OrderInfoDto;
import com.example.demo.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public PaymentEntity savePayment(PaymentEntity paymentEntity){
        return paymentRepository.save(paymentEntity);
    }

    public PaymentEntity createPayment(ShopOrderEntity shopOrderEntity, OrderInfoDto orderInfoDto) {
        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setBruttoAmount(orderInfoDto.getBrutto());
        paymentEntity.setNettoAmount(orderInfoDto.getNetto());
        paymentEntity.setShopOrderEntity(shopOrderEntity);
        paymentEntity.setHash(UUID.randomUUID().toString());

        return savePayment(paymentEntity);
    }
}
