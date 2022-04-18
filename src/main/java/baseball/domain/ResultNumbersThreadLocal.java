package baseball.domain;

/**
 * ResultNumbersThreadLocal
 *
 * @author suji
 * @date 2022-04-15
 **/
public class ResultNumbersThreadLocal {

    private static final ThreadLocal<ResultNumbers> threadLocalResultNumbers;

    static {
        threadLocalResultNumbers = new ThreadLocal<>();
    }

    public static ResultNumbers get() {
        return threadLocalResultNumbers.get();
    }

    public static void set(ResultNumbers resultNumbers) {
        threadLocalResultNumbers.set(resultNumbers);
    }

    public static void remove() {
        threadLocalResultNumbers.remove();
    }
}
