package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameTest {

    private Game game;

    @BeforeEach
    void beforeEach() {
        game = new Game(new GameId("1"), new Player("A"), new Player("B"));
    }

    @Test
    void canScore() {
        scoreNTimes(1, game, new Player("A"));

        assertThat(game.getCurrentScore()).isEqualTo(new Score(new Player("A"), new Player("B"), 1, 0, new Player("A")));
    }

    @Test
    void canScoreTwice() {
        scoreNTimes(2, game, new Player("A"));
        assertThat(game.getCurrentScore()).isEqualTo(new Score(new Player("A"), new Player("B"), 2, 0, new Player("A")));
    }

    @Test
    void canScoreBoth() {
        scoreNTimes(1, game, new Player("A"));
        scoreNTimes(1, game, new Player("B"));

        assertThat(game.getCurrentScore()).isEqualTo(new Score(new Player("A"), new Player("B"), 1, 1, new Player("B")));
    }

    @Test
    void noWinnerYet() {
        scoreNTimes(3, game, new Player("A"));

        assertThat(game.isFinished()).isFalse();
    }

    @Test
    void playerWon() {
        game.playerScores(new Player("A"));         // serving right
        scoreNTimes(4, game, new Player("A"));

        assertThat(game.isFinished()).isTrue();
        assertThat(game.getWinner()).isEqualTo(new Player("A"));
    }

    @Test
    void noWinnerWithOnePointDifference() {
        scoreNTimes(3, game, new Player("A"));
        scoreNTimes(4, game, new Player("B"));

        assertThat(game.isFinished()).isFalse();
    }

    @Test
    void winnerWithTwoPointDifference() {
        scoreNTimes(3, game, new Player("A"));
        scoreNTimes(5, game, new Player("B"));

        assertThat(game.isFinished()).isTrue();
        assertThat(game.getWinner()).isEqualTo(new Player("B"));

    }

    @Test
    void scoringHadNoEffectWhenAlreadyWon() {
        game.playerScores(new Player("A"));         // serving right
        scoreNTimes(4, game, new Player("A"));

        assertThat(game.isFinished()).isTrue();
        assertThat(game.getWinner()).isEqualTo(new Player("A"));

        Score scoreWhenFinished = game.getCurrentScore();

        game.playerScores(new Player("A"));

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