package oncall.domain.enums;

public enum Holiday {

    NEW_YEARS_DAY("신정", 1, 1),
    INDEPENDENCE_MOVEMENT_DAY("삼일절", 3, 1),
    CHILDRENS_DAY("어린이날", 5, 5),
    MEMORIAL_DAY("현충일", 6, 6),
    LIBERATION_DAY("광복절", 8, 15),
    NATIONAL_FOUNDATION_DAY("개천절", 10, 3),
    HANGUL_DAY("한글날", 10, 9),
    CHRISTMAS("성탄절", 12, 25);

    private final int month;
    private final int day;

    Holiday(String name, int month, int day) {
        this.month = month;
        this.day = day;
    }

    public static boolean isHoliday(int month, int day) {
        for (Holiday holiday : Holiday.values()) {
            if (holiday.month == month && holiday.day == day) {
                return true;
            }
        }
        return false;
    }
}
