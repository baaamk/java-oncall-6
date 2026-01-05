package oncall.controller;

import oncall.Parser.Parser;
import oncall.controller.dto.MonthMapper;
import oncall.controller.dto.ScheduleDto;
import oncall.model.domain.Employees;
import oncall.model.domain.service.MonthGenerator;
import oncall.model.domain.service.WorkScheduler;
import oncall.model.domain.vo.Day;
import oncall.view.InputView;
import oncall.view.OutputView;

import java.util.List;
import java.util.function.Supplier;

public class Controller {

    private final InputView inputView;
    private final OutputView outputView;

    public Controller(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void execute() {
        MonthMapper monthMapper = extractedMonth();
        List<Day> days = MonthGenerator.generateMonth(monthMapper.month(), monthMapper.week());

        ScheduleDto scheduleDto = extracted(days, monthMapper);
        outputView.printResult(scheduleDto);

    }

    private ScheduleDto extracted(List<Day> days, MonthMapper monthMapper) {
        return retryInput(()->{
            outputView.noticeInputDaysWorkers();
            String daysWorkers = inputView.inputDaysWorkers();
            List<String> parsedDaysWorkers = Parser.workerParser(daysWorkers);

            outputView.noticeInputHolidaysWorkers();
            String holidaysWorkers = inputView.inputHolidaysWorkers();
            List<String> parsedHolidaysWorkers = Parser.workerParser(holidaysWorkers);

            Employees daysEmployees = Employees.of(parsedDaysWorkers);
            Employees holidayEmployees = Employees.of(parsedHolidaysWorkers);

            List<String> workSchedule = WorkScheduler.workScheduleGenerator(days, monthMapper.month(), daysEmployees, holidayEmployees);

            return ScheduleDto.of(monthMapper.month(), days, workSchedule);
        });
    }

    private MonthMapper extractedMonth() {
        return retryInput(()->{
            outputView.noticeInputMonth();
            String inputMonth = inputView.inputMonth();
            return Parser.monthParser(inputMonth);
        });
    }

    private <T> T retryInput(Supplier<T> action) {
        while (true) {
            try {
                return action.get();
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }
}
