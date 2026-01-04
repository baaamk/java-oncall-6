package oncall.model.domain.vo;

import oncall.exception.ErrorMessage;

import java.util.Objects;
import java.util.regex.Pattern;

public class Employee {
    private static final Pattern REGEXP_KOR = Pattern.compile("^[가-힣]*$");
    private static final int MAX_NAME_LENGTH = 5;

    private final String name;

    private Employee(String name) {
        this.name = name;
    }

    public static Employee from(String name) {
        validateName(name);
        return new Employee(name);
    }

    public String getName() {
        return name;
    }

    private static void validateName(String name) {
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_RANGE_NAME.getMessage());
        }

        if (!REGEXP_KOR.matcher(name).matches()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_FORMAT_NAME.getMessage());
        }

    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
