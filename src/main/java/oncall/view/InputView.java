package oncall.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public String readPlanStart() {
        String message = "비상 근무를 배정할 월과 시작 요일을 입력하세요> ";
        return prompt(message);
    }

    public String readWeekdayStaffs() {
        String message = "평일 비상 근무 순번대로 사원 닉네임을 입력하세요> ";
        return prompt(message);
    }

    public String readWeekendStaffs() {
        String message = "휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> ";
        return prompt(message);
    }

    private String prompt(String message) {
        System.out.printf(message);
        return Console.readLine();
    }
}
