package com.example.demo.service;

import com.example.demo.domain.ClientEntity;
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

    public ClientEntity createClient(ClientDto clientDto) {
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setName(clientDto.getName());
        clientEntity.setStreet(clientDto.getStreet());
        clientEntity.setCity(clientDto.getCity());
        clientEntity.setState(clientDto.getState());
        clientEntity.setZip(clientDto.getCcNumber());
        clientEntity.setCcNumber(clientDto.getCcNumber());
        clientEntity.setCcExpiration(clientDto.getCcExpiration());
        clientEntity.setCcCVV(clientDto.getCcCVV());
        clientEntity.setEmail(clientDto.getEmail());

        return saveClient(clientEntity);

    }


    public ClientEntity saveClient(ClientEntity clientEntity) {

        return clientRepository.save(clientEntity);
    }

    public void deleteClient(ClientEntity clientEntity) {

        clientRepository.delete(clientEntity);
    }

    public Optional<ClientEntity> findById(Long id) {

        return clientRepository.findById(id);
    }

}
