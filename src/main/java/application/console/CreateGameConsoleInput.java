package application.console;

import domain.Player;

public class CreateGameConsoleInput {

    public final Player firstPlayer;
    public final Player secondPlayer;

    public CreateGameConsoleInput(Player firstPlayer, Player secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
    }

    public boolean isComplete() {
        return firstPlayer != null && secondPlayer != null;
    }
}
