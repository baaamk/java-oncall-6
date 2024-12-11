package oncall.view;

import camp.nextstep.edu.missionutils.Console;
import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import oncall.domain.MonthAndDayOfWeek;
import oncall.domain.Roster;
import oncall.domain.Worker;
import oncall.exception.IllegalInputException;

public class InputView {
    private static final String DELIMITER = ",";

    public MonthAndDayOfWeek getMonthDayOfWeek() {
        String input = Console.readLine();
        String[] splitInput = input.split(DELIMITER);

        Month month = parseMonth(splitInput[0]);
        DayOfWeek dayOfWeek = parseDayOfWeek(splitInput[1]);

        return new MonthAndDayOfWeek(month, dayOfWeek);
    }

    private Month parseMonth(String s) {
        try {
            return Month.of(Integer.parseInt(s));
        } catch (NumberFormatException | DateTimeException e) {
            throw new IllegalInputException();
        }
    }

    private DayOfWeek parseDayOfWeek(String s) {
        try {
            Map<String, Integer> DayOfWeeks = new HashMap<>();
            DayOfWeeks.put("월", 1);
            DayOfWeeks.put("화", 2);
            DayOfWeeks.put("수", 3);
            DayOfWeeks.put("목", 4);
            DayOfWeeks.put("금", 5);
            DayOfWeeks.put("토", 6);
            DayOfWeeks.put("일", 7);

            int index = DayOfWeeks.get(s);
            return DayOfWeek.of(index);
        } catch (DateTimeException | NullPointerException e) {
            throw new IllegalInputException();
        }
    }

    public Roster getRoster() {
        String input = Console.readLine();
        String[] names = input.split(DELIMITER);

        List<Worker> workers = new ArrayList<>();
        for(String name : names) {
            workers.add(new Worker(name));
        }
        return new Roster(workers);
    }

}
