package com.example.demo.service;

import com.example.demo.domain.Client;
import com.example.demo.domain.dto.ClientDto;
import com.example.demo.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client createClient(ClientDto clientDto) {
        Client client = new Client();
        client.setName(clientDto.getName());
        client.setStreet(clientDto.getStreet());
        client.setCity(clientDto.getCity());
        client.setState(clientDto.getState());
        client.setZip(clientDto.getCcNumber());
        client.setCcNumber(clientDto.getCcNumber());
        client.setCcExpiration(clientDto.getCcExpiration());
        client.setCcCVV(clientDto.getCcCVV());
        client.setEmail(clientDto.getEmail());

        return saveClient(client);

    }


    public Client saveClient(Client client) {

        return clientRepository.save(client);
    }

    public void deleteClient(Client client) {

        clientRepository.delete(client);
    }

    public Optional<Client> findById(Long id) {

        return clientRepository.findById(id);
    }

}
