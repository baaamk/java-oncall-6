package oncall.model.domain.service;

import oncall.model.domain.Employees;
import oncall.model.domain.vo.Day;
import oncall.model.domain.vo.Month;
import oncall.model.domain.vo.Week;

import java.util.ArrayList;
import java.util.List;

public class WorkScheduler {

    public static List<String> workScheduleGenerator(
            List<Day> days,
            Month month,
            Employees weekdayWorkers,
            Employees holidayWorkers
    ) {
        List<String> assigned = new ArrayList<>(days.size());

        int weekdayIdx = 0;
        int holidayIdx = 0;

        for (Day day : days) {
            boolean holiday = isHoliday(day, month);

            Employees pool = weekdayWorkers;
            int idx = weekdayIdx;

            if (holiday) {
                pool = holidayWorkers;
                idx = holidayIdx;
            }

            String candidate = pool.get(idx % pool.size()).getName();

            if (!assigned.isEmpty() && assigned.get(assigned.size() - 1).equals(candidate)) {
                idx++;
                candidate = pool.get(idx % pool.size()).getName();
            }

            assigned.add(candidate);

            if (holiday) {
                holidayIdx = idx + 1;
            }

            if (!holiday) {
                weekdayIdx = idx + 1;
            }
        }

        return assigned;
    }

    private static boolean isHoliday(Day day, Month month) {
        Week week = day.getWeek();
        boolean weekend = (week == Week.SATURDAY || week == Week.SUNDAY);
        boolean legalHoliday = month.getHolidays().contains(day.getDate());
        return weekend || legalHoliday;
    }
}