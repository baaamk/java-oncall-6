package oncall.model;

import oncall.exception.InvalidMonthException;

import java.util.List;

public enum MonthKOR {
    JANUARY(31, List.of(1)),
    FEBRUARY(28, null),
    MARCH(31, List.of(1)),
    APRIL(30, null),
    MAY(31, List.of(5)),
    JUNE(30, List.of(6)),
    JULY(31, null),
    AUGUST(31, List.of(15)),
    SEPTEMBER(30, null),
    OCTOBER(31, List.of(3, 9)),
    NOVEMBER(30, null),
    DECEMBER(31, List.of(25));

    private final int finalDate;
    private final List<Integer> publicHolidays;

    MonthKOR(int finalDate, List<Integer> publicHolidays) {
        this.finalDate = finalDate;
        this.publicHolidays = publicHolidays;
    }

    public static MonthKOR of(int monthNum) {
        int indexOfMonth = monthNum - 1;
        for (MonthKOR month : MonthKOR.values()) {
            if (month.ordinal() == indexOfMonth) {
                return month;
            }
        }
        throw new InvalidMonthException();
    }

    public int lastDateOfMonth() {
        return finalDate;
    }

    public boolean isHoliday(int date) {
        if (publicHolidays == null) {
            return false;
        }
        return publicHolidays.contains(date);
    }
}
