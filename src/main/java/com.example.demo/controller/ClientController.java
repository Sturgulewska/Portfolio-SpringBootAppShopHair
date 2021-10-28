package com.example.demo.controller;

import com.example.demo.domain.Client;
import com.example.demo.domain.dto.ClientDto;
import com.example.demo.domain.dto.ErrorDto;
import com.example.demo.service.ClientService;
import com.example.demo.service.ValidateBindingResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequestMapping("/client")
@CrossOrigin(origins = {"http://localhost:8080"})
@RestController

public class ClientController {


    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @RequestMapping(
            value = "/client/get_client_id/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Object> getClientId(@PathVariable("id") Long id) {
        Optional<Client> client = clientService.findById(id);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }


    @RequestMapping(value = "/client/add_client",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> addClient(@RequestBody @Valid ClientDto clientDto, BindingResult bindingResult) {
        List<ErrorDto> validateErrorList = ValidateBindingResult.validateBindingResult(bindingResult);
        if (!validateErrorList.isEmpty()) {
            return new ResponseEntity<>(validateErrorList, HttpStatus.BAD_REQUEST);
        }

        clientService.createClient( clientDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}




