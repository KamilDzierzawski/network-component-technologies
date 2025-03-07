package pl.edu.dik.tks.repository.game;

import pl.edu.dik.tks.model.game.Game;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GameRepository {
    Game insert(Game game);

    Optional<Game> findById(Object id);

    List<Game> findAll();

    Game update(Game game);

    void deleteById(Object id);
}
