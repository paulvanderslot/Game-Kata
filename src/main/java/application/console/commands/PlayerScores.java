package application.console.commands;

import application.console.ConsoleInput;
import application.console.GameFeedbackPrinter;
import domain.GameId;
import domain.GameService;
import domain.Player;

public class PlayerScores implements GameCommand {
    private ConsoleInput consoleInput;
    private GameService gameService;
    private GameFeedbackPrinter printer;

    public PlayerScores(ConsoleInput consoleInput, GameService gameService, GameFeedbackPrinter printer) {
        this.consoleInput = consoleInput;
        this.gameService = gameService;
        this.printer = printer;
    }

    @Override public void execute() {
        Player player = consoleInput.player;
        GameId gameId = consoleInput.gameId;

        if (gameService.gameExists(gameId)) {
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
        System.out.println(printer.printScoreFeedback(gameId, gameService.getScore(gameId)));
    }

    private void printWinner(GameId gameId) {
        System.out.println(printer.printWinner(gameId, gameService.getWinner(gameId)));
    }

}
