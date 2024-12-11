package oncall.view;

import java.util.List;
import oncall.domain.MonthlyRoster;

public class OutputView {
    public void printGetMonthDayOfWeek() {
        System.out.print("비상 근무를 배정할 월과 시작 요일을 입력하세요> ");
    }

    public void printGetWeekdayRoster() {
        System.out.print("평일 비상 근무 순번대로 사원 닉네임을 입력하세요> ");
    }

    public void printGetWeekendRoster() {
        System.out.print("휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> ");
    }

    public void printMonthlyRoster(List<MonthlyRoster> monthlyRoster) {
        for (MonthlyRoster roster : monthlyRoster) {
            System.out.println(roster.toString());
        }
    }
}
