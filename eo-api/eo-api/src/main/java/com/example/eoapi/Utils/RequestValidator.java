package com.example.eoapi.Utils;

import com.example.eoapi.Request.CreateEntryRequest;
import com.example.eoapi.Request.CreateUserRequest;
import com.example.eoapi.Request.LoginRequest;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.eoapi.Utils.Constants.MONTH_DAY_REGEX;
import static com.example.eoapi.Utils.Constants.YEAR_REGEX;

@Slf4j
@NoArgsConstructor
@Component
public class RequestValidator {

    public boolean validateLoginRequest(LoginRequest request) {
        return request.getPassword() != null && !request.getPassword().isEmpty() && request.getEmail() != null &&
                !request.getEmail().isEmpty();
    }

    public boolean validateSignUpRequest(CreateUserRequest request) {
        return request.getUserCurrency() != null && !request.getUserCurrency().isEmpty() &&
                request.getUsername() != null && !request.getUsername().isEmpty() &&
                request.getPassword() != null && !request.getPassword().isEmpty() &&
                request.getEmail() != null && !request.getEmail().isEmpty();
    }

    public boolean validateYear(String year) {
        if (year == null || year.isEmpty()) {
            return false;
        }
        Pattern patternYear = Pattern.compile(YEAR_REGEX);
        Matcher matcher = patternYear.matcher(year);
        if (!matcher.matches()) {
            return false;
        }
        int yearNumeric = Integer.parseInt(year);
        return yearNumeric >= 2022;
    }

    public boolean validateMonth(String month) {
        if (month == null || month.isEmpty()) {
            return false;
        }
        Pattern patternYear = Pattern.compile(MONTH_DAY_REGEX);
        Matcher matcher = patternYear.matcher(month);
        if (!matcher.matches()) {
            return false;
        }
        int monthNumeric = Integer.parseInt(month);
        return monthNumeric >= 1 && monthNumeric <= 12;
    }

    public boolean validateDay(String day) {
        if (day == null || day.isEmpty()) {
            return false;
        }
        Pattern patternYear = Pattern.compile(MONTH_DAY_REGEX);
        Matcher matcher = patternYear.matcher(day);
        if (!matcher.matches()) {
            return false;
        }
        int dayNumeric = Integer.parseInt(day);
        return dayNumeric >= 1 && dayNumeric <= 31;
    }

    public boolean validateNewEntry(CreateEntryRequest request) {
        try {
            Types type = Types.valueOf(request.getType());
        } catch (IllegalArgumentException e) {
            return false;
        }

        try {
            Currencies currency = Currencies.valueOf(request.getCurrency());
        } catch (IllegalArgumentException e) {
            return false;
        }

        return validateDay(request.getDay()) && validateMonth(request.getMonth()) && validateYear(request.getYear());
    }
}
