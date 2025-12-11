package oncall.view;

import oncall.model.Calendar;
import oncall.model.DayOfWeekKOR;
import oncall.model.Scheduler;

/**
 * 프로그램의 모든 출력을 담당하는 클래스
 */
public class OutputView {
    public void printErrorMessage() {
        System.out.println("[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요.");
    }

    public void printCalendarPropertyPrompt() {
        System.out.print("비상 근무를 배정할 월과 시작 요일을 입력하세요> ");
    };

    public void printWeekdayWorkerNamesPrompt() {
        System.out.print("평일 비상 근무 순번대로 사원 닉네임을 입력하세요> ");
    };

    public void printHolidayWorkerNamesPrompt() {
        System.out.print("휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> ");
    };

    public void printWorkSchedules(Calendar calendar, Scheduler scheduler) {
        int month = calendar.getMonth();
        for (int i = 1; i <= calendar.getLastDateOfMonth(); i++) {
            DayOfWeekKOR dayOfWeek = calendar.getDayOfMonth(i);
            String day = dayOfWeek.toString();
            if (!dayOfWeek.isHoliday() && calendar.isHoliday(i)) {
                day += "(휴일)";
            }
            String nickname = scheduler.getWorkerOnDuty(i).toString();
            System.out.printf("%d월 %d일 %s %s", month, i, day, nickname);
            System.out.println();
        }
    };
}
