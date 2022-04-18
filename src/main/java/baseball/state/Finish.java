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
        // #. 사용자 입력값에 대한 숫자 범위 및 사이즈 체크
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
        // #. 게임 재시작을 선택한 경우 결과 숫자 초기화 및 Ongoing 상태 리턴
        if (Constants.GAME_RESTART.equals(inputNumbers)) {
            ResultNumbersThreadLocal.remove();
            return new Ongoing();
        }

        // #. 나머지 경우 프로그램 종료
        OutputProvider.printExit();
        return new Exit();
    }
}
