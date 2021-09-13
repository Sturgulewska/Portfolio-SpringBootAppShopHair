package com.example.demo.controller;


import com.example.demo.domain.ClientEntity;
import com.example.demo.domain.ShopOrderEntity;
import com.example.demo.domain.ProductEntity;
import com.example.demo.domain.dto.OrderInfoDto;
import com.example.demo.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.Optional;

@RestController
@RequestMapping(name = "order")
@CrossOrigin
public class OrderController {
    private final OrderService orderService;
    private final ClientService clientService;
    private final ProductService productService;
    private final OrderProductService orderProductService;
    private final EmailService emailService;


    public OrderController(OrderService orderService, ClientService clientService, ProductService productService, OrderProductService orderProductService, EmailService emailService) {
        this.orderService = orderService;
        this.clientService = clientService;
        this.productService = productService;
        this.orderProductService = orderProductService;
        this.emailService = emailService;
    }

    @RequestMapping(value = "/order/see_the_client/{clientID}", method = RequestMethod.POST)
    public ResponseEntity<Object> createOrder(@PathVariable("clientID") Long clientId) {
        Optional<ClientEntity> clienOptional = clientService.findById(clientId);
        if (clienOptional.isEmpty()) {
            return new ResponseEntity<>("Podany  klient " + clientId + " nie istenieje!! ", HttpStatus.BAD_REQUEST);
        }
        orderService.createOrder(clienOptional.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/order/add_product/{orderId}/{productId}", method = RequestMethod.POST)
    public ResponseEntity<Object> addProduct(@PathVariable("orderId") Long orderId, @PathVariable("productId") Long productId) {
        Optional<ShopOrderEntity> optionalOrder = orderService.findByIdOrder(orderId);
        if (optionalOrder.isEmpty()) {
            return new ResponseEntity<>("Podane zamówienie " + orderId + " nie istenieje!!!", HttpStatus.BAD_REQUEST);
        }

        Optional<ProductEntity> optionalProduct = productService.findByIdProduct(productId);
        if (optionalProduct.isEmpty()) {
            return new ResponseEntity<>("Podany produkt  " + productId + " nie istenieje!!!", HttpStatus.BAD_REQUEST);
        }

        orderProductService.createOrderProduct(optionalProduct.get(), optionalOrder.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/order/delete_product/{orderId}/{productId}", method = RequestMethod.POST)
    public ResponseEntity<Object> deleteProduct(@PathVariable("orderId") Long orderId, @PathVariable("productId") Long productId) {
        Optional<ShopOrderEntity> optionalOrder = orderService.findByIdOrder(orderId);
        if (optionalOrder.isEmpty()) {
            return new ResponseEntity<>("Podane zamówienie " + orderId + " nie istenieje!!!", HttpStatus.BAD_REQUEST);
        }

        Optional<ProductEntity> optionalProduct = productService.findByIdProduct(productId);
        if (optionalProduct.isEmpty()) {
            return new ResponseEntity<>("Podany produkt  " + productId + " nie istenieje!!!", HttpStatus.BAD_REQUEST);
        }

        orderProductService.deleteProductFromOrder(optionalOrder.get(), optionalProduct.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/order/see_shopping/{orderId}", method = RequestMethod.GET)
    public ResponseEntity<Object> getOrderInfo(@PathVariable("orderId") Long orderId) {
        Optional<ShopOrderEntity> optionalOrder = orderService.findByIdOrder(orderId);
        if (optionalOrder.isEmpty()) {
            return new ResponseEntity<>("Podane zamówienie " + orderId + " nie istenieje!!!", HttpStatus.BAD_REQUEST);
        }

        OrderInfoDto orderInfoDto = orderService.getOrderInfo(optionalOrder.get());
        return new ResponseEntity<>(orderInfoDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/order/send_confirm_email/{orderId}", method = RequestMethod.GET)
    public ResponseEntity<Object> sendConfirmEmail(@PathVariable("orderId") Long orderId) throws MessagingException {
        Optional<ShopOrderEntity> optionalOrder = orderService.findByIdOrder(orderId);
        if (optionalOrder.isEmpty()) {
            return new ResponseEntity<>("Podane zamówienie " + orderId + " nie istenieje!!!", HttpStatus.BAD_REQUEST);
        }

        orderService.sendConfirmMail(optionalOrder.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value="/order/confirm/{orderHash}", method = RequestMethod.GET)
    public ResponseEntity<Object> confirmOrder(@PathVariable("orderHash") String orderHash) throws MessagingException {
        Optional<ShopOrderEntity> optionalShopOrder = orderService.findByOrderHash(orderHash);
        if(optionalShopOrder.isEmpty()){
            return new ResponseEntity<>("Podane zamówienie nie istnieje", HttpStatus.BAD_REQUEST);
        }

        ShopOrderEntity shopOrderEntity = optionalShopOrder.get();
        if(!shopOrderEntity.getConfirmed()) {
            orderService.setOrderConfirmed(shopOrderEntity);
            orderService.sendOrderSent(shopOrderEntity);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
