package infrastructure.console;

import infrastructure.console.commands.CreateGame;
import infrastructure.console.commands.GameCommand;
import infrastructure.console.commands.ListAllGames;
import infrastructure.console.commands.NoAction;
import infrastructure.console.commands.PlayerScores;
import infrastructure.console.commands.StopApplication;
import service.GameService;

public class GameCommandFactory {
    private static final String QUIT_COMMAND = "quit";
    private static final String LIST_GAMES_COMMAND = "ls";
    private static final String START_OF_CREATE_GAME_COMMAND = "cg";
    private static final String START_OF_SCORE_COMMAND = "g";

    private final ConsoleInputTranslater translater = new ConsoleInputTranslater();
    private final GameService gameService;
    private final GameFeedbackPrinter printer;
    private final ScoreKeeper scoreKeeper;

    GameCommandFactory(ScoreKeeper scoreKeeper, GameService gameService, GameFeedbackPrinter printer) {
        this.scoreKeeper = scoreKeeper;
        this.gameService = gameService;
        this.printer = printer;
    }

    public GameCommand create(String inputString) {
        if (inputString.equalsIgnoreCase(QUIT_COMMAND)) {
            return new StopApplication(scoreKeeper);
        }
        else if (inputString.equalsIgnoreCase(LIST_GAMES_COMMAND)) {
            return new ListAllGames(gameService, printer);
        }
        else if (inputString.toLowerCase().startsWith(START_OF_CREATE_GAME_COMMAND)) {
            return createGameCommand(inputString);
        }
        else if (inputString.toLowerCase().startsWith(START_OF_SCORE_COMMAND)) {
            return scoreCommand(inputString);
        }
        return new NoAction();
    }

    private GameCommand createGameCommand(String inputString) {
        CreateGameConsoleInput input = translater.translateCreateGameInput(inputString);
        if (input.isComplete()) {
            return new CreateGame(gameService, printer, input.firstPlayer, input.secondPlayer);
        }
        return new NoAction();
    }

    private GameCommand scoreCommand(String inputString) {
        ScoreConsoleInput input = translater.translateScoreInput(inputString);
        if (input.isComplete()) {
            return new PlayerScores(input, gameService, printer);
        }
        return new NoAction();
    }

}
