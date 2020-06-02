package application;

import java.util.Scanner;

import domain.GameService;
import domain.Player;
import domain.Score;

public class GameConsoleAdapter {

    private GameService gameService = new GameService(new InMemoryGameRepository());
    private ConsoleInputTranslater consoleInputTranslater = new ConsoleInputTranslater();

    public void startConsoleGame() {
        startGame();
    }

    private void startGame() {
        Scanner scanner = new Scanner(System.in);
        while (!gameService.isFinished("")) {
            ConsoleInput consoleInput = consoleInputTranslater.translate(scanner.nextLine());
            process(consoleInput);
        }
        printWinner();
    }

    private void process(ConsoleInput consoleInput) {
        if (!consoleInput.player.equals(Player.NONE)) {
            gameService.scored("",consoleInput.player);
            printScore();
        }
    }

    private void printScore() {
        System.out.println(getScoreText(gameService.getScore("")));
    }

    private String getScoreText(Score score) {
        String scoreText = "Player " + score.lastScored + " scores";
        scoreText += " Score [";
        scoreText += getPointsForPlayer(Player.A, score);
        scoreText += "-";
        scoreText += getPointsForPlayer(Player.B, score);
        scoreText += "]";
        return scoreText;
    }

    //TODO possible that serving right and points should be moved to Point class? -> would remove ifs (point.toString)
    private String getPointsForPlayer(Player player, Score score) {
        String points = getPointsText(player, score);
        if (score.hasServingRight(player)) {
            points += "*";
        }
        return points;
    }

    private String getPointsText(Player player, Score score) {
        if (player.equals(Player.A)) {
            return String.valueOf(score.playerAPoints);
        }
        else if (player.equals(Player.B)) {
            return String.valueOf(score.playerBPoints);
        }
        throw new IllegalArgumentException("Player not supported");
    }

    private void printWinner() {
        System.out.println("Player " + gameService.getWinner("") + " wins");
    }
}
