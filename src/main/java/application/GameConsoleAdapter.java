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

    private String getScoreText(Score newScore) {
        String scoreText = "Player " + newScore.lastScored + " scores";
        scoreText += " Score [";
        scoreText += getPointsForPlayer(Player.A, newScore);
        scoreText += "-";
        scoreText += getPointsForPlayer(Player.B, newScore);
        scoreText += "]";
        return scoreText;
    }

    //TODO possible that serving right and points should be moved to Point class? -> would remove ifs (point.toString)
    private String getPointsForPlayer(Player player, Score newScore) {
        String points = getPointsText(player, newScore);
        if (newScore.hasServingRight(player)) {
            points += "*";
        }
        return points;
    }

    private String getPointsText(Player player, Score newScore) {
        if (player.equals(Player.A)) {
            return String.valueOf(newScore.playerAPoints);
        }
        else if (player.equals(Player.B)) {
            return String.valueOf(newScore.playerBPoints);
        }
        throw new IllegalArgumentException("Player not supported");
    }

    private void printWinner() {
        System.out.println("Player " + game.getWinner() + " wins");
    }
}
