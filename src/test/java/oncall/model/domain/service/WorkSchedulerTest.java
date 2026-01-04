package oncall.model.domain.service;

import oncall.model.domain.Employees;
import oncall.model.domain.vo.Day;
import oncall.model.domain.vo.Month;
import oncall.model.domain.vo.Week;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WorkSchedulerTest {

    @Test
    @DisplayName("근무자는 최소 5명, 최대 35명 규칙을 만족해야 한다")
    void employeesCountValidation() {
        assertThrows(
                IllegalArgumentException.class,
                () -> Employees.of(List.of("가","나","다","라"))
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> Employees.of(
                        List.of(
                                "가","나","다","라","마","바","사","아","자","차",
                                "카","타","파","하","거","너","더","러","머","버",
                                "서","어","저","처","커","터","퍼","허","고","노",
                                "도","로","모","보","소","오"
                        ) // 36명
                )
        );

        assertDoesNotThrow(() -> Employees.of(List.of("가","나","다","라","마")));
        assertDoesNotThrow(() -> Employees.of(
                List.of(
                        "가","나","다","라","마","바","사","아","자","차",
                        "카","타","파","하","거","너","더","러","머","버",
                        "서","어","저","처","커","터","퍼","허","고","노",
                        "도","로","모","보","소"
                ) // 35명
        ));
    }

    @Test
    @DisplayName("주말과 법정공휴일에는 휴일 근무자 풀이 사용된다")
    void usesHolidayPoolForWeekendAndLegalHoliday() {
        // given
        Month month = Month.OCTOBER;      // 3, 9 공휴일
        Week startWeek = Week.MONDAY;     // 1일=월

        List<Day> days = MonthGenerator.generateMonth(month, startWeek);

        Employees weekdayWorkers = Employees.of(List.of("가","나","다","라","마"));
        Employees holidayWorkers = Employees.of(List.of("바","사","아","자","차"));

        // when
        List<String> assigned = WorkScheduler.workScheduleGenerator(
                days, month, weekdayWorkers, holidayWorkers
        );

        // then
        // 10/3, 10/6, 10/7, 10/9 는 휴일
        assertTrue(isFromHolidayPool(assigned.get(3 - 1), holidayWorkers));
        assertTrue(isFromHolidayPool(assigned.get(6 - 1), holidayWorkers));
        assertTrue(isFromHolidayPool(assigned.get(7 - 1), holidayWorkers));
        assertTrue(isFromHolidayPool(assigned.get(9 - 1), holidayWorkers));
    }

    @Test
    @DisplayName("연속 근무 방지: 직전 근무자와 같으면 다음 사람으로 스킵된다")
    void preventsConsecutiveAssignment() {
        // given
        Month month = Month.JANUARY;
        Week startWeek = Week.FRIDAY; // 1일=금, 2일=토(휴일)

        List<Day> days = MonthGenerator.generateMonth(month, startWeek);

        Employees weekdayWorkers = Employees.of(List.of("가","나","다","라","마"));
        Employees holidayWorkers = Employees.of(List.of("가","바","사","아","자"));

        // when
        List<String> assigned = WorkScheduler.workScheduleGenerator(
                days, month, weekdayWorkers, holidayWorkers
        );

        // then
        assertEquals("가", assigned.get(0));       // 1일
        assertNotEquals("가", assigned.get(1));    // 2일 (연속 방지)
    }


    private boolean isFromHolidayPool(String name, Employees holidayWorkers) {
        for (int i = 0; i < holidayWorkers.size(); i++) {
            if (holidayWorkers.get(i).getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}