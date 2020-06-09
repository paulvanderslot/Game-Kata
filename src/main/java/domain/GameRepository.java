package domain;

import java.util.List;

public interface GameRepository {

    Game findGame(GameId gameId);

    List<Game> findAll();
}
