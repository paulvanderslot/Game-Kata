package application.console;

import domain.Player;
import domain.Score;

public class GameFeedbackPrinter {

    String print(Score score){
        String scoreText = "Player " + score.lastScored + " scores";
        scoreText += " Score [";
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

    String printWinner(Player player){
        return "Player " + player + " wins";
    }
}
