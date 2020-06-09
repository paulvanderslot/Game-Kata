package domain;

public class Game {
    private Score currentScore;
    private GameId gameId;

    public Game(GameId gameId) {
        this.gameId = gameId;
        currentScore = new Score(0, 0, Player.NONE);
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
        return currentScore.playerAPoints >= 4 || currentScore.playerBPoints >= 4;
    }

    private boolean diffOfTwoPoints() {
        int diffInPoints = Math.abs(currentScore.playerAPoints - currentScore.playerBPoints);
        return diffInPoints >= 2;
    }
}
