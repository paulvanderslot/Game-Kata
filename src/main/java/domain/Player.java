package domain;

import java.util.Objects;

public class Player {
    //TODO: get rid of none
    public static final Player NONE = new Player("NONE");
    private final String name;

    public Player(String name) {
        this.name = name;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Player player = (Player) o;
        return name.equalsIgnoreCase(player.name);
    }

    @Override public int hashCode() {
        return Objects.hash(name.toLowerCase());
    }

    @Override public String toString() {
        return name;
    }
}
