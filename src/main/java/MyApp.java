import java.util.Scanner;

public class MyApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        GameConsoleAdapter consoleAdapter = new GameConsoleAdapter();
        while(!consoleAdapter.isFinished()) {
            consoleAdapter.parseGameInput(scanner.nextLine());
        }
    }
}
