package baseball.state;

import baseball.common.Constants;
import baseball.common.Message;
import baseball.domain.CheckResults;
import baseball.domain.ResultNumbers;
import baseball.domain.ResultNumbersThreadLocal;
import baseball.view.InputProvider;
import baseball.view.OutputProvider;

import java.util.*;

/**
 * 게임 진행중 상태
 *
 * @author suji
 * @date 2022-04-15
 **/
public class Ongoing implements GameState {

    @Override
    public String getState() {
        return Constants.STATE_ONGOING;
    }

    @Override
    public String requestInput() {
        OutputProvider.printInputNumbers();
        return InputProvider.inputNumbers();
    }

    @Override
    public boolean validateInput(String inputNumbers) {
        // #. 사용자 입력값에 대한 숫자 범위 및 사이즈 체크
        this.validateNumber(inputNumbers);
        this.validateSize(inputNumbers);

        // #. 사용자 입력값 중복 유효성 체크
        return this.validateDuplication(inputNumbers);
    }

    /**
     * 입력값에 대한 숫자 범위 체크, 맞지 않을 경우 IllegalArgumentException 발생
     * @param inputNumbers 입력값
     */
    private void validateNumber(String inputNumbers) {
        if (!inputNumbers.matches("[1-9]+")) {
            OutputProvider.printIllegalArgumentException();
            throw new IllegalArgumentException(Message.ERROR_WRONG_INPUT);
        }
    }

    /**
     * 입력값에 대한 숫자 길이 체크, 맞지 않을 경우 IllegalArgumentException 발생
     * @param inputNumbers 입력값
     */
    private void validateSize(String inputNumbers) {
        if (inputNumbers.length() != Constants.NUMBER_SIZE) {
            OutputProvider.printIllegalArgumentException();
            throw new IllegalArgumentException(Message.ERROR_WRONG_INPUT);
        }
    }

    /**
     * 입력값에 대한 중복 유효성 체크
     * @param inputNumbers 입력값
     * @return true: 중복 없음, false: 중복 존재
     */
    private boolean validateDuplication(String inputNumbers) {
        Set<String> numbers = new HashSet<>(Arrays.asList(inputNumbers.split("")));
        if (numbers.size() != Constants.NUMBER_SIZE) {
            OutputProvider.printDuplication();
            return false;
        }
        return true;
    }

    @Override
    public GameState operateGame(String inputNumbers) {
        // #. 선택된 결과 숫자 가져옥기
        ResultNumbers resultNumbers = ResultNumbersThreadLocal.get();
        List<Integer> checkNumbers = resultNumbers.getNumbers();

        // #. 판별 후 결과 객체
        CheckResults checkResults = this.judgeNumber(inputNumbers, checkNumbers);
        OutputProvider.printCheckResults(checkResults);
        return this.checkGameResult(checkResults);
    }

    /**
     * 입력값과 결과 숫자 판별
     * @param inputNumbers 입력값
     * @param checkNumbers 체크할 결과 숫자
     * @return 판별 결과
     */
    private CheckResults judgeNumber(String inputNumbers, List<Integer> checkNumbers) {
        CheckResults checkResults = new CheckResults();
        for (int i = 0; i < checkNumbers.size(); i++) {
            String checkNumber = checkNumbers.get(i).toString();
            checkResults.judgeNumbers(inputNumbers, checkNumber, i);
        }
        return checkResults;
    }

    /**
     * 게임 종료 여부 체크
     * @param checkResults 판별 결과 객체
     * @return 체크 후 게임 상태
     */
    private GameState checkGameResult(CheckResults checkResults) {
        if (checkResults.isFinish()) {
            OutputProvider.printFinishGame();
            return new Finish();
        }
        return new Ongoing();
    }
}
