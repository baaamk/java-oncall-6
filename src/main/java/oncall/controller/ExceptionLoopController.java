package oncall.controller;

import java.util.function.Supplier;
import oncall.exception.CustomException;

public abstract class ExceptionLoopController {
    protected <T> T repeatUntilValid(Supplier<T> function) {
        while(true) {
            try {
                return function.get();
            } catch (CustomException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
