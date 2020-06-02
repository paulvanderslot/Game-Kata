package application;

import domain.Game;
import domain.GameRepository;

public class InMemoryGameRepository implements GameRepository {

    private Game game = new Game();

    @Override
    public Game findGame(){
        return game;
    }
}
