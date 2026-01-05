package oncall.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public String inputMonth() {
        return getReadLine();
    }

    public String inputDaysWorkers() {
        return getReadLine();
    }

    public String inputHolidaysWorkers() {
        return getReadLine();
    }

    private static String getReadLine() {
        return Console.readLine();
    }
}
