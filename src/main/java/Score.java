import java.util.Objects;

public class Score {

    private final int playerAPoints;
    private final int playerBPoints;

    private Player lastScored;

    Score(int playerAPoints, int playerBPoints, Player lastScored) {
        this.playerAPoints = playerAPoints;
        this.playerBPoints = playerBPoints;
        this.lastScored = lastScored;
    }

    public Score scored(Player player) {
        if (Player.A == player) {
            return new Score(playerAPoints + 1, playerBPoints, player);
        }
        else if (Player.B == player) {
            return new Score(playerAPoints, playerBPoints + 1, player);
        }

        throw new IllegalArgumentException("Player not supported");
    }

    public Player getLastScored() {
        return lastScored;
    }

    public boolean isFinished() {
        return isFinalPointsReached() && diffOfTwoPoints();
    }

    private boolean isFinalPointsReached() {
        return playerAPoints >= 4 || playerBPoints >= 4;
    }

    private boolean diffOfTwoPoints() {
        return Math.abs(playerAPoints - playerBPoints) >= 2;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Score score = (Score) o;
        return playerAPoints == score.playerAPoints &&
                playerBPoints == score.playerBPoints &&
                lastScored == score.lastScored;
    }

    @Override public int hashCode() {
        return Objects.hash(playerAPoints, playerBPoints, lastScored);
    }

    @Override public String toString() {
        return playerAPoints + "-" + playerBPoints;
    }
}
