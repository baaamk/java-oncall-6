package oncall.controller;

import java.util.List;
import oncall.domain.CombinedRoster;
import oncall.domain.MonthAndDayOfWeek;
import oncall.domain.MonthlyRoster;
import oncall.domain.Roster;
import oncall.domain.Worker;
import oncall.service.CustomService;
import oncall.view.InputView;
import oncall.view.OutputView;

public class CustomController extends ExceptionLoopController{
    private final InputView input;
    private final OutputView output;
    private final CustomService service;

    public CustomController(InputView input, OutputView output, CustomService service) {
        this.input = input;
        this.output = output;
        this.service = service;
    }

    public void run() {
        MonthAndDayOfWeek monthAndDayOfWeek = repeatUntilValid(this::getMonthAndDayOfWeek);
        CombinedRoster combinedRoster = repeatUntilValid(this::getRoster);
        List<MonthlyRoster> monthlyRoster = service.makeMonthlyRoster(monthAndDayOfWeek, combinedRoster);
        output.printMonthlyRoster(monthlyRoster);
    }

    private MonthAndDayOfWeek getMonthAndDayOfWeek() {
        output.printGetMonthDayOfWeek();
        return input.getMonthDayOfWeek();
    }

    private CombinedRoster getRoster() {
        output.printGetWeekdayRoster();
        Roster weekdayRoster = input.getRoster();
        output.printGetWeekendRoster();
        Roster weekendRoster = input.getRoster();

        return new CombinedRoster(weekdayRoster, weekendRoster);
    }
}
