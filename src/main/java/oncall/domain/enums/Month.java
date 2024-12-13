package oncall.domain.enums;

import static oncall.handler.ErrorStateHandler.INVALID_STATE;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum Month {

    JANUARY(1, 31),
    FEBRUARY(2, 28),
    MARCH(3, 31),
    APRIL(4, 30),
    MAY(5, 31),
    JUNE(6, 30),
    JULY(7, 31),
    AUGUST(8, 31),
    SEPTEMBER(9, 30),
    OCTOBER(10, 31),
    NOVEMBER(11, 30),
    DECEMBER(12, 31);

    private final int number;
    private final int days;

    Month(int number, int days) {
        this.number = number;
        this.days = days;
    }

    public static Set<Integer> getMonthNumbers() {
        Set<Integer> monthNumbers = new HashSet<>();
        for (Month month : Month.values()) {
            monthNumbers.add(month.number);
        }
        return monthNumbers;
    }

    public static Month findMonthOf(int monthNumber) {
        return Arrays.stream(Month.values())
                .filter(month -> month.number == monthNumber)
                .findFirst()
                .orElseThrow(INVALID_STATE::getException);
    }

    public int getNumber() {
        return number;
    }

    public int getDays() {
        return days;
    }
}
