package domain;

public interface GameRepository {

    Game findGame(GameId gameId);
}
