package com.microservices.delivery1.repositories;

import com.microservices.delivery1.entites.Ordered;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface OrderedRepository extends CrudRepository<Ordered,Long> {
}
