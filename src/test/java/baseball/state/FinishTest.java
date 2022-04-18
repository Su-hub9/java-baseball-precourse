package baseball.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * FinishTest
 *
 * @author suji
 * @date 2022-04-15
 **/
class FinishTest {

    @DisplayName("사용자가 올바른 값 입력 시 유효성 체크")
    @Test
    void 올바른_입력값_유효성_체크() {
        //given
        String inputNumbers = "1";
        GameState gameState = new Finish();

        //when
        boolean isValid = gameState.validateInput(inputNumbers);

        //then
        assertThat(isValid).isTrue();
    }

    @DisplayName("사용자가 잘못된 값 입력 시 유효성 체크")
    @Test
    void 잘못된_입력값_유효성_체크() {
        //given
        String inputNumbers = "12";
        GameState gameState = new Finish();

        //when
        boolean isValid = gameState.validateInput(inputNumbers);

        //then
        assertThat(isValid).isFalse();
    }

    @DisplayName("게임 종료 후 재시작을 선택한 경우 Ongoing 상태 리턴")
    @Test
    void 게임_종료_후_재시작을_선택한_경우() {
        //given
        GameState gameState = new Finish();
        String inputNumbers = "1";

        //when
        GameState returnState = gameState.operateGame(inputNumbers);

        //then
        assertThat(returnState).isInstanceOf(Ongoing.class);
    }

    @DisplayName("게임 실행 후 프로그램 종료를 선택한 경우 Exit 상태 리턴")
    @Test
    void 게임_종료_후_프로그램_종료를_선택한_경우() {
        //given
        GameState gameState = new Finish();
        String inputNumbers = "2";

        //when
        GameState returnState = gameState.operateGame(inputNumbers);

        //then
        assertThat(returnState).isInstanceOf(Exit.class);
    }
}