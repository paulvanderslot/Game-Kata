package infrastructure.console;

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
        assertThat(input).isEqualTo(ScoreConsoleInput.create(new GameId("1"), new Player("A")));

    }

    @Test
    void translateScoreUppercase() {
        String inputString = "G2 B";

        ScoreConsoleInput input = translater.translateScoreInput(inputString);

        assertThat(input.isComplete()).isTrue();
        assertThat(input).isEqualTo(ScoreConsoleInput.create(new GameId("2"), new Player("B")));
    }

    @Test
    void translateScoreWithTextGameId() {
        String inputString = "Gtext B";

        ScoreConsoleInput input = translater.translateScoreInput(inputString);

        assertThat(input.isComplete()).isFalse();
    }

    @Test
    void translateScoreWithNamedPlayer() {
        String inputString = "G1 piet";

        ScoreConsoleInput input = translater.translateScoreInput(inputString);

        assertThat(input.isComplete()).isTrue();
        assertThat(input.player).isEqualTo(new Player("Piet"));
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
        assertThat(input.firstPlayer).isEqualTo(new Player("A"));
        assertThat(input.secondPlayer).isEqualTo(new Player("B"));
    }

}