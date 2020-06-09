package application.console;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import domain.GameId;
import domain.Player;

public class ConsoleInputTranslater {
    private static final String QUIT_COMMAND = "quit";

    private static final int GAME_GROUP = 1;
    private static final int PLAYER_GROUP = 2;
    private static final Pattern REGEX = Pattern.compile("^G([A-Za-z0-9]+) +([A-Za-z0-9]+)");

    public Optional<ConsoleInput> translate(String inputString) {
        if (inputString.equals(QUIT_COMMAND)) {
            return Optional.of(ConsoleInput.mustQuit());
        }
        String gamepart = extractGamePart(inputString);
        String playerpart = extractPlayerPart(inputString);

        if (gamepart == null || playerpart == null) {
            return Optional.empty();
        }

        return Optional.of(ConsoleInput.create(parseGamePart(gamepart), parsePlayer(playerpart)));
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
