package com.example.eoapi.Repository;

import com.example.eoapi.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User getUserByEmailAndUsername(String email, String username);
    User getUserByEmail(String email);
    boolean existsUserByEmail(String email);
    boolean existsUserByUsername(String username);
}
