package oncall.model.domain.service;

import oncall.model.domain.vo.Day;
import oncall.model.domain.vo.Month;
import oncall.model.domain.vo.Week;

import java.util.ArrayList;
import java.util.List;

public class MonthGenerator {

    public static List<Day> generateMonth(Month month, Week startWeek) {
        List<Day> days = new ArrayList<>(month.getDays());

        Week currentWeek = startWeek;

        for (int date = 1; date <= month.getDays(); date++) {
            days.add(Day.of(date, currentWeek));
            currentWeek = currentWeek.next();
        }

        return days;
    }
}