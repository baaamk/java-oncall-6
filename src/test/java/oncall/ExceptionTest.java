package oncall;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;

class ExceptionTest extends NsTest {
    private static final String ERROR_MESSAGE = "[ERROR]";

    @Test
    void 존재하지_않는_배정_월_예외() {
        assertSimpleTest(() -> {
            runException("0,금");
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @Test
    void 존재하지_않는_배정_요일_예외() {
        assertSimpleTest(() -> {
            runException("7,동");
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @Test
    void 배정하는_월과_요일_2개가_아닌_예외() {
        assertSimpleTest(() -> {
            runException("7,금,토");
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @Test
    void 평일_비상근무_사원_닉네임_중복_예외() {
        assertSimpleTest(() -> {
            runException("7,금", "수아,수아,글로,고니,도밥,준팍");
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @Test
    void 주말_비상근무_사원_닉네임_중복_예외() {
        assertSimpleTest(() -> {
            runException("7,금", "준팍,도밥,고니,수아,루루,글로", "수아,수아,글로,고니,도밥,준팍");
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @Test
    void 비상근무_사원_닉네임_1자_미만_예외() {
        assertSimpleTest(() -> {
            runException("7,금", "준팍,,고니,수아,루루,글로");
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @Test
    void 비상근무_사원_닉네임_5자_초과_예외() {
        assertSimpleTest(() -> {
            runException("7,금", "준팍,도밥도밥도밥,고니,수아,루루,글로");
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @Test
    void 비상근무_사원_5명_미만_예외() {
        List<String> staffs = IntStream.rangeClosed(1, 4)
                .mapToObj(Integer::toString)
                .toList();
        assertSimpleTest(() -> {
            runException("7,금", String.join(",", staffs));
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @Test
    void 비상근무_사원_35명_초과_예외() {
        List<String> staffs = IntStream.rangeClosed(1, 36)
                .mapToObj(Integer::toString)
                .toList();
        assertSimpleTest(() -> {
            runException("7,금", String.join(",", staffs));
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @Test
    void 비상근무_평일_사원과_주말_사원_불일치_예외() {
        assertSimpleTest(() -> {
            runException("7,금", "준팍,도밥,고니,수아,루루,글로", "글로,로로,수아,고니,도밥,준팍");
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
