package oncall.domain;

import java.time.DayOfWeek;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

public class MonthAndDayOfWeek {
    private final Month month;
    private final DayOfWeek dayOfWeek;

    public MonthAndDayOfWeek(Month month, DayOfWeek dayOfWeek) {
        this.month = month;
        this.dayOfWeek = dayOfWeek;
    }

    public int getMonthNumber(){
        return month.getValue();
    }

    public int getLastDay() {
        return month.minLength();
    }

    public int getDayOfWeekNumber() {
        return dayOfWeek.getValue();
    }

    public String getDayOfWeekName() {
        return dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.KOREAN);
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }
}
