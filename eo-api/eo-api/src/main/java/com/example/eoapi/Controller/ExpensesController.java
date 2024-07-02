package com.example.eoapi.Controller;

import com.example.eoapi.Entity.Entries;
import com.example.eoapi.Request.CreateDayRequest;
import com.example.eoapi.Request.CreateEntryRequest;
import com.example.eoapi.Request.CreateMonthRequest;
import com.example.eoapi.Request.CreateYearRequest;
import com.example.eoapi.Request.GetUserEntriesRequest;
import com.example.eoapi.Response.StatusMessage;
import com.example.eoapi.Response.UserTimelineResponse;
import com.example.eoapi.Service.ExpensesService;
import com.example.eoapi.Utils.RequestValidator;
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
    @Autowired
    private RequestValidator requestValidator;

    @PostMapping(path = "/year")
    public ResponseEntity<StatusMessage> addUserYears(@RequestBody CreateYearRequest request) {
        if (requestValidator.validateYear(request.getYear())) {
            StatusMessage statusMessage = expensesService.addUserYears(request.getUserId(), request.getYear());
            return ResponseEntity.ok(statusMessage);
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

    @PostMapping(path = "/month")
    public ResponseEntity<StatusMessage> addUserMonths(@RequestBody CreateMonthRequest request) {
        if (requestValidator.validateMonth(request.getMonth())) {
            StatusMessage statusMessage = expensesService.addUserMonths(request.getUserId(), request.getYearId(), request.getMonth());
            return ResponseEntity.ok(statusMessage);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping(path = "/days")
    public ResponseEntity<StatusMessage> addUserDay(@RequestBody CreateDayRequest request) {
        if (requestValidator.validateDay(request.getDay())) {
            StatusMessage statusMessage = expensesService.addUserDays(request.getUserId(), request.getYearId(), request.getMonthId(), request.getDay());
            return ResponseEntity.ok(statusMessage);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping(path = "/entries/read")
    public ResponseEntity<List<Entries>> getUserEntries(@RequestBody GetUserEntriesRequest request) {
        List<Entries> entries = expensesService.getUserEntriesForGivenYearAndMonthAndDay(request);
        if (entries.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(entries);
        }
    }

    @PostMapping(path = "/entries/add")
    public ResponseEntity<StatusMessage> addUserEntry(@RequestBody CreateEntryRequest request) {
        if (requestValidator.validateNewEntry(request)) {
            StatusMessage response = expensesService.addUserEntry(request);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping(path = "/timeline/{userId}")
    public ResponseEntity<UserTimelineResponse> getUserTimeline(@PathVariable(value = "userId") int userId) {
        UserTimelineResponse response = expensesService.getUserTimeline(userId);
        return ResponseEntity.ok(response);
    }

}
