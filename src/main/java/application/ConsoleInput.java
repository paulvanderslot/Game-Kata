package application;

import java.util.Objects;

import domain.Player;

public class ConsoleInput {
    final Player player;

    public ConsoleInput(Player player) {
        this.player = player;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ConsoleInput that = (ConsoleInput) o;
        return player == that.player;
    }

    @Override public int hashCode() {
        return Objects.hash(player);
    }
}
