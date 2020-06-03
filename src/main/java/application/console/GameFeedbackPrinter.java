package application.console;

import domain.GameId;
import domain.Player;
import domain.Score;

public class GameFeedbackPrinter {

    String print(GameId gameId, Score score) {
        String scoreText = "Player " + score.lastScored + " scores ";
        scoreText += "in game " + gameId + ". ";
        scoreText += "Score [";
        scoreText += getPointsForPlayer(Player.A, score);
        scoreText += "-";
        scoreText += getPointsForPlayer(Player.B, score);
        scoreText += "]";
        return scoreText;
    }

    //TODO possible that serving right and points should be moved to Point class? -> would remove ifs (point.toString)
    private String getPointsForPlayer(Player player, Score score) {
        String points = getPointsText(player, score);
        if (score.hasServingRight(player)) {
            points += "*";
        }
        return points;
    }

    private String getPointsText(Player player, Score score) {
        if (player.equals(Player.A)) {
            return String.valueOf(score.playerAPoints);
        }
        else if (player.equals(Player.B)) {
            return String.valueOf(score.playerBPoints);
        }
        throw new IllegalArgumentException("Player not supported");
    }

    String printWinner(GameId gameId, Player player) {
        return "Player " + player + " wins game " + gameId;
    }

    public String printGameIsFinished(GameId gameId) {
        return "No more scoring possible for game " + gameId + ". The game is finished.";
    }
}
