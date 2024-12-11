package oncall;

import oncall.controller.CustomController;
import oncall.service.CustomService;
import oncall.view.InputView;
import oncall.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        CustomService customService = new CustomService();

        CustomController controller = new CustomController(inputView, outputView, customService);
        controller.run();
    }
}
