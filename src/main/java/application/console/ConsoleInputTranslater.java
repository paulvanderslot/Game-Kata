package application.console;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import application.console.commands.GameCommand;
import application.console.commands.ListAllGames;
import application.console.commands.NoAction;
import application.console.commands.PlayerScores;
import application.console.commands.StopApplication;
import application.storage.InMemoryGameRepository;
import domain.GameId;
import domain.GameService;
import domain.Player;

public class ConsoleInputTranslater {
    private static final String QUIT_COMMAND = "quit";
    private static final String LIST_GAMES_COMMAND = "ls";

    private static final int GAME_GROUP = 1;
    private static final int PLAYER_GROUP = 2;
    private static final Pattern REGEX = Pattern.compile("^[Gg]([A-Za-z0-9]+) +([A-Za-z0-9]+)");

    private GameService gameService = new GameService(new InMemoryGameRepository());
    private GameFeedbackPrinter printer = new GameFeedbackPrinter();
    private ScoreKeeper scoreKeeper;

    ConsoleInputTranslater(ScoreKeeper scoreKeeper) {
        this.scoreKeeper = scoreKeeper;
    }

    public GameCommand translate(String inputString) {
        if (inputString.equalsIgnoreCase(QUIT_COMMAND)) {
            return new StopApplication(scoreKeeper);
        }
        else if (inputString.equalsIgnoreCase(LIST_GAMES_COMMAND)) {
            return new ListAllGames(gameService, printer);
        }
        return scoreCommand(inputString);
    }

    private GameCommand scoreCommand(String inputString) {
        String gamepart = extractGamePart(inputString);
        String playerpart = extractPlayerPart(inputString);

        if (gamepart == null || playerpart == null) {
            return new NoAction();
        }

        return new PlayerScores(ConsoleInput.create(parseGamePart(gamepart), parsePlayer(playerpart)), gameService, printer);
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
        return "";
    }

    private GameId parseGamePart(String gamePart) {
        return new GameId(gamePart);
    }

    private Player parsePlayer(String playerPart) {
        if (playerPart.equalsIgnoreCase("A")) {
            return Player.A;
        }
        else if (playerPart.equalsIgnoreCase("B")) {
            return Player.B;
        }

        return Player.NONE;
    }
}
