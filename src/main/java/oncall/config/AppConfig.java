package oncall.config;

import oncall.controller.Controller;
import oncall.view.InputView;
import oncall.view.OutputView;

/**
 * 애플리케이션의 실행에 필요한 모든 객체를 생성하고 서로 연결하는 설정 클래스
 */
public class AppConfig {
    private static class LazyHolder {
        public static final AppConfig INSTANCE = new AppConfig();

        public static final InputView INPUT_VIEW = new InputView();
        public static final OutputView OUTPUT_VIEW = new OutputView();

        public static final Controller CONTROLLER = new Controller(INPUT_VIEW, OUTPUT_VIEW);
    }

    private AppConfig() {}

    public static AppConfig getInstance() {
        return LazyHolder.INSTANCE;
    }

    public Controller controller() {
        return LazyHolder.CONTROLLER;
    }
}