package main.java.com.greenhouse.repository;
import org.springframework.data.jpa.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.greenhouse.*;

@Repository("userRepository")
public interface UserRepository extends CrudRepository<User, Long> {
     User findByEmail(String email);
     User findByConfirmationToken(String confirmationToken);
}