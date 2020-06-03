package application;

import application.console.GameConsoleAdapter;

public class MyApp {

    public static void main(String[] args) {
        GameConsoleAdapter consoleAdapter = new GameConsoleAdapter();
        consoleAdapter.startConsoleGame();
    }
}
