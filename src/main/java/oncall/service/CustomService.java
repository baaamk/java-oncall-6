package oncall.service;

import java.time.DayOfWeek;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import oncall.config.Holidays;
import oncall.domain.CombinedRoster;
import oncall.domain.MonthAndDayOfWeek;
import oncall.domain.MonthlyRoster;
import oncall.domain.Roster;

public class CustomService {
    public List<MonthlyRoster> makeMonthlyRoster(MonthAndDayOfWeek monthAndDayOfWeek, CombinedRoster combinedRoster) {
        int monthNumber = monthAndDayOfWeek.getMonthNumber(); // 5
        int lastDay = monthAndDayOfWeek.getLastDay(); // 31

        Roster weekdayRoster = combinedRoster.getWeekdayRoster();
        Roster weekendRoster = combinedRoster.getWeekendRoster();

        int dayOfWeekIndex = monthAndDayOfWeek.getDayOfWeekNumber(); // 월-1 , 일-7
        List<MonthlyRoster> monthlyRosters = new ArrayList<>();

        int weekdayIndex = 0;
        int weekendIndex = 0;
        String name = "";
        for (int day = 1; day <= lastDay; day++) {
            boolean isHoliday = isHoliday(monthNumber, day, dayOfWeekIndex);
            if (isHoliday) {
                if(name.equals(weekendRoster.getWorkerNameByIndex(weekendIndex))){//이전 근무자와 이름이 연속되는지 확인
                    weekendRoster.swapRoster(weekendIndex);//연속되면 스왑
                }
                name = weekendRoster.getWorkerNameByIndex(weekendIndex);
                weekendIndex += 1;
                if(weekendIndex > weekendRoster.getRosterSize() - 1){ //순번 다 돌면 초기화
                    weekendIndex = 0;
                }
            }
            if (!isHoliday) {
                if(name.equals(weekdayRoster.getWorkerNameByIndex(weekdayIndex))){//이전 근무자와 이름이 연속되는지 확인
                    weekdayRoster.swapRoster(weekdayIndex);//연속되면 스왑
                }
                name = weekdayRoster.getWorkerNameByIndex(weekdayIndex);
                weekdayIndex += 1;
                if(weekdayIndex > weekdayRoster.getRosterSize() - 1){ //순번 다 돌면 초기화
                    weekdayIndex = 0;
                }
            }
            //리스트에 추가
            monthlyRosters.add(new MonthlyRoster(monthNumber, day, getDayOfWeekName(dayOfWeekIndex), name, isHoliday));

            //요일 순번 다 돌면 초기화
            dayOfWeekIndex += 1;
            if (dayOfWeekIndex > 7) {
                dayOfWeekIndex = 1;
            }
        }
        return monthlyRosters;
    }

    private String getDayOfWeekName(int dayOfWeekNumber) {
        DayOfWeek dayOfWeek = DayOfWeek.of(dayOfWeekNumber);
        return dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.KOREAN);
    }

    private boolean isHoliday(int month, int day, int dayOfWeekNumber) {
        for (Holidays h : Holidays.values()) {
            if (h.getMonth() == month && h.getDay() == day) {
                return true;
            }
        }
        if (DayOfWeek.of(dayOfWeekNumber) == DayOfWeek.SATURDAY || DayOfWeek.of(dayOfWeekNumber) == DayOfWeek.SUNDAY) {
            return true;
        }

        return false;
    }
}
