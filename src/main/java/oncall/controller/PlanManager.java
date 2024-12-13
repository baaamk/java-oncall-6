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
        processPlanStaffs();
        processPlanCalendar();
        finish();
    }

    private void start() {
        List<String> planStart = repeatExecutor.repeatUntilSuccess(this::preparePlanStart);
        int planMonthNumber = Integer.parseInt(planStart.get(0));
        String planDayName = planStart.get(1);
        planCalendar = new PlanCalendar(planMonthNumber, planDayName);
        repeatExecutor.repeatUntilSuccess(this::prepareAllStaffs);
    }

    private void processPlanStaffs() {
        List<String> staffs = new ArrayList<>();
        int weekdayIndex = 0;
        int weekendIndex = 0;
        for (PlanDate planDate : planCalendar.getPlanDates()) {
            if (planDate.isWeekday()) {
                weekdayIndex = processPlanStaff(weekdayIndex, weekdayPlanStaffs, staffs);
            }
            if (!planDate.isWeekday()) {
                weekendIndex = processPlanStaff(weekendIndex, weekendPlanStaffs, staffs);
            }
        }
    }

    private int processPlanStaff(int planStaffIndex, List<String> planStaffs, List<String> staffs) {
        processPlanStaffSwap(planStaffIndex, planStaffs, staffs);
        staffs.add(weekdayPlanStaffs.get(planStaffIndex));
        return processPlanStaffIndex(planStaffIndex, planStaffs);
    }

    private int processPlanStaffIndex(int planStaffIndex, List<String> planStaffs) {
        if (planStaffIndex == planStaffs.size()-1) {
            return 0;
        }
        return planStaffIndex + 1;
    }

    private void processPlanStaffSwap(int planStaffIndex, List<String> planStaffs, List<String> staffs) {
        String plannedStaff = planStaffs.get(planStaffIndex);
        if (!staffs.isEmpty() && Objects.equals(plannedStaff, staffs.get(staffs.size()-1))) {
            int nextIndex = planStaffIndex + 1;
            if (nextIndex == weekdayPlanStaffs.size()) {
                nextIndex = 0;
            }
            Collections.swap(planStaffs, planStaffIndex, nextIndex);
        }
    }

    private void processPlanCalendar() {
        Queue<String> weekdayStaffs = new LinkedList<>(weekdayPlanStaffs);
        Queue<String> weekendStaffs = new LinkedList<>(weekendPlanStaffs);

        for (PlanDate planDate : planCalendar.getPlanDates()) {
            if (planDate.isWeekday()) {
                planCalendar.updateStaffs(weekdayStaffs.peek());
                weekdayStaffs.offer(weekdayStaffs.poll());
            }
            if (!planDate.isWeekday()) {
                planCalendar.updateStaffs(weekendStaffs.peek());
                weekendStaffs.offer(weekendStaffs.poll());
            }
        }
    }

    private void finish() {
        outputView.printPlanCalendar(planCalendar.toString());
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
