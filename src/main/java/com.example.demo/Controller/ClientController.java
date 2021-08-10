package com.example.demo.Controller;

import com.example.demo.domain.Category;
import com.example.demo.domain.Client;
import com.example.demo.domain.dto.AddProductDto;
import com.example.demo.domain.dto.ClientDto;
import com.example.demo.domain.dto.ErrorDto;
import com.example.demo.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestMapping("/client")
@CrossOrigin
@RestController

public class ClientController {


    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @RequestMapping(
            value = "/getClientId/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getClientId(@PathVariable("id") Long id) {
        Optional<Client> client = clientService.findById(id);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }


    @RequestMapping(value = "/addClient",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> addClient(@RequestBody @Valid ClientDto clientDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<ErrorDto> errorDtoList = new ArrayList<>();
            bindingResult.getFieldErrors().forEach(e -> errorDtoList.add(new ErrorDto(e.getDefaultMessage(), e.getField())));
            return new ResponseEntity<>(errorDtoList, HttpStatus.BAD_REQUEST);
        }

        clientService.createClient( clientDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}




