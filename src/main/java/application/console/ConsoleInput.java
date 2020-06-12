package application.console;

import java.util.Objects;

import domain.GameId;
import domain.Player;

public class ConsoleInput {
    public final GameId gameId;
    public final Player player;
    public final boolean mustQuit;
    public boolean listGames;

    private ConsoleInput(GameId gameId, Player player, boolean mustQuit, boolean listGames) {
        this.gameId = gameId;
        this.player = player;
        this.mustQuit = mustQuit;
        this.listGames = listGames;
    }

    public static ConsoleInput create(GameId gameId, Player player) {
        return new ConsoleInput(gameId, player, false, false);
    }

    public static ConsoleInput mustQuit() {
        return new ConsoleInput(null, null, true, false);
    }

    public static ConsoleInput listGames() {
        return new ConsoleInput(null, null, false, true);
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
