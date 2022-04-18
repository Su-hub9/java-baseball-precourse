package baseball.service;

import baseball.common.Constants;
import baseball.domain.ResultNumbers;
import baseball.domain.ResultNumbersThreadLocal;
import baseball.state.GameState;
import baseball.state.Ongoing;

/**
 * 숫자야구게임
 *
 * @author suji
 * @date 2022-04-15
 **/
public class BaseballGame {

    /* 게임의 상태 */
    private GameState gameState;

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public void start() {
        if (this.gameState == null) {
            this.setGameState(new Ongoing());
        }
        while (this.gameState.getState() != Constants.STATE_EXIT) {
            this.play();
        }
    }

    private void play() {
        if (ResultNumbersThreadLocal.get() == null) {
            ResultNumbersThreadLocal.set(new ResultNumbers());
        }

        String inputNumbers = this.gameState.requestInput();
        if (this.gameState.validateInput(inputNumbers)) {
            this.setGameState(this.gameState.operateGame(inputNumbers));
        }
    }
}
