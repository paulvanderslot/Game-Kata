package application;

import application.console.GameConsoleAdapter;
import application.console.ScoreKeeper;

public class MyApp {

    public static void main(String[] args) {
        ScoreKeeper scoreKeeper = new GameConsoleAdapter();
        scoreKeeper.start();
    }
}
