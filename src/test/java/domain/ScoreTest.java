package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ScoreTest {

    @Test
    void playerBGetsServingRight() {
        Score intialScore = new Score(new Player("A"), new Player("B"), 0, 0, new Player("A"));

        Score playerBHasServingRight = intialScore.scores(new Player("B"));

        assertThat(playerBHasServingRight).isEqualTo(new Score(new Player("A"), new Player("B"), 0, 0, new Player("B")));
    }

    @Test
    void playerAScoresPointBecauseHeHasServingRight() {
        Score intialScore = new Score(new Player("A"), new Player("B"), 0, 0, new Player("A"));

        Score playerBHasServingRight = intialScore.scores(new Player("A"));

        assertThat(playerBHasServingRight).isEqualTo(new Score(new Player("A"), new Player("B"), 1, 0, new Player("A")));
    }

}