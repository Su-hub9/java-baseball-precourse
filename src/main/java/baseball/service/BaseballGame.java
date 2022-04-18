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
        // #. 게임 시작 시 기본 상태 설정
        if (this.gameState == null) {
            this.setGameState(new Ongoing());
        }

        // #. 게임 상태가 Exit 이 아닌 경우 계속 진행
        while (this.gameState.getState() != Constants.STATE_EXIT) {
            this.play();
        }
    }

    private void play() {
        // #. 결과 숫자가 없으면 생성
        if (ResultNumbersThreadLocal.get() == null) {
            ResultNumbersThreadLocal.set(new ResultNumbers());
        }

        // #. 사용자 입력 요청
        String inputNumbers = this.gameState.requestInput();

        // #. 입력값 유효성 체크 후 게임 실행, 실행 결과에 따라 게임 상태 업데이트
        if (this.gameState.validateInput(inputNumbers)) {
            this.setGameState(this.gameState.operateGame(inputNumbers));
        }
    }
}
