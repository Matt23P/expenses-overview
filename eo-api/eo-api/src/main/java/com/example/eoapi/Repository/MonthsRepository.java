package com.example.eoapi.Repository;

import com.example.eoapi.Entity.Months;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MonthsRepository extends JpaRepository<Months, Integer> {
    List<Months> getMonthsByUserIdAndYearId(Integer userId, Integer yearId);
    boolean existsByUserIdAndYearIdAndMonth(Integer userId, Integer yearId, String month);
}
