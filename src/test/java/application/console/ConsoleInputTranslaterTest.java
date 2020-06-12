package application.console;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import application.console.commands.GameCommand;
import application.console.commands.ListAllGames;
import application.console.commands.NoAction;
import application.console.commands.PlayerScores;
import application.console.commands.StopApplication;

class ConsoleInputTranslaterTest {
    private final ScoreKeeper nullScoreKeeper = null;
    private ConsoleInputTranslater consoleInputTranslater = new ConsoleInputTranslater(nullScoreKeeper);

    @Test
    void translatePlayerALowercase() {
        String inputString = createInputString("1", "a");

        GameCommand command = consoleInputTranslater.translate(inputString);

        assertThat(command).isOfAnyClassIn(PlayerScores.class);
        //        assertThat(consoleInput.get()).isEqualTo(ConsoleInput.create(new GameId("1"), Player.A));
    }

    @Test
    void translatePlayerBUppercase() {
        String inputString = createInputString("1", "B");

        GameCommand command = consoleInputTranslater.translate(inputString);

        assertThat(command).isOfAnyClassIn(PlayerScores.class);
        //        assertThat(consoleInput.get()).isEqualTo(ConsoleInput.create(new GameId("1"), Player.B));
    }

    @Test
    void translateNoncenceInput() {
        String inputString = createInputString("1", "nonsence");

        GameCommand command = consoleInputTranslater.translate(inputString);

        assertThat(command).isOfAnyClassIn(PlayerScores.class);
        //        assertThat(consoleInput.get()).isEqualTo(ConsoleInput.create(new GameId("1"), Player.NONE));
    }

    @Test
    void translateGameLowercase() {
        String inputString = "g1 a";

        GameCommand command = consoleInputTranslater.translate(inputString);

        assertThat(command).isOfAnyClassIn(PlayerScores.class);
        //        assertThat(consoleInput.get()).isEqualTo(ConsoleInput.create(new GameId("1"), Player.A));
    }

    @Test
    void translateOtherGameIdInput() {
        String inputString = createInputString("other", "a");

        GameCommand command = consoleInputTranslater.translate(inputString);

        assertThat(command).isOfAnyClassIn(PlayerScores.class);
        //        assertThat(consoleInput.get()).isEqualTo(ConsoleInput.create(new GameId("other"), Player.A));
    }

    @Test
    void invalidInputReturnsOptional() {
        String inputString = createInputString(" other", "a");

        GameCommand command = consoleInputTranslater.translate(inputString);

        assertThat(command).isOfAnyClassIn(NoAction.class);
    }

    @Test
    void noGameId() {
        String inputString = createInputString("", "a");

        GameCommand command = consoleInputTranslater.translate(inputString);

        assertThat(command).isOfAnyClassIn(NoAction.class);
    }

    @Test
    void noPlayer() {
        String inputString = createInputString("1", "");

        GameCommand command = consoleInputTranslater.translate(inputString);

        assertThat(command).isOfAnyClassIn(NoAction.class);
    }

    @Test
    void acceptsExtraSpace() {
        String inputStringWithExtraSpace = createInputString("other ", " a");

        GameCommand command = consoleInputTranslater.translate(inputStringWithExtraSpace);

        assertThat(command).isOfAnyClassIn(PlayerScores.class);
        //        assertThat(consoleInput.get()).isEqualTo(ConsoleInput.create(new GameId("other"), Player.A));
    }

    @Test
    void mustQuit() {
        String inputString = "quit";

        GameCommand command = consoleInputTranslater.translate(inputString);

        assertThat(command).isOfAnyClassIn(StopApplication.class);

    }

    @Test
    void mustQuitIgnoreCasing() {
        String inputString = "QUiT";

        GameCommand command = consoleInputTranslater.translate(inputString);

        assertThat(command).isOfAnyClassIn(StopApplication.class);

    }

    @Test
    void listAllGames() {
        String inputString = "LS";

        GameCommand command = consoleInputTranslater.translate(inputString);

        assertThat(command).isOfAnyClassIn(ListAllGames.class);
    }

    @Test
    void listAllGamesIgnoreCasing() {
        String inputString = "Ls";

        GameCommand command = consoleInputTranslater.translate(inputString);

        assertThat(command).isOfAnyClassIn(ListAllGames.class);
    }

    private String createInputString(final String gameId, final String player) {
        return "G" + gameId + " " + player;
    }
}