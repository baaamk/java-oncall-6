package oncall.model;

import oncall.exception.InvalidNicknameException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WorkerTest {
    @Nested
    class SuccessTest {
        @DisplayName("올바른 닉네임 형식을 입력하면 Worker 객체를 생성한다")
        @Test
        void should_Return_Worker() {
            // given
            String nickname = "닉네임";

            // when & then
            assertThat(new Worker(nickname)).isNotNull()
                    .isInstanceOf(Worker.class);
        }
    }

    @Nested
    class ExceptionTest {
        @DisplayName("닉네임 길이가 5자를 초과하면 예외가 발생한다")
        @Test
        void should_ThrowException_WhenNicknameTooLong() {
            // given
            String nickname = "너무긴이름은안돼";

            // when & then
            assertThatThrownBy(() -> new Worker(nickname))
                    .isInstanceOf(InvalidNicknameException.class);
        }
    }
}
