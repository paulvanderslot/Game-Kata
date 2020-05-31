import java.util.Scanner;

public class MyApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ConsoleAdapter consoleAdapter = new ConsoleAdapter();
        while(!consoleAdapter.isFinished()) {
            consoleAdapter.score(scanner.nextLine());
            System.out.println(consoleAdapter.getPrintedScore());
        }
        System.out.println(consoleAdapter.getWinner());
    }
}
