package com.example.eoapi.Repository;

import com.example.eoapi.Entity.Years;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface YearsRepository extends JpaRepository<Years, Integer> {
    List<Years> getYearsByUserId(int userId);
    boolean existsByUserIdAndYear(int userId, String year);
}
