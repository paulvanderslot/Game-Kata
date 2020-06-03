package domain;

import java.util.Objects;

public class Score {

    public final int playerAPoints;
    public final int playerBPoints;
    public final Player lastScored;

    public Score(int playerAPoints, int playerBPoints, Player lastScored) {
        this.playerAPoints = playerAPoints;
        this.playerBPoints = playerBPoints;
        this.lastScored = lastScored;
    }

    public Score scores(Player player) {
        if (hasServingRight(player)) {
            return scoresPoint(player);
        }
        return earnsServingRight(player);
    }

    // TODO not sure if it belongs here
    public boolean hasServingRight(Player player) {
        return lastScored.equals(player);
    }

    private Score earnsServingRight(Player player) {
        return new Score(playerAPoints, playerBPoints, player);
    }

    private Score scoresPoint(Player player) {
        if (Player.A == player) {
            return new Score(playerAPoints + 1, playerBPoints, player);
        }
        else if (Player.B == player) {
            return new Score(playerAPoints, playerBPoints + 1, player);
        }
        throw new IllegalArgumentException("Player not supported");
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
        return "Score{" +
                "playerAPoints=" + playerAPoints +
                ", playerBPoints=" + playerBPoints +
                ", lastScored=" + lastScored +
                '}';
    }
}
