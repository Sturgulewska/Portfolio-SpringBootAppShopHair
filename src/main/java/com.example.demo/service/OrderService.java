package com.example.demo.service;

import com.example.demo.domain.ClientEntity;
import com.example.demo.domain.ProductEntity;
import com.example.demo.domain.ShopOrderEntity;
import com.example.demo.domain.dto.OrderInfoDto;
import com.example.demo.domain.dto.ProductRowDto;
import com.example.demo.repository.ShopOrderRepository;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {

    private final ShopOrderRepository shopOrderRepository;
    private final EmailService emailService;


    public OrderService(ShopOrderRepository shopOrderRepository, EmailService emailService) {
        this.shopOrderRepository = shopOrderRepository;
        this.emailService = emailService;
    }

    public ShopOrderEntity createOrder(ClientEntity clientEntity) {
        ShopOrderEntity shopOrderEntity = new ShopOrderEntity();
        shopOrderEntity.setClientEntity(clientEntity);
        shopOrderEntity.setDate(LocalDateTime.now());
        shopOrderEntity.setHash(UUID.randomUUID().toString());
        shopOrderEntity.setConfirmed(false);
        return saveOrder(shopOrderEntity);
    }

    public void deleteOrder(ShopOrderEntity shopOrderEntity) {
        shopOrderRepository.delete(shopOrderEntity);
    }

    public ShopOrderEntity saveOrder(ShopOrderEntity shopOrderEntity) {
        return shopOrderRepository.save(shopOrderEntity);
    }

    public Optional<ShopOrderEntity> findByIdOrder(Long id) {
        return shopOrderRepository.findById(id);
    }

    public Optional<ShopOrderEntity> findByOrderHash(String orderHash) {
        return shopOrderRepository.findByHash(orderHash);
    }

    public void setOrderConfirmed(ShopOrderEntity shopOrderEntity) {
        shopOrderEntity.setConfirmed(true);
        saveOrder(shopOrderEntity);
    }

    public void sendConfirmMail(ShopOrderEntity shopOrderEntity) throws MessagingException {
        String email = shopOrderEntity.getClientEntity().getEmail();
        String content = "";

       // OrderInfoDto orderInfoDto = getOrderInfo(shopOrder);

        OrderInfoDto orderInfoDto = getOrderInfo(shopOrderEntity);

        content = " Kwota netto Twojego zamówienia wynosi: " + orderInfoDto.getNetto() + "\n";
        content += " Kwota brutto Twojego zamówienia wynosi: " + orderInfoDto.getBrutto() + "\n";
        content += " Twoja lista produktów: " + "\n";
        for (ProductRowDto p : orderInfoDto.getProductList()) {
            content += " Produkt: " + p.getName() + ", kwota netto: " + p.getNetto() + ", kwota brutto wynosi: " + p.getBrutto() + "\n";
        }

        String url = "localhost:8080/order/confirm/" + shopOrderEntity.getHash();
        content += "\nTwój link do potwierdzenia zamówienia:";
        content += "\n\t" + url;

        emailService.sendEmail(email, "Potwierdzenie zamówienia", content);
    }


    public void sendOrderSent(ShopOrderEntity shopOrderEntity) throws MessagingException {
        String email = shopOrderEntity.getClientEntity().getEmail();
        String content = "Twoje zamówienie dotrze do Ciebie w przeciągu 7 dni.";

        emailService.sendEmail(email, "Zamówienie zostało wysłane", content);
    }

    public OrderInfoDto getOrderInfo(ShopOrderEntity shopOrderEntity) {
        OrderInfoDto orderInfoDto = new OrderInfoDto();
        List<ProductEntity> productList = shopOrderEntity.getProductList();
        List<ProductRowDto> productRowDtos = new ArrayList<>();

        Float priceNetto = 0.0f;
        Float priceBrutto = 0.0f;
        Integer productCount = productList.size();

        for (ProductEntity product : productList) {
            // zlicza cene netto
            priceNetto += product.getNetto();

            //zlicza cene brutto
            priceBrutto += product.getBrutto();

            // tworzy obiekt
            ProductRowDto productRowDto = new ProductRowDto();

            // ustawia cene netto
            productRowDto.setNetto(product.getNetto());

            // ustawia cene brutto
            productRowDto.setBrutto(product.getBrutto());

            // ustawia nazwę
            productRowDto.setName(product.getName());

            // dodaje obiekt do listy
            productRowDtos.add(productRowDto);
        }

        orderInfoDto.setNetto(priceNetto);
        orderInfoDto.setBrutto(priceBrutto);
        orderInfoDto.setProductCount(productCount);
        orderInfoDto.setProductList(productRowDtos);
        return orderInfoDto;
    }
}

