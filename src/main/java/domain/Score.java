package domain;

import java.util.Objects;

public class Score {

    public final int playerAPoints;
    public final int playerBPoints;
    public final Player lastScored;

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

        throw new IllegalArgumentException("domain.Player not supported");
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
}
