package baseball.state;

import baseball.common.Constants;

/**
 * 프로그램 종료 상태
 *
 * @author suji
 * @date 2022-04-15
 **/
public class Exit implements GameState {

    @Override
    public String getState() {
        return Constants.STATE_EXIT;
    }

    @Override
    public String requestInput() {
        return null;
    }

    @Override
    public boolean validateInput(String inputNumbers) {
        return false;
    }

    @Override
    public GameState operateGame(String inputNumbers) {
        return null;
    }
}
