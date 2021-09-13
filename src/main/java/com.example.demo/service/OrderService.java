package com.example.demo.service;

import com.example.demo.domain.Client;
import com.example.demo.domain.Product;
import com.example.demo.domain.ShopOrder;
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

    public ShopOrder createOrder(Client client) {
        ShopOrder shopOrder = new ShopOrder();
        shopOrder.setClient(client);
        shopOrder.setDate(LocalDateTime.now());
        shopOrder.setHash(UUID.randomUUID().toString());
        shopOrder.setConfirmed(false);
        return saveOrder(shopOrder);
    }

    public void deleteOrder(ShopOrder shopOrder) {
        shopOrderRepository.delete(shopOrder);
    }

    public ShopOrder saveOrder(ShopOrder shopOrder) {
        return shopOrderRepository.save(shopOrder);
    }

    public Optional<ShopOrder> findByIdOrder(Long id) {
        return shopOrderRepository.findById(id);
    }

    public Optional<ShopOrder> findByOrderHash(String orderHash) {
        return shopOrderRepository.findByHash(orderHash);
    }

    public void setOrderConfirmed(ShopOrder shopOrder) {
        shopOrder.setConfirmed(true);
        saveOrder(shopOrder);
    }

    public void sendConfirmMail(ShopOrder shopOrder) throws MessagingException {
        String email = shopOrder.getClient().getEmail();
        String content = "";

       // OrderInfoDto orderInfoDto = getOrderInfo(shopOrder);

        OrderInfoDto orderInfoDto = getOrderInfo(shopOrder);

        content = " Kwota netto Twojego zamówienia wynosi: " + orderInfoDto.getNetto() + "\n";
        content += " Kwota brutto Twojego zamówienia wynosi: " + orderInfoDto.getBrutto() + "\n";
        content += " Twoja lista produktów: " + "\n";
        for (ProductRowDto p : orderInfoDto.getProductList()) {
            content += " Produkt: " + p.getName() + ", kwota netto: " + p.getNetto() + ", kwota brutto wynosi: " + p.getBrutto() + "\n";
        }

        String url = "localhost:8080/order/confirm/" + shopOrder.getHash();
        content += "\nTwój link do potwierdzenia zamówienia:";
        content += "\n\t" + url;

        emailService.sendEmail(email, "Potwierdzenie zamówienia", content);
    }


    public void sendOrderSent(ShopOrder shopOrder) throws MessagingException {
        String email = shopOrder.getClient().getEmail();
        String content = "Twoje zamówienie dotrze do Ciebie w przeciągu 7 dni.";

        emailService.sendEmail(email, "Zamówienie zostało wysłane", content);
    }

    public OrderInfoDto getOrderInfo(ShopOrder shopOrder) {
        OrderInfoDto orderInfoDto = new OrderInfoDto();
        List<Product> productList = shopOrder.getProductList();
        List<ProductRowDto> productRowDtos = new ArrayList<>();

        Float priceNetto = 0.0f;
        Float priceBrutto = 0.0f;
        Integer productCount = productList.size();

        for (Product product : productList) {
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

