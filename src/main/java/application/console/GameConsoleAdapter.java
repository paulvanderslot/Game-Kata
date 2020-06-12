package application.console;

import java.util.Scanner;

import application.console.commands.GameCommand;

public class GameConsoleAdapter implements ScoreKeeper {

    private ConsoleInputTranslater consoleInputTranslater = new ConsoleInputTranslater(this);
    private GameFeedbackPrinter printer = new GameFeedbackPrinter();

    private boolean isActive = true;

    public void start() {
        printWelcomeMessage();
        Scanner scanner = new Scanner(System.in);
        while (isActive) {
            GameCommand command = consoleInputTranslater.translate(scanner.nextLine());
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
