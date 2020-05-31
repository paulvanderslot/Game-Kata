import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ScoreDeterminerTest {


    @Test
    void canScore(){
        ConsoleAdapter consoleAdapter = new ConsoleAdapter();
        consoleAdapter.score("A");

        assertThat(consoleAdapter.getPrintedScore()).isEqualTo("Player A scores Score [1-0]");
    }

    @Test
    void canScoreTwice(){
        ConsoleAdapter consoleAdapter = new ConsoleAdapter();
        consoleAdapter.score("A");
        consoleAdapter.score("a");

        assertThat(consoleAdapter.getPrintedScore()).isEqualTo("Player A scores Score [2-0]");
    }

    @Test
    void canScoreBoth(){
        ConsoleAdapter consoleAdapter = new ConsoleAdapter();
        consoleAdapter.score("A");
        consoleAdapter.score("B");

        assertThat(consoleAdapter.getPrintedScore()).isEqualTo("Player B scores Score [1-1]");
    }

    @Test
    void noWinnerYet(){
        ConsoleAdapter consoleAdapter = new ConsoleAdapter();
        consoleAdapter.score("A");
        consoleAdapter.score("a");
        consoleAdapter.score("a");

        assertThat(consoleAdapter.isFinished()).isFalse();
    }

    @Test
    void playerWon(){
        ConsoleAdapter consoleAdapter = new ConsoleAdapter();
        consoleAdapter.score("A");
        consoleAdapter.score("a");
        consoleAdapter.score("a");
        consoleAdapter.score("a");

        assertThat(consoleAdapter.isFinished()).isTrue();
    }

    @Test
    void noWinnerWithOnePointDifference(){
        ConsoleAdapter consoleAdapter = new ConsoleAdapter();
        consoleAdapter.score("A");
        consoleAdapter.score("a");
        consoleAdapter.score("a");
        consoleAdapter.score("b");
        consoleAdapter.score("b");
        consoleAdapter.score("b");
        consoleAdapter.score("b");

        assertThat(consoleAdapter.isFinished()).isFalse();
    }

    @Test
    void winnerWithTwoPointDifference(){
        ConsoleAdapter consoleAdapter = new ConsoleAdapter();
        consoleAdapter.score("A");
        consoleAdapter.score("a");
        consoleAdapter.score("a");
        consoleAdapter.score("b");
        consoleAdapter.score("b");
        consoleAdapter.score("b");
        consoleAdapter.score("b");
        consoleAdapter.score("b");

        assertThat(consoleAdapter.isFinished()).isTrue();
    }

}