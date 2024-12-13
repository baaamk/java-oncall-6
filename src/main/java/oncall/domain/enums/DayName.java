package oncall.domain.enums;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public enum DayName {

    MONDAY("월"),
    TUESDAY("화"),
    WEDNESDAY("수"),
    THURSDAY("목"),
    FRIDAY("금"),
    SATURDAY("토"),
    SUNDAY("일");

    private final String name;

    DayName(String name) {
        this.name = name;
    }

    public static Set<String> getDayNames() {
        Set<String> dayNames = new LinkedHashSet<>();
        for (DayName dayName : DayName.values()) {
            dayNames.add(dayName.name);
        }
        return dayNames;
    }

    public static Queue<DayName> getDayNamesStartingFrom(String dayName) {
        Queue<DayName> dayNames = new LinkedList<>(Arrays.asList(DayName.values()));
        while (!dayNames.isEmpty() && !dayNames.peek().name.equals(dayName)) {
            dayNames.offer(dayNames.poll());
        }
        return dayNames;
    }

    public String getName() {
        return name;
    }

    public boolean isWeekday() {
        Set<DayName> weekdayNames = Set.of(MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY);
        return weekdayNames.contains(this);
    }
}
