package oncall.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SchedulerTest {
    @DisplayName("평일 순번에 맞게 비상 근무자가 배정된다")
    @Test
    void should_Assign_WeekdayWorkers() {
        // given
        Calendar calendar = Calendar.of(12, "월");
        Workers weekdayWorkers = Workers.from(
                List.of("준팍", "도밥", "고니", "수아", "루루")
        );
        Workers holidayWorkers = Workers.from(
                List.of("준팍", "도밥", "고니", "수아", "루루")
        );

        // when
        Scheduler scheduler = Scheduler.of(calendar, weekdayWorkers, holidayWorkers);

        // then
        assertThat(scheduler.getWorkerOnDuty(5).toString()).isEqualTo("루루");
    }

    @DisplayName("휴일 순번에 맞게 비상 근무자가 배정된다")
    @Test
    void should_Assign_HolidayWorkers() {
        // given
        Calendar calendar = Calendar.of(12, "월");
        Workers weekdayWorkers = Workers.from(
                List.of("준팍", "도밥", "고니", "수아", "루루")
        );
        Workers holidayWorkers = Workers.from(
                List.of("준팍", "도밥", "고니", "수아", "루루")
        );

        // when
        Scheduler scheduler = Scheduler.of(calendar, weekdayWorkers, holidayWorkers);

        // then
        assertThat(scheduler.getWorkerOnDuty(7).toString()).isEqualTo("도밥");
    }

    @DisplayName("순번상 특정 근무자가 연속 2일 근무하게 되는 상황에는, 다음 근무자와 순서를 바꿔 편성된다")
    @Test
    void should_Assign_DutyInARow() {
        // given
        Calendar calendar = Calendar.of(5, "월");
        Workers weekdayWorkers = Workers.from(
                List.of("준팍","도밥","고니","수아","루루","글로","솔로스타","우코","슬링키","참새","도리")
        );
        Workers holidayWorkers = Workers.from(
                List.of("수아","루루","글로","솔로스타","우코","슬링키","참새","도리","준팍","도밥","고니")
        );

        // when
        Scheduler scheduler = Scheduler.of(calendar, weekdayWorkers, holidayWorkers);

        // then
        assertThat(scheduler.getWorkerOnDuty(5).toString()).isEqualTo("루루");
    }
}
