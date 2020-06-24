package application.console.commands;

import application.console.GameFeedbackPrinter;
import application.console.ScoreConsoleInput;
import domain.GameId;
import domain.GameService;
import domain.Player;

public class PlayerScores implements GameCommand {
    private ScoreConsoleInput scoreConsoleInput;
    private GameService gameService;
    private GameFeedbackPrinter printer;

    public PlayerScores(ScoreConsoleInput scoreConsoleInput, GameService gameService, GameFeedbackPrinter printer) {
        this.scoreConsoleInput = scoreConsoleInput;
        this.gameService = gameService;
        this.printer = printer;
    }

    @Override public void execute() {
        Player player = scoreConsoleInput.player;
        GameId gameId = scoreConsoleInput.gameId;

        if (gameService.gameExists(gameId)) {
            if (gameService.isFinished(gameId)) {
                printGameIsFinished(gameId);
            }
            else {
                //TODO check if player plays in game. otherwise print message
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
