package application;

import java.util.Scanner;

import domain.GameService;
import domain.Player;
import domain.Score;

public class GameConsoleAdapter {

    private GameService gameService = new GameService(new InMemoryGameRepository());
    private ConsoleInputTranslater consoleInputTranslater = new ConsoleInputTranslater();
    private GameFeedbackPrinter printer = new GameFeedbackPrinter();

    public void startConsoleGame() {
        startGame();
    }

    private void startGame() {
        Scanner scanner = new Scanner(System.in);
        while (!gameService.isFinished("")) {
            ConsoleInput consoleInput = consoleInputTranslater.translate(scanner.nextLine());
            process(consoleInput);
        }
        printWinner();
    }

    private void process(ConsoleInput consoleInput) {
        if (!consoleInput.player.equals(Player.NONE)) {
            gameService.scored("", consoleInput.player);
            printScore();
        }
    }

    private void printScore() {
        System.out.println(printer.print(gameService.getScore("")));
    }

    private void printWinner() {
        System.out.println(printer.printWinner(gameService.getWinner("")));
    }
}
