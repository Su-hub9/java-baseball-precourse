package baseball.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

/**
 * ResultNumbersTest
 *
 * @author suji
 * @date 2022-04-15
 **/
class ResultNumbersTest {

    @DisplayName("선택된 3자리 숫자가 지정된 범위 내의 숫자인지 확인")
    @Test
    void 선택된_3자리_숫자_범위_확인() {
        //given
        ResultNumbers resultNumbers = new ResultNumbers();

        //when
        List<Integer> numbers = resultNumbers.getNumbers();

        //then
        assertThat(numbers).allMatch(number -> number.toString().matches("[1-9]"));
    }

    @DisplayName("선택된 3자리 숫자 내 중복이 존재하는지 확인")
    @Test
    void 선택된_3자리_중복_확인() {
        //given
        ResultNumbers resultNumbers = new ResultNumbers();

        //when
        List<Integer> numbers = resultNumbers.getNumbers();
        Set<Integer> checkNumbers = new HashSet<>(numbers);

        //then
        assertThat(checkNumbers.size()).isEqualTo(numbers.size());
    }

}