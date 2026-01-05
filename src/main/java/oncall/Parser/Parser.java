package oncall.Parser;

import oncall.controller.dto.MonthMapper;
import oncall.exception.ErrorMessage;
import oncall.model.domain.vo.Month;
import oncall.model.domain.vo.Week;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    private static final String DELIMITER = ",";
    private static final int GROUP_MONTH = 1;
    private static final int GROUP_WEEK = 2;

    private static final Pattern MONTH_PATTERN =
            Pattern.compile("^\\s*(\\d{1,2})\\s*,\\s*([^,\\s]+)\\s*$");

    private static final Pattern WORKER_PATTERN =
            Pattern.compile("^\\s*[^,\\s]+\\s*(\\s*,\\s*[^,\\s]+\\s*)*$");

    public static MonthMapper monthParser(String inputMonth) {
        Matcher matcher = validateAndGetMonthMatcher(inputMonth);

        int monthValue = Integer.parseInt(matcher.group(GROUP_MONTH));
        String weekToken = matcher.group(GROUP_WEEK).trim();

        Month month = Month.from(monthValue);
        Week week = extractWeek(weekToken);

        return new MonthMapper(month, week);
    }

    public static List<String> workerParser(String inputWorker) {
        validateWorkerInput(inputWorker);

        List<String> workers = Arrays.stream(inputWorker.split(DELIMITER))
                .map(String::trim)
                .toList();

        validateNameRules(workers);
        validateNoDuplicate(workers);
        validateEmployeeCount(workers);

        return workers;
    }

    private static Matcher validateAndGetMonthMatcher(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.EMPTY_INPUT.getMessage());
        }

        Matcher matcher = MONTH_PATTERN.matcher(input);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_MONTH_INPUT.getMessage());
        }

        int month = Integer.parseInt(matcher.group(GROUP_MONTH));
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_MONTH_RANGE.getMessage());
        }

        extractWeek(matcher.group(GROUP_WEEK).trim());

        return matcher;
    }

    private static void validateWorkerInput(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.EMPTY_INPUT.getMessage());
        }
        if (!WORKER_PATTERN.matcher(input).matches()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_FORMAT_NAME.getMessage());
        }
    }

    private static void validateNameRules(List<String> workers) {
        for (String name : workers) {
            if (name.length() > 5) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_RANGE_NAME.getMessage());
            }
            if (!name.matches("^[가-힣]+$")) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_FORMAT_NAME.getMessage());
            }
        }
    }

    private static void validateNoDuplicate(List<String> workers) {
        if (new HashSet<>(workers).size() != workers.size()) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATE_EMPLOYEE.getMessage());
        }
    }

    private static void validateEmployeeCount(List<String> workers) {
        int size = workers.size();
        if (size < 5 || size > 35) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_EMPLOY_RANGE.getMessage());
        }
    }

    private static Week extractWeek(String week) {
        if (week.equals(Week.MONDAY.getName())) {
            return Week.MONDAY;
        }
        if (week.equals(Week.TUESDAY.getName())) {
            return Week.TUESDAY;
        }
        if (week.equals(Week.WEDNESDAY.getName())) {
            return Week.WEDNESDAY;
        }
        if (week.equals(Week.THURSDAY.getName())) {
            return Week.THURSDAY;
        }
        if (week.equals(Week.FRIDAY.getName())) {
            return Week.FRIDAY;
        }
        if (week.equals(Week.SATURDAY.getName())) {
            return Week.SATURDAY;
        }
        if (week.equals(Week.SUNDAY.getName())) {
            return Week.SUNDAY;
        }

        throw new IllegalArgumentException(ErrorMessage.INVALID_WEEK.getMessage());
    }
}