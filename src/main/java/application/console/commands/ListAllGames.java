package application.console.commands;

import application.console.GameFeedbackPrinter;
import domain.GameService;

public class ListAllGames implements GameCommand {
    private GameService gameService;
    private GameFeedbackPrinter printer;

    public ListAllGames(GameService gameService, GameFeedbackPrinter printer) {
        this.gameService = gameService;
        this.printer = printer;
    }

    @Override public void execute() {
        gameService.ongoingGames().forEach(
                game -> System.out.println(printer.printGameSummary(game.id(), game.isFinished(), game.getCurrentScore())));
    }
}
