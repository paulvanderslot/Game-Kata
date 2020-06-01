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
            printCurrentScore();
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

    private void printCurrentScore() {
        System.out.println(getScoreText(game.getCurrentScore()));
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
        System.out.println("Player " + game.getWinner() + " wins");
    }
}
