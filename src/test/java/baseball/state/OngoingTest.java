package baseball.state;

import baseball.common.Message;
import baseball.domain.ResultNumbers;
import baseball.domain.ResultNumbersThreadLocal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * OngoingTest
 *
 * @author suji
 * @date 2022-04-15
 **/
class OngoingTest {

    String resultNumbers;

    @BeforeEach
    void generateResultNumbers() {
        ResultNumbers resultNumbers = new ResultNumbers();
        ResultNumbersThreadLocal.set(resultNumbers);

        StringBuilder inputNumbers = new StringBuilder();
        for (Integer number : resultNumbers.getNumbers()) {
            inputNumbers.append(number);
        }
        this.resultNumbers = inputNumbers.toString();
    }

    @AfterEach
    void removeResultNumbers() {
        ResultNumbersThreadLocal.remove();
        this.resultNumbers = null;
    }

    @DisplayName("사용자가 올바른 값 입력 시 유효성 체크")
    @Test
    void 올바른_입력값_유효성_체크() {
        //given
        String inputNumbers = "123";
        GameState gameState = new Ongoing();

        //when
        boolean isValid = gameState.validateInput(inputNumbers);

        //then
        assertThat(isValid).isTrue();
    }

    @DisplayName("사용자가 잘못된 값 입력 시 유효성 체크 후 IllegalArgumentException 발생")
    @Test
    void 잘못된_입력값_유효성_체크_후_예외_발생() {
        //given
        String inputNumbers = "abc";
        GameState gameState = new Ongoing();

        //when & then
        assertThatThrownBy(() -> {
            gameState.validateInput(inputNumbers);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Message.ERROR_WRONG_INPUT);
    }

    @DisplayName("사용자가 중복된 값 입력 시 유효성 체크")
    @Test
    void 중복된_입력값_유효성_체크() {
        //given
        String inputNumbers = "122";
        GameState gameState = new Ongoing();

        //when
        boolean isValid = gameState.validateInput(inputNumbers);

        //then
        assertThat(isValid).isFalse();
    }

    @DisplayName("게임 실행 후 사용자가 정답을 입력하고 Finish 상태 리턴")
    @Test
    void 게임_실행_후_종료된_상태() {
        //given
        GameState gameState = new Ongoing();

        //when
        GameState returnState = gameState.operateGame(this.resultNumbers);

        //then
        assertThat(returnState).isInstanceOf(Finish.class);
    }

    @DisplayName("게임 실행 후 입력값이 정답과 일치하지 않아 Ongoing 상태 리턴")
    @Test
    void 게임_실행_후_계속_진행중인_상태() {
        //given
        GameState gameState = new Ongoing();
        String inputNumbers = new StringBuilder(this.resultNumbers).reverse().toString();

        //when
        GameState returnState = gameState.operateGame(inputNumbers);

        //then
        assertThat(returnState).isInstanceOf(Ongoing.class);
    }
}