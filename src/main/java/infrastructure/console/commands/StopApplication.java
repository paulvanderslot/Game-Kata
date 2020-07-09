package infrastructure.console.commands;

import infrastructure.console.ScoreKeeper;

public class StopApplication implements GameCommand {

    private final ScoreKeeper scoreKeeper;

    public StopApplication(ScoreKeeper scoreKeeper) {
        this.scoreKeeper = scoreKeeper;
    }

    @Override public void execute() {
        scoreKeeper.stop();
    }
}
