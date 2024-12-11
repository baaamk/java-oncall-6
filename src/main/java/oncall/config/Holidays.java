package oncall.config;

public enum Holidays {
    NEW_YEARS_DAY(1, 1),         // 1월 1일
    INDEPENDENCE_DAY(3, 1),      // 3월 1일
    CHILDRENS_DAY(5, 5),         // 5월 5일
    MEMORIAL_DAY(6, 6),          // 6월 6일
    LIBERATION_DAY(8, 15),       // 8월 15일
    NATIONAL_FOUNDATION_DAY(10, 3), // 10월 3일
    HANGUL_DAY(10, 9),           // 10월 9일
    CHRISTMAS_DAY(12, 25);       // 12월 25일

    private final int month;
    private final int day;

    // Constructor
    Holidays(int month, int day) {
        this.month = month;
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }
}
