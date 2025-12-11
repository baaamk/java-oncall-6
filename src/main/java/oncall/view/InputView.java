package oncall.view;

import camp.nextstep.edu.missionutils.Console;

/**
 * 프로그램의 모든 입력을 담당하는 클래스
 */
public class InputView {
    public String readCalendarProperty() {
        return Console.readLine();
    }

    public String readWeekdayWorkersPrompt() {
        return Console.readLine();
    }

    public String readHolidayWorkersPrompt() {
        return Console.readLine();
    }
}
