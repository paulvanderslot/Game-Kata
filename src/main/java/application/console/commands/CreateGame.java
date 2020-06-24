package application.console.commands;

import application.console.GameFeedbackPrinter;
import domain.GameId;
import domain.GameService;
import domain.Player;

public class CreateGame implements GameCommand {

    private GameService gameService;
    private GameFeedbackPrinter printer;

    public CreateGame(GameService gameService, GameFeedbackPrinter printer) {
        this.gameService = gameService;
        this.printer = printer;
    }

    @Override public void execute() {
        Player playerOne = Player.A;
        Player playerTwo = Player.B;
        GameId gameId = gameService.startGame(playerOne, playerTwo);
        System.out.println(printer.gameCreated(gameId, playerOne, playerTwo));
    }

}
