package oncall.controller;

import oncall.model.Calendar;
import oncall.model.Scheduler;
import oncall.model.Workers;
import oncall.util.InputParser;
import oncall.view.InputView;
import oncall.view.OutputView;

import java.util.List;

/**
 * 프로그램의 전체 흐름 조율, 입출력과 로직을 연결하는 클래스
 */
public class Controller {
    private final InputView inputView;
    private final OutputView outputView;

    public Controller(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Calendar calendar = createCalendar();
        Scheduler scheduler = createScheduler(calendar);
        outputView.printWorkSchedules(calendar, scheduler);
    }

    private Calendar createCalendar() {
        while (true) {
            try {
                outputView.printCalendarPropertyPrompt();
                String calendarProperty = inputView.readCalendarProperty();
                List<String> properties = InputParser.parseToStrings(calendarProperty);
                int monthNum = InputParser.parseToInt(properties.get(0));
                String firstDayOfMonth = properties.get(1);
                return Calendar.of(monthNum, firstDayOfMonth);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage();
            }
        }
    }

    private Scheduler createScheduler(Calendar calendar) {
        while(true) {
            try {
                outputView.printWeekdayWorkerNamesPrompt();
                String weekdayWorkerNames = inputView.readWeekdayWorkersPrompt();
                Workers weekdayWorkers = Workers.from(InputParser.parseToStrings(weekdayWorkerNames));

                outputView.printHolidayWorkerNamesPrompt();
                String holidayWorkerNames = inputView.readHolidayWorkersPrompt();
                Workers holidayWorkers = Workers.from(InputParser.parseToStrings(holidayWorkerNames));

                return Scheduler.of(calendar, weekdayWorkers, holidayWorkers);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage();
            }
        }
    }
}
