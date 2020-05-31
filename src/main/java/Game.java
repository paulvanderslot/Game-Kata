public class Game {
    Score currentScore;

    Game() {
        currentScore = new Score(0, 0, Player.NONE);
    }

    void playerScores(Player player) {
        currentScore =  currentScore.scored(player);
    }

    boolean isFinished() {
        return currentScore.isFinished();
    }

    public Player getWinner() {
        return currentScore.getLastScored();
    }

    public Score getCurrentScore() {
        return currentScore;
    }
}
