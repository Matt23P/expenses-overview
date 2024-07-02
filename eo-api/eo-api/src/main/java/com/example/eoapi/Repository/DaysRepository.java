package com.example.eoapi.Repository;

import com.example.eoapi.Entity.Days;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DaysRepository extends JpaRepository<Days, Integer> {
    List<Days> getDaysByUserIdAndMonthIdAndYearId(Integer userId, Integer monthId, Integer yearId);
    List<Days> getDaysByUserId(Integer userId);
    boolean existsByUserIdAndYearIdAndMonthIdAndDay(Integer userId, Integer yearId, Integer monthId, String day);
}
