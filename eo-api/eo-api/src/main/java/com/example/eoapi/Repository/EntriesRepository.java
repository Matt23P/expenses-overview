package com.example.eoapi.Repository;

import com.example.eoapi.Entity.Entries;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EntriesRepository extends JpaRepository<Entries, Integer> {
    List<Entries> getEntriesByUserIdAndYearAndMonthAndDay(Integer userId, String year, String month, String day);
}
