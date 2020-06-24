package application.console;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import domain.GameId;
import domain.Player;

public class ConsoleInputTranslater {

    private static final Pattern REGEX_SCORE_INPUT = Pattern.compile("^[Gg]([0-9]+) +([A-Za-z0-9]+)");
    private static final Pattern REGEX_CREATE_GAME_INPUT = Pattern.compile("^[Cc][Gg] +([A-Za-z0-9]+) +([A-Za-z0-9]+)");

    ScoreConsoleInput translateScoreInput(String inputString) {
        String gamepart = extractPart(REGEX_SCORE_INPUT, 1, inputString);
        ;
        String playerpart = extractPart(REGEX_SCORE_INPUT, 2, inputString);
        return ScoreConsoleInput.create(parseGameId(gamepart), parsePlayer(playerpart));
    }

    public CreateGameConsoleInput translateCreateGameInput(String inputString) {
        String player1 = extractPart(REGEX_CREATE_GAME_INPUT, 1, inputString);
        String player2 = extractPart(REGEX_CREATE_GAME_INPUT, 2, inputString);
        return new CreateGameConsoleInput(parsePlayer(player1), parsePlayer(player2));
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

        return new Player(playerPart);
    }

    private String extractPart(Pattern regexToUse, int groupNr, String inputString) {
        Matcher matcher = regexToUse.matcher(inputString);
        if (matcher.matches()) {
            return matcher.group(groupNr);
        }
        return null;
    }
}
