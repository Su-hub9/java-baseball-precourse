package baseball.domain;

import baseball.common.Constants;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 상대방(컴퓨터)이 선택한 서로 다른 임의의 3자리 결과 숫자
 *
 * @author suji
 * @date 2022-04-15
 **/
public class ResultNumbers {
    List<Integer> numbers;

    public ResultNumbers() {
        Set<Integer> uniqueNumbers = new HashSet<>();
        while (uniqueNumbers.size() < Constants.NUMBER_SIZE) {
            uniqueNumbers.add(Randoms.pickNumberInRange(Constants.RANGE_START, Constants.RANGE_END));
        }
        numbers = new ArrayList<>(uniqueNumbers);
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
