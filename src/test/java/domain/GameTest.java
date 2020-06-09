package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameTest {

    private Game game;

    @BeforeEach
    void beforeEach() {
        game = new Game();
    }

    @Test
    void canScore() {
        scoreNTimes(1, game, Player.A);

        assertThat(game.getCurrentScore()).isEqualTo(new Score(1, 0, Player.A));
    }

    @Test
    void canScoreTwice() {
        scoreNTimes(2, game, Player.A);
        assertThat(game.getCurrentScore()).isEqualTo(new Score(2, 0, Player.A));
    }

    @Test
    void canScoreBoth() {
        scoreNTimes(1, game, Player.A);
        scoreNTimes(1, game, Player.B);

        assertThat(game.getCurrentScore()).isEqualTo(new Score(1, 1, Player.B));
    }

    @Test
    void noWinnerYet() {
        scoreNTimes(3, game, Player.A);

        assertThat(game.isFinished()).isFalse();
    }

    @Test
    void playerWon() {
        game.playerScores(Player.A);         // serving right
        scoreNTimes(4, game, Player.A);

        assertThat(game.isFinished()).isTrue();
        assertThat(game.getWinner()).isEqualTo(Player.A);
    }

    @Test
    void noWinnerWithOnePointDifference() {
        scoreNTimes(3, game, Player.A);
        scoreNTimes(4, game, Player.B);

        assertThat(game.isFinished()).isFalse();
        assertThat(game.getWinner()).isEqualTo(Player.NONE);
    }

    @Test
    void winnerWithTwoPointDifference() {
        scoreNTimes(3, game, Player.A);
        scoreNTimes(5, game, Player.B);

        assertThat(game.isFinished()).isTrue();
        assertThat(game.getWinner()).isEqualTo(Player.B);

    }

    @Test
    void scoringHadNoEffectWhenAlreadyWon() {
        game.playerScores(Player.A);         // serving right
        scoreNTimes(4, game, Player.A);

        assertThat(game.isFinished()).isTrue();
        assertThat(game.getWinner()).isEqualTo(Player.A);

        Score scoreWhenFinished = game.getCurrentScore();

        game.playerScores(Player.A);

        Score scoreAfterScoringAgain = game.getCurrentScore();

        assertThat(scoreWhenFinished).isEqualTo(scoreAfterScoringAgain);
    }

    void scoreNTimes(int times, Game game, Player player) {
        if (!game.getCurrentScore().hasServingRight(player)) {
            gainServingRight(game, player);
        }
        for (int i = 0; i < times; i++) {
            game.playerScores(player);
        }
    }

    private void gainServingRight(Game game, Player player) {
        game.playerScores(player);
    }

}