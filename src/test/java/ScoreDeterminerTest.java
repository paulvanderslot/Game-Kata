import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;

class ScoreDeterminerTest {


    @Test
    void canScore(){
        ScoreDeterminer scoreDeterminer = new ScoreDeterminer();
        scoreDeterminer.score("A");

        assertThat(scoreDeterminer.getScore()).isEqualTo("Player A scores Score [1-0]");
    }

    @Test
    void canScoreTwice(){
        ScoreDeterminer scoreDeterminer = new ScoreDeterminer();
        scoreDeterminer.score("A");
        scoreDeterminer.score("a");

        assertThat(scoreDeterminer.getScore()).isEqualTo("Player A scores Score [2-0]");
    }

    @Test
    void canScoreBoth(){
        ScoreDeterminer scoreDeterminer = new ScoreDeterminer();
        scoreDeterminer.score("A");
        scoreDeterminer.score("B");

        assertThat(scoreDeterminer.getScore()).isEqualTo("Player B scores Score [1-1]");
    }


}