package baseball.view;

import baseball.common.Message;
import baseball.domain.CheckResults;

import java.util.Objects;

/**
 * 화면 출력 데이터 제공자
 *
 * @author suji
 * @date 2022-04-15
 **/
public class OutputProvider {

    public static void printInputNumbers() {
        System.out.print(Message.REQUEST_INPUT_NUMBERS);
    }

    public static void printCheckResults(CheckResults checkResults) {
        if (Objects.isNull(checkResults)) {
            throw new IllegalArgumentException(Message.ERROR_CHECK_RESULTS);
        }
        System.out.println(checkResults.toString());
    }

    public static void printFinishGame() {
        System.out.println(Message.FINISH_GAME);
    }

    public static void printRestartOrExit() {
        System.out.println(Message.REQUEST_RESTART_OR_EXIT);
    }

    public static void printExit() {
        System.out.print(Message.EXIT_GAME);
    }

    public static void printDuplication() {
        System.out.println(Message.ERROR_INPUT_DUPLICATION);
    }

    public static void printWrongInput() {
        System.out.println(Message.ERROR_WRONG_INPUT);
    }

    public static void printIllegalArgumentException() {
        printWrongInput();
        printExit();
    }
}
