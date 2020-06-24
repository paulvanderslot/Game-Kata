package domain;

import java.util.List;
import java.util.Optional;

public class GameService {

    private final GameRepository repository;

    public GameService(GameRepository repository) {
        if (repository == null) {
            throw new IllegalStateException(GameRepository.class.getSimpleName() + " may not be null");
        }
        this.repository = repository;
    }

    public GameId startGame(Player playerOne, Player playerTwo) {
        GameId gameId = repository.nextId();
        repository.addGame(new Game(gameId, playerOne, playerTwo));
        return gameId;
    }

    // Not expose game to the outside? maybe GameSummary?
    public List<Game> ongoingGames() {
        return repository.findAll();
    }

    public boolean gameExists(GameId gameId) {
        Optional<Game> game = repository.findGame(gameId);
        return game.isPresent();
    }

    public void scored(GameId gameId, Player player) {
        Optional<Game> game = repository.findGame(gameId);
        game.ifPresent(g -> g.playerScores(player));
    }

    public Score getScore(GameId gameId) {
        Optional<Game> game = repository.findGame(gameId);
        if (game.isPresent()) {
            return game.get().getCurrentScore();
        }
        throw new IllegalStateException("game does not exist");
    }

    public boolean isFinished(GameId gameId) {
        Optional<Game> game = repository.findGame(gameId);
        if (game.isPresent()) {
            return game.get().isFinished();
        }
        throw new IllegalStateException("game does not exist");
    }

    public Player getWinner(GameId gameId) {
        Optional<Game> game = repository.findGame(gameId);
        if (game.isPresent()) {
            return game.get().getWinner();
        }
        throw new IllegalStateException("game does not exist");
    }

}
