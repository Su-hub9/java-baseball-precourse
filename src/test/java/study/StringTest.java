package study;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * String 클래스에 대한 학습 테스트
 *
 * @author suji
 * @date 2022-04-15
 **/
class StringTest {

    @DisplayName("요구사항 1-1: \"1,2\"을 ,로 split 했을 때 1과 2로 잘 분리되는지 확인")
    @Test
    void test_split_string_1_and_2() {
        //given
        String value = "1,2";

        //when
        String[] splitStr = value.split(",");

        //then
        assertThat(splitStr).containsExactly("1", "2");
    }

    @DisplayName("요구사항 1-2: \"1\"을 ,로 split 했을 때 1만을 포함하는 배열이 반환되는지 확인")
    @Test
    void test_split_string_1() {
        //given
        String value = "1";

        //when
        String[] splitStr = value.split(",");

        //then
        assertThat(splitStr).containsExactly("1");
    }

    @DisplayName("요구사항 2: \"(1,2)\" 값이 주어졌을 때 ()을 제거하고 \"1,2\"를 반환하는지 확인")
    @Test
    void test_substring_brace() {
        //given
        String value = "(1,2)";

        //when
        String substringValue = value.substring(1, value.length() - 1);

        //then
        assertThat(substringValue).isEqualTo("1,2");
    }

    @DisplayName("요구사항 3-1: \"abc\" 값이 주어졌을 때 특정 위치의 문자를 가져오는지 확인")
    @Test
    void test_charAt_abc() {
        //given
        String value = "abc";

        //when & then
        assertThat(value.charAt(0)).isEqualTo('a');
        assertThat(value.charAt(1)).isEqualTo('b');
        assertThat(value.charAt(2)).isEqualTo('c');
    }

    @DisplayName("요구사항 3-2: 특정 위치의 문자를 가져올 때 위치 값을 벗어나면 예외 발생하는지 확인")
    @Test
    void test_charAt_abc_StringIndexOutOfBoundsException() {
        //given
        String value = "abc";
        int outOfIndex = value.length();

        //when & then
        assertThatThrownBy(() -> {
            assertThat(value.charAt(outOfIndex)).isEqualTo('d');
        }).isInstanceOf(StringIndexOutOfBoundsException.class)
          .hasMessage("String index out of range: %s", outOfIndex);
    }
}
