package com.example.eoapi.Repository;

import com.example.eoapi.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    User getUserByEmailAndUsername(String email, String username);
    boolean existsUserByEmail(String email);
    boolean existsUserByUsername(String username);
}
