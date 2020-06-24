package application.console;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import domain.GameId;
import domain.Player;

public class ConsoleInputTranslater {
    private static final int GAME_GROUP = 1;
    private static final int PLAYER_GROUP = 2;
    private static final Pattern REGEX = Pattern.compile("^[Gg]([0-9]+) +([A-Za-z0-9]+)");

    // to seperate class + tests from GameCommandFactoryTest
    ScoreConsoleInput translateScoreInput(String inputString) {
        String gamepart = extractGamePart(inputString);
        String playerpart = extractPlayerPart(inputString);
        return ScoreConsoleInput.create(parseGameId(gamepart), parsePlayer(playerpart));
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

    private GameId parseGameId(String gamePart) {
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
