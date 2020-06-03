package application.console;

import java.util.Objects;

import domain.GameId;
import domain.Player;

public class ConsoleInput {
    final GameId gameId;
    final Player player;
    final boolean mustQuit;

    private ConsoleInput(GameId gameId, Player player, boolean mustQuit) {
        this.gameId = gameId;
        this.player = player;
        this.mustQuit = mustQuit;
    }

    public static ConsoleInput create(GameId gameId, Player player) {
        return new ConsoleInput(gameId, player, false);
    }

    public static ConsoleInput mustQuit() {
        return new ConsoleInput(null, null, true);
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ConsoleInput that = (ConsoleInput) o;
        return mustQuit == that.mustQuit &&
                Objects.equals(gameId, that.gameId) &&
                player == that.player;
    }

    @Override public int hashCode() {
        return Objects.hash(gameId, player, mustQuit);
    }

    @Override public String toString() {
        return "ConsoleInput{" +
                "gameId=" + gameId +
                ", player=" + player +
                ", mustQuit=" + mustQuit +
                '}';
    }
}
