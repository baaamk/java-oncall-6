package oncall.validator;

import static oncall.handler.ErrorArgumentHandler.INVALID_INPUT;
import static oncall.util.Constants.INPUT_DELIMITER;
import static oncall.util.Constants.PLAN_START_SIZE;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import oncall.domain.enums.DayName;
import oncall.domain.enums.Month;

public class PlanStartValidator {

    public List<String> validateAndParse(String input) {
        List<String> planStart = Arrays.asList(input.split(INPUT_DELIMITER));
        validate(planStart);
        return planStart;
    }

    private void validate(List<String> planStart) {
        validateSize(planStart);
        validateMonthNumber(planStart.get(0));
        validateDayName(planStart.get(1));
    }

    private void validateSize(List<String> planStart) {
        if (planStart.size() != PLAN_START_SIZE) {
            throw INVALID_INPUT.getException();
        }
    }

    private void validateMonthNumber(String monthStart) {
        validateMonthNumberDigit(monthStart);
        validateMonthNumberExist(monthStart);
    }

    private void validateMonthNumberDigit(String monthStart) {
        try {
            Integer.parseInt(monthStart);
        } catch (NumberFormatException e) {
            throw INVALID_INPUT.getException();
        }
    }

    private void validateMonthNumberExist(String monthStart) {
        Set<Integer> monthNumbers = Month.getMonthNumbers();
        int monthStartNumber = Integer.parseInt(monthStart);
        if (!monthNumbers.contains(monthStartNumber)) {
            throw INVALID_INPUT.getException();
        }
    }

    private void validateDayName(String dayName) {
        validateDayNameExist(dayName);
    }

    private void validateDayNameExist(String dayName) {
        Set<String> dayNames = DayName.getDayNames();
        if (!dayNames.contains(dayName)) {
            throw INVALID_INPUT.getException();
        }
    }
}
