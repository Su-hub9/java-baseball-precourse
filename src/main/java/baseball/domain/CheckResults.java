package baseball.domain;

/**
 * CheckResults
 *
 * @author suji
 * @date 2022-04-16
 **/
public class CheckResults {
    private int strike;
    private int ball;

    /**
     * 게임 종료 여부
     * @return true: 3스트라이크
     */
    public boolean isFinish() {
        return strike == 3;
    }

    /**
     * 사용자가 입력한 숫자 판별
     * @param inputNumbers 사용자에게 입력 받은 3자리 숫자
     * @param checkNumber 체크 할 결과 숫자 1자리
     * @param checkIndex 체크 할 결과 숫자 Index
     */
    public void judgeNumbers(String inputNumbers, String checkNumber, int checkIndex) {
        if (inputNumbers.contains(checkNumber)) {
            this.increaseStrikeCount(inputNumbers, checkNumber, checkIndex);
            this.increaseBallCount(inputNumbers,checkNumber, checkIndex);
        }
    }

    private void increaseBallCount(String inputNumbers, String checkNumber, int checkIndex) {
        int index = inputNumbers.indexOf(checkNumber);
        if (index != checkIndex) {
            ++this.ball;
        }
    }

    private void increaseStrikeCount(String inputNumbers, String checkNumber, int checkIndex) {
        int index = inputNumbers.indexOf(checkNumber);
        if (index == checkIndex) {
            ++this.strike;
        }
    }

    @Override
    public String toString() {
        String result = "";
        if (ball > 0) result += String.format("%d볼", ball);
        if (result.length() > 0) result += " ";
        if (strike > 0) result += String.format("%d스트라이크", strike);
        if (result.length() == 0) result = "낫싱";
        return result;
    }
}
