package oncall.model.domain;

import oncall.exception.ErrorMessage;
import oncall.model.domain.vo.Employee;

import java.util.List;
import java.util.Objects;


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
    }

    private static boolean isDuplicate(List<String> employees) {
        return employees.size() != employees.stream().distinct().toList().size();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Employees employees1 = (Employees) o;
        return Objects.equals(employees, employees1.employees);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(employees);
    }
}
