package com.example.eoapi.Service;

import com.example.eoapi.Entity.Days;
import com.example.eoapi.Entity.Entries;
import com.example.eoapi.Entity.Months;
import com.example.eoapi.Entity.Years;
import com.example.eoapi.Request.CreateEntryRequest;
import com.example.eoapi.Request.GetUserEntriesRequest;
import com.example.eoapi.Response.StatusMessage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExpensesService {
    List<Years> getUserYears(int userId);
    StatusMessage addUserYears(int userId, String year);
    List<Months> getUserMonthsForGivenYear(int userId, int yearId);
    StatusMessage addUserMonths(int userId, int yearId, String month);
    List<Days> getUserDaysForGivenYearAndMonth(int userId, int yearId, int monthId);
    StatusMessage addUserDays(int userId, int yearId, int monthId, String day);
    List<Entries> getUserEntriesForGivenYearAndMonthAndDay(GetUserEntriesRequest request);
    StatusMessage addUserEntry(CreateEntryRequest request);
}
