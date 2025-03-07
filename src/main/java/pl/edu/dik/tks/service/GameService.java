package pl.edu.dik.tks.service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.dik.tks.exception.business.GameNotFoundException;
import pl.edu.dik.tks.exception.business.IncorrectPlayerNumberException;
import pl.edu.dik.tks.model.game.Game;
import pl.edu.dik.tks.repository.game.MongoGameRepository;

import java.util.List;

@Service
public class GameService {
    private final MongoGameRepository gameRepository;

    @Autowired
    public GameService(MongoGameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Game createGame(Game game) throws IncorrectPlayerNumberException {
        if (game.getMaxPlayers() < game.getMinPlayers()) {
            throw new IncorrectPlayerNumberException("Max players cannot be less than min players");
        }
        return gameRepository.save(game);
    }

    public Game findGameById(ObjectId id) throws GameNotFoundException {
        return gameRepository.findById(id)
                .orElseThrow(() -> new GameNotFoundException("Game with ID " + id + " not found"));
    }

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public Game updateGame(Game game) throws GameNotFoundException {
        Game foundGame = gameRepository.findById(game.getId())
                .orElseThrow(() -> new GameNotFoundException("Game with ID " + game.getId() + " not found"));

        if (!game.getName().equals(foundGame.getName())) {
            foundGame.setName(game.getName());
        }
        if (game.getPricePerDay() != foundGame.getPricePerDay()) {
            foundGame.setPricePerDay(game.getPricePerDay());
        }

        return gameRepository.update(foundGame);
    }

    /*public void deleteGameById(UUID id) {
        try {
            if (rentRepository.isGameRented(id)) {
                throw new LogicException("Game with ID " + id + " is rented.");
            }
            if (!boardGameRepository.deleteById(id)) {
                throw new NotFoundException("Game with ID " + id + " not found.");
            }
        } catch (LogicException | NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new DatabaseException("Database error");
        }
    }*/
}
