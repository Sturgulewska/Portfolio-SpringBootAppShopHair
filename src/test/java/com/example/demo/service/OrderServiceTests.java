package com.example.demo.service;

import com.example.demo.domain.Category;
import com.example.demo.domain.Client;
import com.example.demo.domain.OrderProduct;
import com.example.demo.domain.ShopOrder;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.OrderProductRepository;
import com.example.demo.repository.ShopOrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.verification.VerificationMode;

import javax.validation.constraints.Email;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class OrderServiceTests {

    private ShopOrderRepository repository = Mockito.mock(ShopOrderRepository.class);
    private EmailService service = Mockito.mock(EmailService.class);

    @Test
    void shouldFindServiceId() {
        //Given

        Client client = new Client();
        client.setId(1L);
        client.setName("Amelia Meczko");
        client.setCcCVV("970");
        client.setCity("Poland");
        client.setCcExpiration("09/2025");
        client.setEmail("meczko@wp.pl");
        client.setCcNumber("4552159332265436");
        client.setState("Podlaskie");
        client.setStreet("Złota 4");
        client.setZip("16-400");


        OrderService orderService = new OrderService(repository, service);
        ShopOrder order = new ShopOrder();
        order.setId(1L);
        order.setConfirmed(true);
        order.setDate(LocalDateTime.now());
        order.setHash("c4ca4238a0b923820dcc509a6f75849b");
        order.setClient(client);


        //When and Then
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(order));


    }}

