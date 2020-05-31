public class ConsoleAdapter {

    private Score score = new Score();

    public void score(String input) {
        Player player = parse(input);
        if (!player.equals(Player.NONE)) {
            score.scored(player);
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

    public String getPrintedScore() {
        return "Player " + score.getLastScored() + " scores Score [" + score.toString() + "]";
    }

    public boolean isFinished() {
        return score.isFinished();
    }

    public String getWinner() {
        return "Player " + score.getLastScored() + " wins";
    }
}
