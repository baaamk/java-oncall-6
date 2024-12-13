package oncall.domain;

import oncall.domain.enums.DayName;

public class PlanDate {

    private final int day;
    private final DayName dayName;
    private final boolean isHoliday;

    public PlanDate(int day, DayName dayName, boolean isHoliday) {
        this.day = day;
        this.dayName = dayName;
        this.isHoliday = isHoliday;
    }

    public boolean isWeekday() {
        return dayName.isWeekday() && !isHoliday;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%d일 ", day));
        sb.append(String.format("%s%s ", dayName.getName(), formatDayNameType()));
        return sb.toString();
    }

    private String formatDayNameType() {
        if (dayName.isWeekday() && isHoliday) {
            return "(휴일)";
        }
        return "";
    }
}
