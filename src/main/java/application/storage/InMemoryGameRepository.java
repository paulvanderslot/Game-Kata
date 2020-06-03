package application.storage;

import java.util.HashMap;
import java.util.Map;

import domain.Game;
import domain.GameId;
import domain.GameRepository;

public class InMemoryGameRepository implements GameRepository {

    private final Map<GameId, Game> ongoingGames = new HashMap<>();

    @Override
    public Game findGame(GameId gameId) {
        return ongoingGames.computeIfAbsent(gameId, id -> new Game());

    }
}
