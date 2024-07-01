package com.example.eoapi.Service;

import com.example.eoapi.Entity.Days;
import com.example.eoapi.Entity.Entries;
import com.example.eoapi.Entity.Months;
import com.example.eoapi.Entity.Years;
import com.example.eoapi.Repository.DaysRepository;
import com.example.eoapi.Repository.EntriesRepository;
import com.example.eoapi.Repository.MonthsRepository;
import com.example.eoapi.Repository.UserRepository;
import com.example.eoapi.Repository.YearsRepository;
import com.example.eoapi.Request.CreateEntryRequest;
import com.example.eoapi.Request.GetUserEntriesRequest;
import com.example.eoapi.Response.StatusMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class ExpensesServiceImpl implements ExpensesService{

    @Autowired
    private YearsRepository yearsRepository;
    @Autowired
    private MonthsRepository monthsRepository;
    @Autowired
    private DaysRepository daysRepository;
    @Autowired
    private EntriesRepository entriesRepository;
    @Autowired
    private UserRepository userRepository;


    @Override
    public List<Years> getUserYears(int userId) {
        if (userRepository.existsById(userId)) {
            List <Years> yearsList = yearsRepository.getYearsByUserId(userId);
            if (!yearsList.isEmpty()) {
                return yearsList;
            }
            log.info("User years list empty");
        } else {
            log.info("No such user with id {}", userId);
        }
        return null;
    }

    @Override
    public StatusMessage addUserYears(int userId, String year) {
        if (!userRepository.existsById(userId)) {
            log.error("User with id {} does not exist", userId);
            return new StatusMessage(false, Collections.singletonList("User doesn't exists"));
        } else if (yearsRepository.existsByUserIdAndYear(userId, year)) {
            log.error("Year {} already exists", year);
            return new StatusMessage(false, Collections.singletonList("Year already exists"));
        }

        Years newYear = Years.builder()
                .year(year)
                .userId(userId)
                .build();
        try {
            yearsRepository.save(newYear);
            log.info("Year successfully created for user {}", userId);
            return new StatusMessage(true, Collections.singletonList(""));
        } catch (Exception ex) {
            log.error("Error while adding year for user {} :  {}", userId, ex.getMessage());
            return new StatusMessage(false, Collections.singletonList("Error while creating new year. Please try again later."));
        }
    }

    @Override
    public List<Months> getUserMonthsForGivenYear(int userId, int yearId) {
        if (userRepository.existsById(userId) && yearsRepository.existsById(yearId)) {
            List<Months> monthsList = monthsRepository.getMonthsByUserIdAndYearId(userId, yearId);
            if (!monthsList.isEmpty()) {
                return monthsList;
            }
            log.info("User months list empty");
        } else {
            log.info("User or year with given ID doesn't exists");
        }
        return null;
    }

    @Override
    public StatusMessage addUserMonths(int userId, int yearId, String month) {
        if (!userRepository.existsById(userId) || !yearsRepository.existsById(yearId)) {
            log.error("User or year doesn't exist");
            return new StatusMessage(false, Collections.singletonList("User or year doesn't exist."));
        } else if (monthsRepository.existsByUserIdAndYearIdAndMonth(userId, yearId, month)) {
            log.error("Month {} already exists in this year for user {}", month, userId);
            return new StatusMessage(false, Collections.singletonList("This month already exists in given year."));
        }

        Months newMonth = Months.builder()
                .userId(userId)
                .yearId(yearId)
                .month(month)
                .build();
        try {
            monthsRepository.save(newMonth);
            log.info("Month successfully created for user {}", userId);
            return new StatusMessage(true, Collections.singletonList(""));
        } catch (Exception ex) {
            log.error("Error while adding month for user {} :  {}", userId, ex.getMessage());
            return new StatusMessage(false, Collections.singletonList("Error while creating new month. Please try again later."));
        }
    }

    @Override
    public List<Days> getUserDaysForGivenYearAndMonth(int userId, int yearId, int monthId) {
        return null;
    }

    @Override
    public StatusMessage addUserDays(int userId, int yearId, int monthId, String day) {
        return null;
    }

    @Override
    public List<Entries> getUserEntriesForGivenYearAndMonthAndDay(GetUserEntriesRequest request) {
        return null;
    }

    @Override
    public StatusMessage addUserEntry(CreateEntryRequest request) {
        return null;
    }
}
