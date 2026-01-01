package oncall.domain.vo;

import java.util.Set;

public enum Month {
    JANUARY(1, 31, Set.of(1)),
    FEBRUARY(2, 28, Set.of()),
    MARCH(3, 31, Set.of(1)),
    APRIL(4, 30, Set.of()),
    MAY(5, 31, Set.of(5)),
    JUNE(6, 30, Set.of(6)),
    JULY(7, 31, Set.of()),
    AUGUST(8, 31, Set.of(15)),
    SEPTEMBER(9, 30, Set.of()),
    OCTOBER(10, 31, Set.of(3, 9)),
    NOVEMBER(11, 30, Set.of()),
    DECEMBER(12, 31, Set.of(25));

    private final int month;
    private final int days;



    private final Set<Integer> holidays;

    Month(int month, int days, Set<Integer> holidays) {
        this.month = month;
        this.days = days;
        this.holidays = holidays;
    }

    public int getMonth() {
        return month;
    }

    public int getDays() {
        return days;
    }

    public Set<Integer> getHolidays() {
        return holidays;
    }
}
