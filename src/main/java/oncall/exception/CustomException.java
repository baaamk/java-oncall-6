package oncall.exception;

public class CustomException extends IllegalArgumentException{
    private static final String ERROR_PREFIX = "[ERROR] ";

    CustomException(String message){
        super(ERROR_PREFIX + message);
    }
}
