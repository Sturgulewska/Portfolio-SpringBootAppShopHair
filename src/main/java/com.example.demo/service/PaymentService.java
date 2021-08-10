package com.example.demo.service;

import com.example.demo.domain.Payment;
import com.example.demo.domain.ShopOrder;
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

    public Payment savePayment(Payment payment){
        return paymentRepository.save(payment);
    }

    public Payment createPayment(ShopOrder shopOrder, OrderInfoDto orderInfoDto) {
        Payment payment = new Payment();
        payment.setBruttoAmount(orderInfoDto.getBrutto());
        payment.setNettoAmount(orderInfoDto.getNetto());
        payment.setShopOrder(shopOrder);
        payment.setHash(UUID.randomUUID().toString());

        return savePayment(payment);
    }
}
