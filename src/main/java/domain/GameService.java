package domain;

public class GameService {

    private final GameRepository repository;

    public GameService(GameRepository repository) {
        if (repository == null) {
            throw new IllegalStateException(GameRepository.class.getSimpleName() + " may not be null");
        }
        this.repository = repository;
    }

    public void scored(GameId gameId, Player player) {
        Game game = repository.findGame(gameId);
        game.playerScores(player);
    }

    public Score getScore(GameId gameId) {
        Game game = repository.findGame(gameId);
        return game.getCurrentScore();
    }

    public boolean isFinished(GameId gameId) {
        Game game = repository.findGame(gameId);
        return game.isFinished();
    }

    public Player getWinner(GameId gameId) {
        Game game = repository.findGame(gameId);

        return game.getWinner();
    }

}
