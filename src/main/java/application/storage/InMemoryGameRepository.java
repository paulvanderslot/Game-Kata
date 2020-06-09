package application.storage;

import static java.util.stream.Collectors.toList;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.Game;
import domain.GameId;
import domain.GameRepository;

public class InMemoryGameRepository implements GameRepository {

    private final Map<GameId, Game> ongoingGames = new HashMap<>();

    @Override
    public Game findGame(GameId gameId) {
        return ongoingGames.computeIfAbsent(gameId, Game::new);
    }

    @Override public List<Game> findAll() {
        Comparator<Game> byId = Comparator.comparing(game -> game.id().toString());
        return ongoingGames.values().stream().sorted(byId).collect(toList());
    }
}
