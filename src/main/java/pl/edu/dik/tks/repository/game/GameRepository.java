package pl.edu.dik.tks.repository.game;

import pl.edu.dik.tks.model.game.Game;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GameRepository {
    Game save(Game game);

    Optional<Game> findById(Object id);

    List<Game> findAll();

    void deleteById(Object id);
}
