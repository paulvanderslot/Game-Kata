package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class GameTest {

    @Test
    void canScore() {
        Game game = new Game();
        game.playerScores(Player.A);

        Assertions.assertThat(game.getCurrentScore()).isEqualTo(new Score(1, 0, Player.A));
    }

    @Test
    void canScoreTwice() {
        Game game = new Game();
        game.playerScores(Player.A);
        game.playerScores(Player.A);

        Assertions.assertThat(game.getCurrentScore()).isEqualTo(new Score(2, 0, Player.A));
    }

    @Test
    void canScoreBoth() {
        Game game = new Game();
        game.playerScores(Player.A);
        game.playerScores(Player.B);

        Assertions.assertThat(game.getCurrentScore()).isEqualTo(new Score(1, 1, Player.B));
    }

    @Test
    void noWinnerYet() {
        Game game = new Game();
        game.playerScores(Player.A);
        game.playerScores(Player.A);
        game.playerScores(Player.A);

        Assertions.assertThat(game.isFinished()).isFalse();
    }

    @Test
    void playerWon() {
        Game game = new Game();
        game.playerScores(Player.A);
        game.playerScores(Player.A);
        game.playerScores(Player.A);
        game.playerScores(Player.A);

        Assertions.assertThat(game.isFinished()).isTrue();
    }

    @Test
    void noWinnerWithOnePointDifference() {
        Game game = new Game();
        game.playerScores(Player.A);
        game.playerScores(Player.A);
        game.playerScores(Player.A);
        game.playerScores(Player.B);
        game.playerScores(Player.B);
        game.playerScores(Player.B);
        game.playerScores(Player.B);

        Assertions.assertThat(game.isFinished()).isFalse();
    }

    @Test
    void winnerWithTwoPointDifference() {
        Game game = new Game();
        game.playerScores(Player.A);
        game.playerScores(Player.A);
        game.playerScores(Player.A);
        game.playerScores(Player.B);
        game.playerScores(Player.B);
        game.playerScores(Player.B);
        game.playerScores(Player.B);
        game.playerScores(Player.B);

        Assertions.assertThat(game.isFinished()).isTrue();
    }

}