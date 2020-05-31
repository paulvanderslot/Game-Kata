public class Score {

    private int playerAPoints = 0;
    private int playerBPoints = 0;

    private Player lastScored;

    public void scored(Player player) {
        if (Player.A == player) {
            playerAPoints++;
        }
        else if (Player.B == player) {
            playerBPoints++;
        }
        lastScored = player;
    }

    public Player getLastScored() {
        return lastScored;
    }

    public boolean isFinished() {
        return isFinalPointsReached() && diffOfTwoPoints();
    }

    private boolean isFinalPointsReached() {
        return playerAPoints >= 4 || playerBPoints >= 4;
    }

    private boolean diffOfTwoPoints() {
        return Math.abs(playerAPoints - playerBPoints) >= 2;
    }

    @Override public String toString() {
        return playerAPoints + "-" + playerBPoints;
    }
}
