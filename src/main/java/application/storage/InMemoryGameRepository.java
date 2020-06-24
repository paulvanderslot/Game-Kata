package application.storage;

import static java.util.stream.Collectors.toList;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import domain.Game;
import domain.GameId;
import domain.GameRepository;

public class InMemoryGameRepository implements GameRepository {

    private final Map<GameId, Game> ongoingGames = new HashMap<>();
    private static AtomicInteger idCounter = new AtomicInteger(1);

    @Override
    public GameId nextId() {
        return new GameId("" + idCounter.getAndIncrement());
    }

    @Override public void addGame(Game game) {
        ongoingGames.put(game.id(), game);
    }

    @Override
    public Optional<Game> findGame(GameId gameId) {
        if (ongoingGames.containsKey(gameId)) {
            return Optional.of(ongoingGames.get(gameId));
        }
        return Optional.empty();
    }

    @Override public List<Game> findAll() {
        Comparator<Game> byId = Comparator.comparing(game -> game.id().toString());
        return ongoingGames.values().stream().sorted(byId).collect(toList());
    }
}
