package oncall.model.domain.vo;

public class Day {
    private final int date;
    private final Week week;

    private Day(int date, Week week) {
        this.date = date;
        this.week = week;
    }

    public static Day of(int date, Week week) {
        return new Day(date, week);
    }

    public int getDate() {
        return date;
    }

    public Week getWeek() {
        return week;
    }
}
