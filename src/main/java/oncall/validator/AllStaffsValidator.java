package oncall.validator;

import static oncall.handler.ErrorArgumentHandler.INVALID_INPUT;

import java.util.List;

public class AllStaffsValidator {

    public void validate(List<String> weekdayStaffs, List<String> weekendStaffs) {
        validateStaffsSize(weekdayStaffs, weekendStaffs);
        validateStaffsExist(weekdayStaffs, weekendStaffs);
    }

    private void validateStaffsSize(List<String> weekdayStaffs, List<String> weekendStaffs) {
        if (weekdayStaffs.size() != weekendStaffs.size()) {
            throw INVALID_INPUT.getException();
        }
    }

    private void validateStaffsExist(List<String> weekdayStaffs, List<String> weekendStaffs) {
        for (String staff : weekdayStaffs) {
            if (!weekendStaffs.contains(staff)) {
                throw INVALID_INPUT.getException();
            }
        }
    }

}
