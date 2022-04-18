package baseball;

import baseball.service.BaseballGame;

public class Application {

    public static void main(String[] args) {
        // #. 숫자야구게임 시작
        BaseballGame baseballGame = new BaseballGame();
        baseballGame.start();
    }
}
