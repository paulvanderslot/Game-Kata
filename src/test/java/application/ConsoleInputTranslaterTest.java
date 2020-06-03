package application;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import application.console.ConsoleInput;
import application.console.ConsoleInputTranslater;
import domain.GameId;
import domain.Player;

class ConsoleInputTranslaterTest {
    private ConsoleInputTranslater consoleInputTranslater = new ConsoleInputTranslater();

    @Test
    void translatePlayerALowercase() {
        String inputString = createInputString("1", "a");

        Optional<ConsoleInput> consoleInput = consoleInputTranslater.translate(inputString);

        assertThat(consoleInput).isPresent();
        assertThat(consoleInput.get()).isEqualTo(ConsoleInput.create(new GameId("1"), Player.A));
    }

    @Test
    void translatePlayerBUppercase() {
        String inputString = createInputString("1", "B");

        Optional<ConsoleInput> consoleInput = consoleInputTranslater.translate(inputString);

        assertThat(consoleInput).isPresent();
        assertThat(consoleInput.get()).isEqualTo(ConsoleInput.create(new GameId("1"), Player.B));
    }

    @Test
    void translateNoncenceInput() {
        String inputString = createInputString("1", "nonsence");

        Optional<ConsoleInput> consoleInput = consoleInputTranslater.translate(inputString);

        assertThat(consoleInput).isPresent();
        assertThat(consoleInput.get()).isEqualTo(ConsoleInput.create(new GameId("1"), Player.NONE));
    }

    @Test
    void translateOtherGameIdInput() {
        String inputString = createInputString("other", "a");

        Optional<ConsoleInput> consoleInput = consoleInputTranslater.translate(inputString);

        assertThat(consoleInput).isPresent();
        assertThat(consoleInput.get()).isEqualTo(ConsoleInput.create(new GameId("other"), Player.A));
    }

    @Test
    void invalidInputReturnsOptional() {
        String inputString = createInputString(" other", "a");

        Optional<ConsoleInput> consoleInput = consoleInputTranslater.translate(inputString);

        assertThat(consoleInput).isEmpty();
    }

    @Test
    void noGameId() {
        String inputString = createInputString("", "a");

        Optional<ConsoleInput> consoleInput = consoleInputTranslater.translate(inputString);

        assertThat(consoleInput).isEmpty();
    }

    @Test
    void noPlayer() {
        String inputString = createInputString("1", "");

        Optional<ConsoleInput> consoleInput = consoleInputTranslater.translate(inputString);

        assertThat(consoleInput).isEmpty();
    }

    @Test
    void mustQuit() {
        String inputString = "quit";

        Optional<ConsoleInput> consoleInput = consoleInputTranslater.translate(inputString);

        assertThat(consoleInput).isPresent();
    }

    private String createInputString(final String gameId, final String player) {
        return "G" + gameId + " " + player;
    }
}