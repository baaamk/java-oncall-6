package oncall.controller;

import static oncall.handler.ErrorArgumentHandler.INVALID_INPUT;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import oncall.domain.PlanCalendar;
import oncall.domain.PlanDate;
import oncall.util.RepeatExecutor;
import oncall.validator.AllStaffsValidator;
import oncall.validator.PlanStartValidator;
import oncall.validator.StaffsValidator;
import oncall.view.InputView;
import oncall.view.OutputView;

public class PlanManager {
    private final InputView inputView;
    private final OutputView outputView;
    private final RepeatExecutor repeatExecutor;

    private PlanCalendar planCalendar;
    private List<String> weekdayPlanStaffs;
    private List<String> weekendPlanStaffs;

    public PlanManager(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.repeatExecutor = new RepeatExecutor(outputView);
    }

    public void run() {
        start();
    }

    private void start() {
        List<String> planStart = repeatExecutor.repeatUntilSuccess(this::preparePlanStart);
        int planMonthNumber = Integer.parseInt(planStart.get(0));
        String planDayName = planStart.get(1);
        planCalendar = new PlanCalendar(planMonthNumber, planDayName);
        repeatExecutor.repeatUntilSuccess(this::prepareAllStaffs);
    }

    private List<String> preparePlanStart() {
        String input = inputView.readPlanStart();
        PlanStartValidator validator = new PlanStartValidator();
        return validator.validateAndParse(input);
    }

    private void prepareAllStaffs() {
        AllStaffsValidator validator = new AllStaffsValidator();
        prepareStaffs();
        validator.validate(weekdayPlanStaffs, weekendPlanStaffs);
    }

    private void prepareStaffs() {
        StaffsValidator validator = new StaffsValidator();
        prepareWeekdayStaffs(validator);
        prepareWeekendStaffs(validator);
    }

    private void prepareWeekdayStaffs(StaffsValidator validator) {
        String input = inputView.readWeekdayStaffs();
        weekdayPlanStaffs = validator.validateAndParse(input);
    }

    private void prepareWeekendStaffs(StaffsValidator validator) {
        String input = inputView.readWeekendStaffs();
        weekendPlanStaffs = validator.validateAndParse(input);
    }
}
