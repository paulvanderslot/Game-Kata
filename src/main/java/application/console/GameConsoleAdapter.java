package application.console;

import java.util.Optional;
import java.util.Scanner;

import application.storage.InMemoryGameRepository;
import domain.GameId;
import domain.GameService;
import domain.Player;

public class GameConsoleAdapter {

    private GameService gameService = new GameService(new InMemoryGameRepository());
    private ConsoleInputTranslater consoleInputTranslater = new ConsoleInputTranslater();
    private GameFeedbackPrinter printer = new GameFeedbackPrinter();

    public void startConsoleGame() {
        startGame();
    }

    private void startGame() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            Optional<ConsoleInput> optionalInput = consoleInputTranslater.translate(scanner.nextLine());
            if (optionalInput.isPresent()) {
                ConsoleInput consoleInput = optionalInput.get();
                if (consoleInput.mustQuit) {
                    return;
                }
                process(consoleInput);
            }
        }
    }

    private void process(ConsoleInput consoleInput) {
        Player player = consoleInput.player;
        GameId gameId = consoleInput.gameId;

        if (!player.equals(Player.NONE)) {
            if (gameService.isFinished(gameId)) {
                printGameIsFinished(gameId);
            }
            else {
                gameService.scored(gameId, player);
                printScoreFeedback(gameId);
            }
        }
    }

    private void printGameIsFinished(GameId gameId) {
        System.out.println(printer.printGameIsFinished(gameId));
    }

    private void printScoreFeedback(GameId gameId) {
        printScore(gameId);
        if (gameService.isFinished(gameId)) {
            printWinner(gameId);
        }
    }

    private void printScore(GameId gameId) {
        System.out.println(printer.print(gameId, gameService.getScore(gameId)));
    }

    private void printWinner(GameId gameId) {
        System.out.println(printer.printWinner(gameId, gameService.getWinner(gameId)));
    }
}
