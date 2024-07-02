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
import com.example.eoapi.Response.UserTimelineResponse;
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
    public StatusMessage addUserDays(int userId, int yearId, int monthId, String day) {
        if (!userRepository.existsById(userId) || !yearsRepository.existsById(yearId) || !monthsRepository.existsById(monthId)) {
            log.error("User/year/month doesn't exist");
            return new StatusMessage(false, Collections.singletonList("User/year/month doesn't exist"));
        } else if (daysRepository.existsByUserIdAndYearIdAndMonthIdAndDay(userId, yearId, monthId, day)) {
            log.error("Day {} already exists in this month for user {}", day, userId);
            return new StatusMessage(false, Collections.singletonList("This day already exists in given month"));
        }

        Days newDay = Days.builder()
                .userId(userId)
                .yearId(yearId)
                .monthId(monthId)
                .day(day)
                .build();

        try {
            daysRepository.save(newDay);
            log.info("Day successfully created for user {}", userId);
            return new StatusMessage(true, Collections.singletonList(""));
        } catch (Exception ex) {
            log.error("Error while adding day for user {} :  {}", userId, ex.getMessage());
            return new StatusMessage(false, Collections.singletonList("Error while creating new Day. Please try again later."));
        }
    }

    @Override
    public List<Entries> getUserEntriesForGivenYearAndMonthAndDay(GetUserEntriesRequest request) {
        if (userRepository.existsById(request.getUserId())) {
            List<Entries> entriesList = entriesRepository.getEntriesByUserIdAndYearAndMonthAndDay(request.getUserId(),
                    request.getYear(), request.getMonth(), request.getDay());
            if (!entriesList.isEmpty()) {
                return entriesList;
            }
            log.info("User entries list empty");
        } else {
            log.info("User with ID {} doesn't exists", request.getUserId());
        }
        return null;
    }

    @Override
    public StatusMessage addUserEntry(CreateEntryRequest request) {
        if (userRepository.existsById(request.getUserId())) {
            try {
                Entries newEntry = Entries.builder()
                        .userId(request.getUserId())
                        .amount(request.getAmount())
                        .description(request.getDescription())
                        .type(request.getType())
                        .income(request.isIncome())
                        .currency(request.getCurrency())
                        .year(request.getYear())
                        .month(request.getMonth())
                        .day(request.getDay())
                        .build();
                entriesRepository.save(newEntry);
                return new StatusMessage(true, Collections.singletonList(""));
            } catch (Exception ex) {
                log.error("Error while adding entry for user {} :  {}", request.getUserId(), ex.getMessage());
                return new StatusMessage(false, Collections.singletonList(
                        "Error while adding entry for user - please try again later"));
            }
        } else {
            log.info("User with ID {} does not exists", request.getUserId());
            return new StatusMessage(false, Collections.singletonList("User doesn't exist"));
        }
    }

    @Override
    public UserTimelineResponse getUserTimeline(int userId) {
        if (userRepository.existsById(userId)) {
            try {
                List<Years> yearsList = yearsRepository.getYearsByUserId(userId);
                List<Months> monthsList = monthsRepository.getMonthsByUserId(userId);
                List<Days> daysList = daysRepository.getDaysByUserId(userId);
                return new UserTimelineResponse(yearsList, monthsList, daysList, true,
                        Collections.singletonList(""));
            } catch (Exception ex) {
                log.error("Error while getting user yrs, mnths, days for user {} :  {}", userId, ex.getMessage());
                return new UserTimelineResponse(null, null, null, false,
                        Collections.singletonList("Error while fetching data - please try again later"));
            }
        } else {
            log.error("User with ID {} does not exist", userId);
            return new UserTimelineResponse(null, null, null, false,
                    Collections.singletonList("User doesn't exist"));
        }
    }
}
