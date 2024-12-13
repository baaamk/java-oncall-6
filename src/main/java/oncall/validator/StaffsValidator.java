package oncall.validator;

import static oncall.handler.ErrorArgumentHandler.INVALID_INPUT;
import static oncall.util.Constants.INPUT_DELIMITER;
import static oncall.util.Constants.STAFFS_MAX_NUMBER;
import static oncall.util.Constants.STAFFS_MIN_NUMBER;
import static oncall.util.Constants.STAFF_NICKNAME_MAX_LENGTH;
import static oncall.util.Constants.STAFF_NICKNAME_MIN_LENGTH;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class StaffsValidator {

    public List<String> validateAndParse(String input) {
        List<String> staffs = new LinkedList<>(Arrays.asList(input.split(INPUT_DELIMITER)));
        validate(staffs);
        return staffs;
    }

    private void validate(List<String> staffs) {
        validateStaffsNumber(staffs);
        validateStaffsDistinct(staffs);
        validateStaffsNickname(staffs);
    }

    private void validateStaffsNumber(List<String> staffs) {
        if (staffs.size() < STAFFS_MIN_NUMBER || staffs.size() > STAFFS_MAX_NUMBER) {
            throw INVALID_INPUT.getException();
        }
    }

    private void validateStaffsDistinct(List<String> staffs) {
        if (staffs.stream().distinct().count() != staffs.size()) {
            throw INVALID_INPUT.getException();
        }
    }

    private void validateStaffsNickname(List<String> staffs) {
        for (String staff : staffs) {
            validateStaffNickname(staff);
        }
    }

    private void validateStaffNickname(String staff) {
        if (staff.length() < STAFF_NICKNAME_MIN_LENGTH || staff.length() > STAFF_NICKNAME_MAX_LENGTH) {
            throw INVALID_INPUT.getException();
        }
    }
}
