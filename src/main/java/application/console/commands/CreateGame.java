package application.console.commands;

import application.console.GameFeedbackPrinter;
import domain.GameId;
import domain.GameService;
import domain.Player;

public class CreateGame implements GameCommand {

    private GameService gameService;
    private GameFeedbackPrinter printer;
    private Player firstPlayer;
    private Player secondPlayer;

    public CreateGame(GameService gameService, GameFeedbackPrinter printer, Player firstPlayer, Player secondPlayer) {
        this.gameService = gameService;
        this.printer = printer;
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
    }

    @Override public void execute() {
        GameId gameId = gameService.startGame(firstPlayer, secondPlayer);
        System.out.println(printer.gameCreated(gameId, firstPlayer, secondPlayer));
    }

}
