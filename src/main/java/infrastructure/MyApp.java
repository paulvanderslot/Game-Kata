package infrastructure;

import infrastructure.console.GameConsoleAdapter;
import infrastructure.console.ScoreKeeper;

public class MyApp {

    public static void main(String[] args) {
        ScoreKeeper scoreKeeper = new GameConsoleAdapter();
        scoreKeeper.start();
    }
}
