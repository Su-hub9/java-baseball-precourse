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
        this.validateNumber(inputNumbers);
        this.validateSize(inputNumbers);
        return this.validateDuplication(inputNumbers);
    }

    private void validateNumber(String inputNumbers) {
        if (!inputNumbers.matches("[1-9]+")) {
            OutputProvider.printIllegalArgumentException();
            throw new IllegalArgumentException(Message.ERROR_WRONG_INPUT);
        }
    }

    private void validateSize(String inputNumbers) {
        if (inputNumbers.length() != Constants.NUMBER_SIZE) {
            OutputProvider.printIllegalArgumentException();
            throw new IllegalArgumentException(Message.ERROR_WRONG_INPUT);
        }
    }

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
        ResultNumbers resultNumbers = ResultNumbersThreadLocal.get();
        List<Integer> checkNumbers = resultNumbers.getNumbers();
        CheckResults checkResults = this.judgeNumber(inputNumbers, checkNumbers);
        OutputProvider.printCheckResults(checkResults);
        return this.checkGameResult(checkResults);
    }

    private CheckResults judgeNumber(String inputNumbers, List<Integer> checkNumbers) {
        CheckResults checkResults = new CheckResults();
        for (int i = 0; i < checkNumbers.size(); i++) {
            String checkNumber = checkNumbers.get(i).toString();
            checkResults.judgeNumbers(inputNumbers, checkNumber, i);
        }
        return checkResults;
    }

    private GameState checkGameResult(CheckResults checkResults) {
        if (checkResults.isFinish()) {
            OutputProvider.printFinishGame();
            return new Finish();
        }
        return new Ongoing();
    }
}
