package infrastructure.console;

import java.util.Scanner;

import infrastructure.console.commands.GameCommand;
import infrastructure.storage.InMemoryGameRepository;
import service.GameService;

public class GameConsoleAdapter implements ScoreKeeper {

    private GameService gameService = new GameService(new InMemoryGameRepository());
    private GameFeedbackPrinter printer = new GameFeedbackPrinter();
    private GameCommandFactory gameCommandFactory = new GameCommandFactory(this, gameService, printer);

    private boolean isActive = true;

    public void start() {
        printWelcomeMessage();
        Scanner scanner = new Scanner(System.in);
        while (isActive) {
            GameCommand command = gameCommandFactory.create(scanner.nextLine());
            command.execute();
        }
    }

    public void stop() {
        isActive = false;
    }

    private void printWelcomeMessage() {
        System.out.println(printer.printWelcomeMessage());
    }

}
