package oncall;

import oncall.config.AppConfig;
import oncall.controller.Controller;

/**
 * 프로그램 진입점을 담당하는 클래스
 */
public class Application {
    public static void main(String[] args) {
        AppConfig appConfig = AppConfig.getInstance();

        Controller controller = appConfig.controller();
        controller.run();
    }
}