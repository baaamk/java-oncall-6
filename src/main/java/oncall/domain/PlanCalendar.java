package oncall.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import oncall.domain.enums.DayName;
import oncall.domain.enums.Holiday;
import oncall.domain.enums.Month;

public class PlanCalendar {

    private final Month month;
    private final List<PlanDate> planDates;
    private final List<String> staffs;

    public PlanCalendar(int monthNumber, String dayName) {
        this.month = Month.findMonthOf(monthNumber);
        this.planDates = initialize(dayName);
        this.staffs = new ArrayList<>();
    }

    private List<PlanDate> initialize(String dayName) {
        List<PlanDate> dates = new ArrayList<>();
        Queue<DayName> dayNames = DayName.getDayNamesStartingFrom(dayName);
        int monthDays = month.getDays();
        for (int day = 1; day <= monthDays; day++) {
            dates.add(new PlanDate(day, dayNames.peek(), Holiday.isHoliday(month.getNumber(), day)));
            dayNames.offer(dayNames.poll());
        }
        return dates;
    }

    public List<PlanDate> getPlanDates() {
        return planDates;
    }

    public void updateStaffs(String staff) {
        staffs.add(staff);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < planDates.size(); i++) {
            sb.append(System.lineSeparator());
            sb.append(String.format("%d월 ", month.getNumber()));
            sb.append(planDates.get(i).toString());
            sb.append(staffs.get(i));
        }
        return sb.toString();
    }
}
