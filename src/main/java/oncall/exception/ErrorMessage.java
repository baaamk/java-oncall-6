package oncall.exception;

public enum ErrorMessage {
    INVALID_RANGE_NAME("이름은 최대 5자로 제한됩니다."),
    INVALID_FORMAT_NAME("이름은 한글만 들어올 수 있습니다."),
    DUPLICATE_EMPLOYEE("중복된 이름의 근무자가 있습니다."),
    INVALID_EMPLOY_RANGE("근무자는 최소 5명, 최대 35명 입니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
