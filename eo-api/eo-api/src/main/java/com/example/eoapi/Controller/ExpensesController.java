package com.example.eoapi.Controller;

import com.example.eoapi.Entity.Days;
import com.example.eoapi.Entity.Entries;
import com.example.eoapi.Entity.Months;
import com.example.eoapi.Entity.Years;
import com.example.eoapi.Request.CreateDayRequest;
import com.example.eoapi.Request.CreateEntryRequest;
import com.example.eoapi.Request.CreateMonthRequest;
import com.example.eoapi.Request.CreateYearRequest;
import com.example.eoapi.Request.GetUserEntriesRequest;
import com.example.eoapi.Response.StatusMessage;
import com.example.eoapi.Service.ExpensesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("${url.api.expenses}")
public class ExpensesController {
    @Autowired
    private ExpensesService expensesService;

    @GetMapping(path = "/year/{userId}")
    public ResponseEntity<List<Years>> getUserYears(@PathVariable(value="userId") int userId) {
        List<Years> years = expensesService.getUserYears(userId);
        if (years.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            log.info("Found {} years for user {}", years.size(), userId);
            return ResponseEntity.ok(years);
        }
    }

    @PostMapping(path = "/year")
    public ResponseEntity<StatusMessage> addUserYears(@RequestBody CreateYearRequest request) {
        StatusMessage statusMessage = expensesService.addUserYears(request.getUserId(), request.getYear());
        return ResponseEntity.ok(statusMessage);
    }

    @GetMapping(path = "/month/{userId}/{yearId}")
    public ResponseEntity<List<Months>> getUserMonths(@PathVariable(value="userId") int userId,
                                                      @PathVariable(value = "yearId") int yearId) {
        List<Months> months = expensesService.getUserMonthsForGivenYear(userId, yearId);
        if (months.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(months);
        }
    }

    @PostMapping(path = "/month")
    public ResponseEntity<StatusMessage> addUserMonths(@RequestBody CreateMonthRequest request) {
        StatusMessage statusMessage = expensesService.addUserMonths(request.getUserId(), request.getYearId(), request.getMonth());
        return ResponseEntity.ok(statusMessage);
    }

    @GetMapping(path = "/days/{userId}/{yearId}/{monthId}")
    public ResponseEntity<List<Days>> getUserDays(@PathVariable(value = "userId") int userId,
                                                  @PathVariable(value = "yearId") int yearId,
                                                  @PathVariable(value = "monthId") int monthId) {
        List<Days> days = expensesService.getUserDaysForGivenYearAndMonth(userId, yearId, monthId);
        if (days.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(days);
        }
    }

    @PostMapping(path = "/days")
    public ResponseEntity<StatusMessage> addUserDay(@RequestBody CreateDayRequest request) {
        StatusMessage statusMessage = expensesService.addUserDays(request.getUserId(), request.getYearId(), request.getMonthId(), request.getDay());
        return ResponseEntity.ok(statusMessage);
    }

    @PostMapping(path = "/entries/{userId}/{yearId}/{monthId}")
    public ResponseEntity<List<Entries>> getUserEntries(@RequestBody GetUserEntriesRequest request) {
        List<Entries> entries = expensesService.getUserEntriesForGivenYearAndMonthAndDay(request);
        if (entries.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(entries);
        }
    }

    @PostMapping(path = "/entries")
    public ResponseEntity<StatusMessage> addUserEntry(@RequestBody CreateEntryRequest request) {
        StatusMessage response = expensesService.addUserEntry(request);
        return ResponseEntity.ok(response);
    }

}
