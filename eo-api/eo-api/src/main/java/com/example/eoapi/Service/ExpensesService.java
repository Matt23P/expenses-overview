package com.example.eoapi.Service;

import com.example.eoapi.Entity.Entries;
import com.example.eoapi.Request.CreateEntryRequest;
import com.example.eoapi.Request.GetUserEntriesRequest;
import com.example.eoapi.Response.StatusMessage;
import com.example.eoapi.Response.UserTimelineResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExpensesService {
    StatusMessage addUserYears(int userId, String year);
    StatusMessage addUserMonths(int userId, int yearId, String month);
    StatusMessage addUserDays(int userId, int yearId, int monthId, String day);
    List<Entries> getUserEntriesForGivenYearAndMonthAndDay(GetUserEntriesRequest request);
    StatusMessage addUserEntry(CreateEntryRequest request);
    UserTimelineResponse getUserTimeline(int userId);
}
