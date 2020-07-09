package infrastructure.console;

import java.util.Objects;

import domain.GameId;
import domain.Player;

public class ScoreConsoleInput {
    public final GameId gameId;
    public final Player player;

    private ScoreConsoleInput(GameId gameId, Player player) {
        this.gameId = gameId;
        this.player = player;
    }

    public static ScoreConsoleInput create(GameId gameId, Player player) {
        return new ScoreConsoleInput(gameId, player);
    }

    public boolean isComplete() {
        return gameId != null && player != null;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ScoreConsoleInput that = (ScoreConsoleInput) o;
        return
                Objects.equals(gameId, that.gameId) &&
                        Objects.equals(player, that.player);
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
