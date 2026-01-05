package oncall.config;

import oncall.controller.Controller;
import oncall.view.InputView;
import oncall.view.OutputView;

public class AppConfig {
    public Controller controller() {
        return new Controller(inputView(), outputView());
    }

    private InputView inputView() {
        return new InputView();
    }

    private OutputView outputView() {
        return new OutputView();
    }

}
