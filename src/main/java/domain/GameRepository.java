package domain;

import java.util.List;
import java.util.Optional;

public interface GameRepository {

    GameId nextId();

    void addGame(Game game);

    Optional<Game> findGame(GameId gameId);

    List<Game> findAll();
}
