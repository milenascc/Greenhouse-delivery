package com.microservices.delivery1.repositories;

import com.microservices.delivery1.entites.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
public interface ClientRepository extends CrudRepository<Client,Long> {

}
