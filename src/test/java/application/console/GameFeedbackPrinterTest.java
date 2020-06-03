package application.console;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import domain.GameId;
import domain.Player;
import domain.Score;

class GameFeedbackPrinterTest {

    GameFeedbackPrinter printer = new GameFeedbackPrinter();

    @Test
    void printScorePlayerBScores() {
        Score score = new Score(1, 2, Player.B);

        String result = printer.print(new GameId("id"), score);

        Assertions.assertThat(result).isEqualTo("Player B scores in game id. Score [1-2*]");
    }

    @Test
    void printScorePlayerAScores() {
        Score score = new Score(3, 1, Player.A);

        String result = printer.print(new GameId("id"), score);

        Assertions.assertThat(result).isEqualTo("Player A scores in game id. Score [3*-1]");
    }

    @Test
    void printWinner() {
        String result = printer.printWinner(new GameId("id"), Player.A);

        Assertions.assertThat(result).isEqualTo("Player A wins game id");
    }

    @Test
    void printGameIsFinished() {
        String result = printer.printGameIsFinished(new GameId("id"));

        Assertions.assertThat(result).isEqualTo("No more scoring possible for game id. The game is finished.");
    }
}