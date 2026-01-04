package oncall.domain;

import oncall.model.domain.vo.Employee;
import oncall.exception.ErrorMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class EmployeeTest {
    @Test
    @DisplayName("이름이 정상적으로 입력되었다.")
    void 이름이_정상적으로_입력되었다() {
        Employee employee = Employee.from("우테");

        Assertions.assertDoesNotThrow(()-> employee);
    }

    @Test
    @DisplayName("이름에 한글 외의 문자가 있다.")
    void 이름에_한글_이외의_문자가_있다() {
        assertThatThrownBy(() -> Employee.from("우테!"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_FORMAT_NAME.getMessage());
    }

    @Test
    @DisplayName("이름에 한글 외의 문자가 있다.2")
    void 이름에_한글_이외의_문자가_있다2() {
        assertThatThrownBy(() -> Employee.from("dfjd"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_FORMAT_NAME.getMessage());
    }

    @Test
    @DisplayName("이름이 5글자 초과이다.")
    void 이름이_5글자_초과이다() {
        assertThatThrownBy(() -> Employee.from("안녕하세요이"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_RANGE_NAME.getMessage());
    }
}