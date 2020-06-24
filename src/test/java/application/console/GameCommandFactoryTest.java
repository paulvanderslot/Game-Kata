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
    void translateNoncenceScoredInput() {
        String inputString = "G1 Noncence";

        GameCommand command = gameCommandFactory.create(inputString);

        assertThat(command).isOfAnyClassIn(PlayerScores.class);
    }

    @Test
    void translateNonNumberGameIdInput() {
        String inputString = "Gother a";

        GameCommand command = gameCommandFactory.create(inputString);

        assertThat(command).isOfAnyClassIn(NoAction.class);
    }

    @Test
    void invalidInputReturnsOptional() {
        String inputString = "G other a";

        GameCommand command = gameCommandFactory.create(inputString);

        assertThat(command).isOfAnyClassIn(NoAction.class);
    }

    @Test
    void noGameId() {
        String inputString = "G a";

        GameCommand command = gameCommandFactory.create(inputString);

        assertThat(command).isOfAnyClassIn(NoAction.class);
    }

    @Test
    void noPlayer() {
        String inputString = "G1 ";

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
    void createNewGameWithoutPlayers() {
        String inputString = "CG";

        GameCommand command = gameCommandFactory.create(inputString);

        assertThat(command).isOfAnyClassIn(NoAction.class);
    }

    @Test
    void createNewGame() {
        String inputString = "cG A b";

        GameCommand command = gameCommandFactory.create(inputString);

        assertThat(command).isOfAnyClassIn(CreateGame.class);
    }

}