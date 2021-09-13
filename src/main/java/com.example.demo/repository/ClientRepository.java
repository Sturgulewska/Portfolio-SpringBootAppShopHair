package com.example.demo.repository;

import com.example.demo.domain.ClientEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository <ClientEntity, Long> {
}
