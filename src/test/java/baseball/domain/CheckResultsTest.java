package baseball.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * CheckResultsTest
 *
 * @author suji
 * @date 2022-04-15
 **/
class CheckResultsTest {

    @DisplayName("사용자에게 입력 받은 숫자 판별")
    @Test
    void 입력_숫자_판별() {
        //given
        String inputNumbers = "985";
        String[] resultNumbers = "589".split("");
        String expectedResult = "2볼 1스트라이크";

        //when
        CheckResults checkResults = new CheckResults();
        for (int i = 0; i < resultNumbers.length; i++) {
            checkResults.judgeNumbers(inputNumbers, resultNumbers[i], i);
        }

        //then
        assertThat(checkResults.toString()).isEqualTo(expectedResult);
    }

    @DisplayName("사용자에게 입력 받은 숫자가 3스트라이크 일 경우 게임 종료 여부 확인")
    @Test
    void 게임_종료_확인() {
        //given
        String inputNumbers = "589";
        String[] resultNumbers = "589".split("");

        //when
        CheckResults checkResults = new CheckResults();
        for (int i = 0; i < resultNumbers.length; i++) {
            checkResults.judgeNumbers(inputNumbers, resultNumbers[i], i);
        }

        //then
        assertThat(checkResults.isFinish()).isTrue();
    }

    @DisplayName("사용자에게 입력 받은 숫자가 3스트라이크가 아닐 경우 게임 종료 여부 확인")
    @Test
    void 게임_종료되지_않았을_경우() {
        //given
        String inputNumbers = "985";
        String[] resultNumbers = "589".split("");

        //when
        CheckResults checkResults = new CheckResults();
        for (int i = 0; i < resultNumbers.length; i++) {
            checkResults.judgeNumbers(inputNumbers, resultNumbers[i], i);
        }

        //then
        assertThat(checkResults.isFinish()).isFalse();
    }
}