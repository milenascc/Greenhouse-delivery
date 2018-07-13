package com.greenhouse.repository;

import com.greenhouse.model.User;
import com.greenhouse.repository.UserRepository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository("userRepository")
public interface UserRepository extends CrudRepository<User, Long> {
     User findByEmail(String email);
     User findByConfirmationToken(String confirmationToken);
}