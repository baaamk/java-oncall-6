package oncall.domain;

public class MonthlyRoster {
    private final int month;
    private final int day;
    private final String dayOfWeek;
    private final String name;
    private final boolean holiday;

    public MonthlyRoster(int month, int day, String dayOfWeek, String name, boolean holiday) {
        this.month = month;
        this.day = day;
        this.dayOfWeek = dayOfWeek;
        this.name = name;
        this.holiday = holiday;
    }

    @Override
    public String toString() {
        return String.valueOf(month) + "월 " + String.valueOf(day) + "일 " + dayOfWeek + isHoliday() + name;
    }

    private String isHoliday() {
        //주말은 아님
        if (holiday && !(dayOfWeek.equals("토") || dayOfWeek.equals("일"))) {
            return "(휴일) ";
        }
        return " ";
    }
}
