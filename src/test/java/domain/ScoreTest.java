package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ScoreTest {

    @Test
    void playerBGetsServingRight() {
        Score intialScore = new Score(0, 0, Player.A);

        Score playerBHasServingRight = intialScore.scores(Player.B);

        assertThat(playerBHasServingRight).isEqualTo(new Score(0, 0, Player.B));
    }

    @Test
    void playerAScoresPointBecauseHeHasServingRight() {
        Score intialScore = new Score(0, 0, Player.A);

        Score playerBHasServingRight = intialScore.scores(Player.A);

        assertThat(playerBHasServingRight).isEqualTo(new Score(1, 0, Player.A));
    }

}