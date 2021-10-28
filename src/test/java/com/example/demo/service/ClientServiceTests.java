package com.example.demo.service;

import com.example.demo.domain.Client;
import com.example.demo.domain.dto.ClientDto;
import com.example.demo.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ClientServiceTests {


    private ClientRepository clientRepository = Mockito.mock(ClientRepository.class);

    @Test
    void shouldFindClientId(){
        //Given
        ClientService clientService = new ClientService(clientRepository);

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


        //When and Then
        Mockito.when(clientRepository.findById(1L)).thenReturn(Optional.of(client));


    }}