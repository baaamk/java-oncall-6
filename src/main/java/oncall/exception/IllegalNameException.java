package oncall.exception;

public class IllegalNameException extends CustomException {
    public IllegalNameException() {
        super("이름이 5자 초과입니다. 다시 입력해 주세요.");
    }
}
