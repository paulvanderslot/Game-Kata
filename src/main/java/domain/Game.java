package domain;

public class Game {
    private Score currentScore;
    private GameId gameId;

    public Game(GameId gameId, Player firstPlayer, Player secondPlayer) {
        if (firstPlayer.equals(secondPlayer)) {
            throw new IllegalArgumentException("Player 1 and 2 are the same");
        }
        this.gameId = gameId;
        currentScore = Score.startingScore(firstPlayer, secondPlayer);
    }

    public GameId id() {
        return gameId;
    }

    public void playerScores(Player player) {
        if (!isFinished()) {
            currentScore = currentScore.scores(player);
        }
    }

    public boolean isFinished() {
        return isFinalPointsReached() && diffOfTwoPoints();
    }

    public Player getWinner() {
        if (isFinished()) {
            return currentScore.lastScored;
        }
        return Player.NONE;
    }

    public Score getCurrentScore() {
        return currentScore;
    }

    private boolean isFinalPointsReached() {
        return currentScore.firstPlayerPoints >= 4 || currentScore.secondPlayerPoints >= 4;
    }

    private boolean diffOfTwoPoints() {
        int diffInPoints = Math.abs(currentScore.firstPlayerPoints - currentScore.secondPlayerPoints);
        return diffInPoints >= 2;
    }
}
