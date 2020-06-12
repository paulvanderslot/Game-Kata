package application.console;

import java.util.Objects;

import domain.GameId;
import domain.Player;

public class ConsoleInput {
    public final GameId gameId;
    public final Player player;

    private ConsoleInput(GameId gameId, Player player) {
        this.gameId = gameId;
        this.player = player;
    }

    public static ConsoleInput create(GameId gameId, Player player) {
        return new ConsoleInput(gameId, player);
    }

    public boolean isComplete() {
        return gameId != null && player != null;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ConsoleInput that = (ConsoleInput) o;
        return
                Objects.equals(gameId, that.gameId) &&
                        player == that.player;
    }

    @Override public int hashCode() {
        return Objects.hash(gameId, player);
    }

    @Override public String toString() {
        return "ConsoleInput{" +
                "gameId=" + gameId +
                ", player=" + player +
                '}';
    }
}
