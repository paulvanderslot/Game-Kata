package application.console;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import application.console.commands.CreateGame;
import application.console.commands.GameCommand;
import application.console.commands.ListAllGames;
import application.console.commands.NoAction;
import application.console.commands.PlayerScores;
import application.console.commands.StopApplication;

class GameCommandFactoryTest {

    //TODO commands inhoudelijk testen
    private GameCommandFactory gameCommandFactory =
            new GameCommandFactory(null, null, null);

    @Test
    void translatePlayerCommand() {
        String inputString = "g1 a";

        GameCommand command = gameCommandFactory.create(inputString);

        assertThat(command).isOfAnyClassIn(PlayerScores.class);
    }

    @Test
    void translatePlayerCommandIppercase() {
        String inputString = "G1 A";

        GameCommand command = gameCommandFactory.create(inputString);

        assertThat(command).isOfAnyClassIn(PlayerScores.class);
    }


    @Test
    void translateNoncenceInput() {
        String inputString = createInputString("1", "nonsence");

        GameCommand command = gameCommandFactory.create(inputString);

        assertThat(command).isOfAnyClassIn(NoAction.class);
    }

    @Test
    void translateNonNumberGameIdInput() {
        String inputString = createInputString("other", "a");

        GameCommand command = gameCommandFactory.create(inputString);

        assertThat(command).isOfAnyClassIn(NoAction.class);
    }

    @Test
    void invalidInputReturnsOptional() {
        String inputString = createInputString(" other", "a");

        GameCommand command = gameCommandFactory.create(inputString);

        assertThat(command).isOfAnyClassIn(NoAction.class);
    }

    @Test
    void noGameId() {
        String inputString = createInputString("", "a");

        GameCommand command = gameCommandFactory.create(inputString);

        assertThat(command).isOfAnyClassIn(NoAction.class);
    }

    @Test
    void noPlayer() {
        String inputString = createInputString("1", "");

        GameCommand command = gameCommandFactory.create(inputString);

        assertThat(command).isOfAnyClassIn(NoAction.class);
    }

    @Test
    void acceptsExtraSpace() {
        String inputStringWithExtraSpace = "G1  a";

        GameCommand command = gameCommandFactory.create(inputStringWithExtraSpace);

        assertThat(command).isOfAnyClassIn(PlayerScores.class);
    }

    @Test
    void mustQuit() {
        String inputString = "quit";

        GameCommand command = gameCommandFactory.create(inputString);

        assertThat(command).isOfAnyClassIn(StopApplication.class);

    }

    @Test
    void mustQuitIgnoreCasing() {
        String inputString = "QUiT";

        GameCommand command = gameCommandFactory.create(inputString);

        assertThat(command).isOfAnyClassIn(StopApplication.class);

    }

    @Test
    void listAllGames() {
        String inputString = "LS";

        GameCommand command = gameCommandFactory.create(inputString);

        assertThat(command).isOfAnyClassIn(ListAllGames.class);
    }

    @Test
    void listAllGamesIgnoreCasing() {
        String inputString = "Ls";

        GameCommand command = gameCommandFactory.create(inputString);

        assertThat(command).isOfAnyClassIn(ListAllGames.class);
    }

    @Test
    void createNewGame() {
        String inputString = "CG";

        GameCommand command = gameCommandFactory.create(inputString);

        assertThat(command).isOfAnyClassIn(CreateGame.class);
    }

    @Test
    void createNewGameIgnoreCasing() {
        String inputString = "cG";

        GameCommand command = gameCommandFactory.create(inputString);

        assertThat(command).isOfAnyClassIn(CreateGame.class);
    }

    private String createInputString(final String gameId, final String player) {
        return "G" + gameId + " " + player;
    }
}