package application.console;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import domain.GameId;
import domain.Player;

class ConsoleInputTranslaterTest {

    ConsoleInputTranslater translater = new ConsoleInputTranslater();

    @Test
    void translateScoreCommand() {
        String inputString = "g1 a";

        ScoreConsoleInput input = translater.translateScoreInput(inputString);

        assertThat(input.isComplete()).isTrue();
        assertThat(input).isEqualTo(ScoreConsoleInput.create(new GameId("1"), Player.A));

    }

    @Test
    void translateScoreUppercase() {
        String inputString = "G2 B";

        ScoreConsoleInput input = translater.translateScoreInput(inputString);

        assertThat(input.isComplete()).isTrue();
        assertThat(input).isEqualTo(ScoreConsoleInput.create(new GameId("2"), Player.B));
    }

    @Test
    void translateScoreWithTextGameId() {
        String inputString = "Gtext B";

        ScoreConsoleInput input = translater.translateScoreInput(inputString);

        assertThat(input.isComplete()).isFalse();
    }

    @Test
    void translateScoreWithNonExistingPlayer() {
        String inputString = "G1 piet";

        ScoreConsoleInput input = translater.translateScoreInput(inputString);

        assertThat(input.isComplete()).isFalse();
    }

    @Test
    void translateCreateGameWithoutPlayers() {
        String inputString = "CG";

        CreateGameConsoleInput input = translater.translateCreateGameInput(inputString);

        assertThat(input.isComplete()).isFalse();
    }

    @Test
    void translateCreateGameWithPlayerAAndB() {
        String inputString = "cg a b";

        CreateGameConsoleInput input = translater.translateCreateGameInput(inputString);

        assertThat(input.isComplete()).isTrue();
        assertThat(input.firstPlayer).isEqualTo(Player.A);
        assertThat(input.secondPlayer).isEqualTo(Player.B);
    }

}