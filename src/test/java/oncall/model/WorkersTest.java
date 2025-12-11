package oncall.model;

import oncall.exception.DuplicatedNicknameException;
import oncall.exception.WorkersLessThanMinimumException;
import oncall.exception.WorkersOverMaximum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WorkersTest {
    @Nested
    class SuccessTest {
        @DisplayName("유효한 조건의 근무자 목록을 입력 받으면 Workers 객체를 생성한다")
        @Test
        void should_Return_Workers() {
            // given
            List<String> workers = List.of("하나", "둘", "셋", "넷", "다섯");

            // when & then
            assertThat(Workers.from(workers)).isNotNull()
                    .isInstanceOf(Workers.class);
        }
    }

    @Nested
    class ExceptionTest {
        @DisplayName("근무자의 수가 5명 미만일 경우 예외가 발생한다")
        @Test
        void should_ThrowException_WhenWorkersLessThanMinimum() {
            // given
            List<String> workers = List.of("하나", "둘", "셋");

            // when & then
            assertThatThrownBy(() -> Workers.from(workers))
                    .isInstanceOf(WorkersLessThanMinimumException.class);
        }

        @DisplayName("근무자의 수가 35명을 초과할 경우 예외가 발생한다")
        @Test
        void should_ThrowException_WhenWorkersOverMaximum() {
            // given
            List<String> workers = List.of(
                    "1", "2", "3", "4", "5",
                    "6", "7", "8", "9", "10",
                    "11", "12", "13", "14", "15",
                    "16", "17", "18", "19", "20",
                    "21", "22", "23", "24", "25",
                    "26", "27", "28", "29", "30",
                    "31", "32", "33", "34", "35",
                    "36"
                    );

            // when & then
            assertThatThrownBy(() -> Workers.from(workers))
                    .isInstanceOf(WorkersOverMaximum.class);
        }

        @DisplayName("근무자의 닉네임이 중복되는 경우 예외가 발생한다")
        @Test
        void should_ThrowException_ForDuplicatedNicknames() {
            // given
            List<String> workers = List.of("하나", "둘", "셋", "넷", "다섯", "여섯", "하나");

            // when & then
            assertThatThrownBy(() -> Workers.from(workers))
                    .isInstanceOf(DuplicatedNicknameException.class);
        }
    }
}
