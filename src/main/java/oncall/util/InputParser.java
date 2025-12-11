package oncall.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 사용자 입력을 변환, 검증하는 유틸리티 클래스
 */
public final class InputParser {
    private InputParser() {}

    public static String refineInput(String input) {
        boolean isNullOrBlank = (input == null) || input.isBlank();
        if (isNullOrBlank) {
            throw new IllegalArgumentException();
        }
        return input.trim();
    }

    public static List<String> parseToStrings(String input) {
        String refinedInput = refineInput(input);
        List<String> strings = new ArrayList<>();
        for (String string : refinedInput.split(",")) {
            strings.add(refineInput(string));
        }
        return strings;
    }

    public static int parseToInt(String input) {
        String refinedInput = refineInput(input);
        try {
            return Integer.parseInt(refinedInput);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }
}