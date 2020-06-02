package domain;

public class GameService {

    private final GameRepository repository;

    public GameService(GameRepository repository) {
        this.repository = repository;
    }

    public void scored(String gameId, Player player) {
        Game game = repository.findGame();
        game.playerScores(player);
    }

    public Score getScore(String gameId) {
        Game game = repository.findGame();
        return game.getCurrentScore();
    }

    public boolean isFinished(String gameId) {
        Game game = repository.findGame();
        return game.isFinished();
    }

    public Player getWinner(String gameId) {
        Game game = repository.findGame();

        return game.getWinner();
    }

}
