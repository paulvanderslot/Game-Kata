package application.console;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import application.console.commands.GameCommand;
import application.console.commands.ListAllGames;
import application.console.commands.NoAction;
import application.console.commands.PlayerScores;
import application.console.commands.StopApplication;
import domain.GameId;
import domain.GameService;
import domain.Player;

public class GameCommandFactory {
    private static final String QUIT_COMMAND = "quit";
    private static final String LIST_GAMES_COMMAND = "ls";

    private static final int GAME_GROUP = 1;
    private static final int PLAYER_GROUP = 2;
    private static final Pattern REGEX = Pattern.compile("^[Gg]([A-Za-z0-9]+) +([A-Za-z0-9]+)");

    private final GameService gameService;
    private final GameFeedbackPrinter printer;
    private final ScoreKeeper scoreKeeper;

    GameCommandFactory(ScoreKeeper scoreKeeper, GameService gameService, GameFeedbackPrinter printer) {
        this.scoreKeeper = scoreKeeper;
        this.gameService = gameService;
        this.printer = printer;
    }

    public GameCommand create(String inputString) {
        if (inputString.equalsIgnoreCase(QUIT_COMMAND)) {
            return new StopApplication(scoreKeeper);
        }
        else if (inputString.equalsIgnoreCase(LIST_GAMES_COMMAND)) {
            return new ListAllGames(gameService, printer);
        }
        return scoreCommand(inputString);
    }

    private GameCommand scoreCommand(String inputString) {
        ConsoleInput input = translate(inputString);
        if (input.isComplete()) {
            return new PlayerScores(input, gameService, printer);
        }
        return new NoAction();
    }

    // to seperate class + tests from GameCommandFactoryTest
    private ConsoleInput translate(String inputString) {
        String gamepart = extractGamePart(inputString);
        String playerpart = extractPlayerPart(inputString);
        return ConsoleInput.create(parseGamePart(gamepart), parsePlayer(playerpart));
    }

    private String extractGamePart(String inputString) {
        Matcher matcher = REGEX.matcher(inputString);
        if (matcher.matches()) {
            return matcher.group(GAME_GROUP);
        }
        return null;
    }

    private String extractPlayerPart(String inputString) {
        Matcher matcher = REGEX.matcher(inputString);
        if (matcher.matches()) {
            return matcher.group(PLAYER_GROUP);
        }
        return null;
    }

    private GameId parseGamePart(String gamePart) {
        if (gamePart == null) {
            return null;
        }
        return new GameId(gamePart);
    }

    private Player parsePlayer(String playerPart) {
        if (playerPart == null) {
            return null;
        }

        if (playerPart.equalsIgnoreCase("A")) {
            return Player.A;
        }
        else if (playerPart.equalsIgnoreCase("B")) {
            return Player.B;
        }

        return null;
    }
}
