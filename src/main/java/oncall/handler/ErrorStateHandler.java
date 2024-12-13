package oncall.handler;

import static oncall.util.Constants.ERROR_PREFIX;

public enum ErrorStateHandler {

    INVALID_STATE("유효하지 않은 상태입니다.");

    private final String message;

    ErrorStateHandler(String message) {
        this.message = ERROR_PREFIX + message;
    }

    public IllegalStateException getException() {
        return new IllegalStateException(message);
    }
}
