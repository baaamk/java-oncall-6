package oncall.model;

import oncall.exception.InvalidDayOfWeekException;

/**
 * 요일 Enum
 */
public enum DayOfWeekKOR {
    MON("월", false),
    TUES("화", false),
    WEDS("수", false),
    THURS("목", false),
    FRI("금", false),
    SAT("토", true),
    SUN("일", true);

    private final String dayOfWeek;
    private final boolean isHoliday;

    DayOfWeekKOR(String dayOfWeek, boolean isHoliday) {
        this.dayOfWeek = dayOfWeek;
        this.isHoliday = isHoliday;
    }

    public static DayOfWeekKOR of(String dayOfWeek) {
        for (DayOfWeekKOR day : DayOfWeekKOR.values()) {
            if (dayOfWeek.equals(day.dayOfWeek)) {
                return day;
            }
        }
        throw new InvalidDayOfWeekException();
    }

    @Override
    public String toString() {
        return this.dayOfWeek;
    }

    public boolean isHoliday() {
        return this.isHoliday;
    }
}
