package baseball.state;

import baseball.common.Constants;
import baseball.domain.ResultNumbersThreadLocal;
import baseball.view.InputProvider;
import baseball.view.OutputProvider;

/**
 * 게임 완료 상태
 *
 * @author suji
 * @date 2022-04-15
 **/
public class Finish implements GameState {

    @Override
    public String getState() {
        return Constants.STATE_FINISH;
    }

    @Override
    public String requestInput() {
        OutputProvider.printRestartOrExit();
        return InputProvider.inputNumbers();
    }

    @Override
    public boolean validateInput(String inputNumbers) {
        if (this.validateNumber(inputNumbers) && this.validateSize(inputNumbers)) {
            return true;
        }
        OutputProvider.printWrongInput();
        return false;
    }

    private boolean validateNumber(String inputNumbers) {
        return inputNumbers.matches("[1-2]");
    }

    private boolean validateSize(String inputNumbers) {
        return inputNumbers.length() == 1;
    }

    @Override
    public GameState operateGame(String inputNumbers) {
        if (Constants.GAME_RESTART.equals(inputNumbers)) {
            ResultNumbersThreadLocal.remove();
            return new Ongoing();
        }
        OutputProvider.printExit();
        return new Exit();
    }
}
