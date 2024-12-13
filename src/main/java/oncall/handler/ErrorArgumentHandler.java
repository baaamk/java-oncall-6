package oncall.handler;

import static oncall.util.Constants.ERROR_PREFIX;

public enum ErrorArgumentHandler {

    INVALID_INPUT("유효하지 않은 입력 값입니다. 다시 입력해 주세요.");

    private final String message;

    ErrorArgumentHandler(String message) {
        this.message = ERROR_PREFIX + message;
    }

    public IllegalArgumentException getException() {
        return new IllegalArgumentException(message);
    }
}
