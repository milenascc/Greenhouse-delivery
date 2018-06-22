package com.microservices.delivery1.repositories;

import com.microservices.delivery1.entites.Product;
import org.springframework.data.repository.CrudRepository;


import javax.transaction.Transactional;

@Transactional
public interface ProductRepository extends CrudRepository<Product, Long> {
}
