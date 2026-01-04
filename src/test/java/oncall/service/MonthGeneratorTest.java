package oncall.service;

import oncall.domain.vo.Day;
import oncall.domain.vo.Month;
import oncall.domain.vo.Week;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MonthGeneratorTest {

    @Test
    void 월의_시작요일을_기준으로_Day_리스트를_생성한다() {
        // given
        MonthGenerator generator = new MonthGenerator();
        Month month = Month.MARCH;        // 31일

        Week startWeek = Week.MONDAY;     // 1일이 월요일

        // when
        List<Day> days = generator.generateMonth(month, startWeek);

        // then
        assertThat(days).hasSize(31);

        assertThat(days.get(0).getDate()).isEqualTo(1);
        assertThat(days.get(0).getWeek()).isEqualTo(Week.MONDAY);

        assertThat(days.get(6).getDate()).isEqualTo(7);
        assertThat(days.get(6).getWeek()).isEqualTo(Week.SUNDAY);

        assertThat(days.get(7).getDate()).isEqualTo(8);
        assertThat(days.get(7).getWeek()).isEqualTo(Week.MONDAY);
    }
}