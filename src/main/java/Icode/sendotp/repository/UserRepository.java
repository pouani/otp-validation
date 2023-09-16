package Icode.sendotp.repository;

import Icode.sendotp.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findUserByUserEmail(String Email);
}
