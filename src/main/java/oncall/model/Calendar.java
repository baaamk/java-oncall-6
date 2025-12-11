package oncall.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 설정 날짜(월, 시작 요일) 정보를 저장하는 클래스
 */
public class Calendar {
    private final MonthKOR month;
    private final List<DayOfWeekKOR> dayOfMonth;

    private Calendar(MonthKOR month, DayOfWeekKOR firstDayOfMonth) {
        this.month = month;
        this.dayOfMonth = matchDayWithDate(firstDayOfMonth);
    }

    public static Calendar of(int monthNum, String firstDayOfMonth) {
        MonthKOR month = MonthKOR.of(monthNum);
        DayOfWeekKOR dayOfWeek = DayOfWeekKOR.of(firstDayOfMonth);
        return new Calendar(month, dayOfWeek);
    }

    public int getMonth() {
        return month.ordinal() + 1;
    }

    public DayOfWeekKOR getDayOfMonth(int date) {
        return dayOfMonth.get(date - 1);
    }

    public int getLastDateOfMonth() {
        return month.lastDateOfMonth();
    }

    public boolean isHoliday(int date) {
        return month.isHoliday(date);
    }

    private List<DayOfWeekKOR> matchDayWithDate(DayOfWeekKOR firstDayOfMonth) {
        List<DayOfWeekKOR> daysOfMonth = new ArrayList<>();
        List<DayOfWeekKOR> dayOfWeek = List.of(DayOfWeekKOR.values());
        int indexOfFirstDay = firstDayOfMonth.ordinal();
        for (int i = 0; i < month.lastDateOfMonth(); i++) {
            daysOfMonth.add(dayOfWeek.get((i + indexOfFirstDay)%7));
        }
        return daysOfMonth;
    }
}
