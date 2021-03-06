package domain;

import java.util.Objects;

public class GameId {

    private String gameId;

    public GameId(String gameId) {
        if (gameId == null || gameId.isBlank()) {
            throw new IllegalStateException("GameId is empty or null");
        }
        this.gameId = gameId;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        GameId gameId1 = (GameId) o;
        return Objects.equals(gameId, gameId1.gameId);
    }

    @Override public int hashCode() {
        return Objects.hash(gameId);
    }

    @Override public String toString() {
        return gameId;
    }
}
