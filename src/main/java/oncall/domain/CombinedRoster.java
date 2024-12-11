package oncall.domain;

import java.util.List;
import oncall.exception.IllegalInputException;

public class CombinedRoster {
    private final Roster weekdayRoster;
    private final Roster weekendRoster;

    public CombinedRoster(Roster weekdayRoster, Roster weekendRoster) {
        validate(weekdayRoster, weekendRoster);
        this.weekdayRoster = weekdayRoster;
        this.weekendRoster = weekendRoster;
    }

    private void validate(Roster weekdayRoster, Roster weekendRoster) {
        List<Worker> weekdayWorkers = weekdayRoster.getWorkers();
        List<Worker> weekendWorkers = weekendRoster.getWorkers();

        if(weekdayWorkers.size() != weekendWorkers.size()){
            throw new IllegalInputException();
        }
        // 중복 검사 로직은 나중에
    }

    public Roster getWeekdayRoster() {
        return weekdayRoster;
    }

    public Roster getWeekendRoster() {
        return weekendRoster;
    }

}
