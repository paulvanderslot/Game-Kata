public class GameConsoleAdapter {

    private Game game = new Game();

    public void parseGameInput(String input) {
        Player player = parse(input);
        if (!player.equals(Player.NONE)) {
            game.playerScores(player);
            printScore(game.getCurrentScore());
        }
    }

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
        System.out.println("Player " + newScore.getLastScored() + " scores Score [" + newScore.toString() + "]");
    }

    public boolean isFinished() {
        if (game.isFinished()) {
            printWinner();
        }
        return game.isFinished();
    }

    private void printWinner() {
        System.out.println("Player " + game.getWinner() + " wins");
    }
}
