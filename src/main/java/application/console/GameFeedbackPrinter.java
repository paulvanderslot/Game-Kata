package application.console;

import domain.GameId;
import domain.Player;
import domain.Score;

public class GameFeedbackPrinter {

    public String printScoreFeedback(GameId gameId, Score score) {
        String playerAndGameText = "Player " + score.lastScored + " scores in game " + gameId + ". ";
        return playerAndGameText + getScoreInfo(score);
    }

    private String getScoreInfo(Score score) {
        String scoreInfo = "Score [";
        scoreInfo += getPointsForPlayer(Player.A, score);
        scoreInfo += "-";
        scoreInfo += getPointsForPlayer(Player.B, score);
        scoreInfo += "]";
        return scoreInfo;
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

    public String printWinner(GameId gameId, Player player) {
        return "Player " + player + " wins game " + gameId;
    }

    public String printGameIsFinished(GameId gameId) {
        return "No more scoring possible for game " + gameId + ". The game is finished.";
    }

    public String printWelcomeMessage() {
        return "*********************************" + System.lineSeparator() +
                "**         ScoreKeeper         **" + System.lineSeparator() +
                "*********************************";
    }

    public String printGameSummary(GameId id, boolean finished, Score score) {
        String gameInfo = "Game " + id;
        String gameStateInfo = finished ? "Finished" : "Ongoing";
        String winnerInfo = finished ? " Player " + score.lastScored + " won" : "";
        return gameInfo + " : " + gameStateInfo + " - " + getScoreInfo(score) + winnerInfo;
    }

}
