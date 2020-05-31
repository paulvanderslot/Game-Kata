public class ScoreDeterminer {

    private int scoreA;

    private int scoreB;

    private Player lastScored;

    public void score(String input) {
        if (input.equalsIgnoreCase("A")) {
            scoreA++;
            lastScored = Player.A;
        }
        else if (input.equalsIgnoreCase("B")) {
            scoreB++;
            lastScored = Player.B;
        }
    }

    public String getScore() {
        return "Player " + lastScored + " scores Score [" + scoreA + "-" + scoreB + "]";
    }

    enum Player {
        A,
        B
    }

}
