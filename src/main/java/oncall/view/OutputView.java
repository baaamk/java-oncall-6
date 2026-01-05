package oncall.view;

import oncall.controller.dto.ScheduleDto;
import oncall.model.domain.vo.Day;
import oncall.model.domain.vo.Month;

import java.util.List;

public class OutputView {
    public void noticeInputMonth() {
        System.out.print("비상 근무를 배정할 월과 시작 요일을 입력하세요> ");
    }

    public void noticeInputDaysWorkers() {
        System.out.print("평일 비상 근무 순번대로 사원 닉네임을 입력하세요> ");
    }

    public void noticeInputHolidaysWorkers() {
        System.out.print("휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> ");
    }

    public void printResult(ScheduleDto scheduleDto) {
        Month month = scheduleDto.month();
        List<Day> days = scheduleDto.days();
        List<String> workers = scheduleDto.workers();

        for (int i = 1; i <= days.size(); i++) {
            Day day = days.get(i - 1);

            StringBuilder sb = new StringBuilder();
            sb.append(month.getMonth()).append("월 ")
                    .append(i).append("일 ")
                    .append(day.getWeek().getName());

            if (month.getHolidays().contains(i)) {
                sb.append("(휴일)");
            }

            sb.append(" ").append(workers.get(i - 1));

            System.out.println(sb);
        }
    }

    public void printError(String message) {
        System.out.println(message);
    }
}
