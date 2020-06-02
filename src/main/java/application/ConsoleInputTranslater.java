package application;

import domain.Player;

public class ConsoleInputTranslater {

    public ConsoleInput translate(String inputString) {
        return new ConsoleInput(parsePlayer(inputString));
    }

    private Player parsePlayer(String input) {
        if (input.equalsIgnoreCase("A")) {
            return Player.A;
        }
        else if (input.equalsIgnoreCase("B")) {
            return Player.B;
        }

        return Player.NONE;
    }
}
