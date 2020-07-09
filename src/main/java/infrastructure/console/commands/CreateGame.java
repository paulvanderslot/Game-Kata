package infrastructure.console.commands;

import domain.GameId;
import domain.Player;
import infrastructure.console.GameFeedbackPrinter;
import service.GameService;

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
        if (!firstPlayer.equals(secondPlayer)) {
            GameId gameId = gameService.startGame(firstPlayer, secondPlayer);
            System.out.println(printer.gameCreated(gameId, firstPlayer, secondPlayer));
        }
    }

}
