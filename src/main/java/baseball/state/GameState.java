package baseball.state;

/**
 * GameState
 *
 * @author suji
 * @date 2022-04-15
 **/
public interface GameState {

    String getState();

    String requestInput();

    boolean validateInput(String inputNumbers);

    GameState operateGame(String inputNumbers);
}
