package com.example.demo.repository;

import com.example.demo.domain.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository <Client, Long> {
}
