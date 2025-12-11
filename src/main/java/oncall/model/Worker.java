package oncall.model;

import oncall.exception.InvalidNicknameException;
import oncall.util.InputParser;

/**
 * 근무자 정보를 저장하는 클래스
 */
public class Worker {
    private final String nickname;

    public Worker(String nickname) {
        validate(nickname);
        this.nickname = nickname;
    }

    private void validate(String nickname) {
        if (nickname.length() > 5) {
            throw new InvalidNicknameException();
        }
    }

    @Override
    public String toString() {
        return nickname;
    }

    public boolean isSame(Worker worker) {
        return this.nickname.equals(worker.nickname);
    }
}
