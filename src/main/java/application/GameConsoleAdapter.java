package application;

import java.util.Scanner;

import domain.Game;
import domain.Player;
import domain.Score;

public class GameConsoleAdapter {

    private Game game = new Game();

    public void startNewGame() {
        game = new Game();
        startGame();
    }

    private void startGame() {
        Scanner scanner = new Scanner(System.in);
        while (!game.isFinished()) {
            parseGameInput(scanner.nextLine());
        }
        printWinner();
    }

    public void parseGameInput(String input) {
        Player player = parse(input);
        if (!player.equals(Player.NONE)) {
            game.playerScores(player);
            printScore(game.getCurrentScore());
        }
    }

    // TODO move to parsing class if gets complicated
    private Player parse(String input) {
        if (input.equalsIgnoreCase("A")) {
            return Player.A;
        }
        else if (input.equalsIgnoreCase("B")) {
            return Player.B;
        }

        return Player.NONE;
    }

    private void printScore(Score newScore) {
        System.out.println(getScoreText(newScore));
    }

    //TODO possible that serving right and points should be moved to Point class? -> would remove ifs (point.toString)
    private String getScoreText(Score newScore) {
        String scoreText = "Player " + newScore.lastScored + " scores";
        scoreText += " Score [" + newScore.playerAPoints;
        if (newScore.lastScored.equals(Player.A)) {
            scoreText += "*";
        }
        scoreText += "-" + newScore.playerBPoints;
        if (newScore.lastScored.equals(Player.B)) {
            scoreText += "*";
        }
        scoreText += "]";
        return scoreText;
    }

    private void printWinner() {
        System.out.println("Player " + game.getWinner() + " wins");
    }
}
