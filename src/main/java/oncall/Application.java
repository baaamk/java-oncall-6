package oncall;

import oncall.controller.PlanManager;
import oncall.view.InputView;
import oncall.view.OutputView;

public class Application {
    public static void main(String[] args) {
        new PlanManager(new InputView(), new OutputView()).run();
    }
}
