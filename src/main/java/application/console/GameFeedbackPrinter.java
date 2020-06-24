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
        scoreInfo += String.valueOf(score.firstPlayerPoints);
        scoreInfo += addServingRightString(score, score.firstPlayer);
        scoreInfo += "-";
        scoreInfo += String.valueOf(score.secondPlayerPoints);
        scoreInfo += addServingRightString(score, score.secondPlayer);
        scoreInfo += "]";
        return scoreInfo;
    }

    //TODO possible that serving right and points should be moved to Point class? -> would remove ifs (point.toString)
    private String addServingRightString(Score score, Player player) {
        if (score.hasServingRight(player)) {
            return "*";
        }
        return "";
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
        String gameInfo = "Game " + id + ", " + score.firstPlayer + " vs " + score.secondPlayer;
        String gameStateInfo = finished ? "Finished" : "Ongoing";
        String winnerInfo = finished ? " Player " + score.lastScored + " won" : "";
        return gameInfo + " : " + gameStateInfo + " - " + getScoreInfo(score) + winnerInfo;
    }

    public String gameCreated(GameId gameId, Player playerOne, Player playerTwo) {
        return "Game " + gameId + " created with player " + playerOne + " and " + playerTwo;
    }

    public String printNotPlaying(GameId gameId, Player player) {
        return "Player " + player + " does not participate in game " + gameId;
    }
}
