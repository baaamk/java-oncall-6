package oncall.model.domain;

import oncall.exception.ErrorMessage;
import oncall.model.domain.vo.Employee;

import java.util.List;


public class Employees {
    private final List<Employee> employees;

    private Employees(List<Employee> employees) {
        this.employees = employees;
    }

    public static Employees of(List<String> employees) {
        validateEmployees(employees);
        return new Employees(
                employees.stream()
                        .map(Employee::from)
                        .toList()
        );
    }

    private static void validateEmployees(List<String> employees) {
        if (isDuplicate(employees)){
            throw new IllegalArgumentException(ErrorMessage.DUPLICATE_EMPLOYEE.getMessage());
        }
        if (employees.size() < 5 || employees.size() < 35) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_EMPLOY_RANGE.getMessage());
        }
    }

    private static boolean isDuplicate(List<String> employees) {
        return employees.size() != employees.stream().distinct().count();
    }

    public int size() {
        return employees.size();
    }

    public Employee get(int index) {
        return employees.get(index);
    }

}
