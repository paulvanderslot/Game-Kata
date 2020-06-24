package domain;

import java.util.Objects;

public class Score {

    public final Player firstPlayer;
    public final Player secondPlayer;
    public final int firstPlayerPoints;
    public final int secondPlayerPoints;
    public final Player lastScored;

    public static Score startingScore(Player firstPlayer, Player secondPlayer) {
        return new Score(firstPlayer, secondPlayer, 0, 0, Player.NONE);
    }

    public Score(Player firstPlayer, Player secondPlayer, int firstPlayerPoints, int secondPlayerPoints, Player lastScored) {
        if (firstPlayerPoints < 0 || secondPlayerPoints < 0) {
            throw new IllegalArgumentException("No negative points allowed");
        }
        if (lastScored == null) {
            throw new IllegalArgumentException("lastScored may not be null");
        }
        if (firstPlayer == null) {
            throw new IllegalArgumentException("firstPlayer may not be null");
        }
        if (secondPlayer == null) {
            throw new IllegalArgumentException("secondPlayer may not be null");
        }

        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.firstPlayerPoints = firstPlayerPoints;
        this.secondPlayerPoints = secondPlayerPoints;
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
        return new Score(firstPlayer, secondPlayer, firstPlayerPoints, secondPlayerPoints, player);
    }

    private Score scoresPoint(Player player) {
        if (firstPlayer.equals(player)) {
            return new Score(firstPlayer, secondPlayer, firstPlayerPoints + 1, secondPlayerPoints, player);
        }
        else if (secondPlayer.equals(player)) {
            return new Score(firstPlayer, secondPlayer, firstPlayerPoints, secondPlayerPoints + 1, player);
        }
        throw new IllegalArgumentException("Player not supported");
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Score score = (Score) o;
        return firstPlayerPoints == score.firstPlayerPoints &&
                secondPlayerPoints == score.secondPlayerPoints &&
                firstPlayer == score.firstPlayer &&
                secondPlayer == score.secondPlayer &&
                lastScored == score.lastScored;
    }

    @Override public int hashCode() {
        return Objects.hash(firstPlayer, secondPlayer, firstPlayerPoints, secondPlayerPoints, lastScored);
    }

    @Override public String toString() {
        return "Score{" +
                "firstPlayer=" + firstPlayer +
                ", secondPlayer=" + secondPlayer +
                ", firstPlayerPoints=" + firstPlayerPoints +
                ", secondPlayerPoints=" + secondPlayerPoints +
                ", lastScored=" + lastScored +
                '}';
    }
}
