//package pl.edu.dik.tks.service;
//
//import com.mongodb.MongoException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import pl.edu.dik.tks.exception.DatabaseException;
//import pl.edu.dik.tks.exception.business.GameNotFoundException;
//import pl.edu.dik.tks.model.game.Game;
//import pl.edu.dik.tks.repository.game.MongoGameRepository;
//
//import java.util.List;
//import java.util.UUID;
//
//@Service
//public class GameService {
//    private final MongoGameRepository gameRepository;
//
//    @Autowired
//    public GameService(MongoGameRepository gameRepository) {
//        this.gameRepository = gameRepository;
//    }
//
//    public Game createGame(Game game) throws DatabaseException {
//        try {
//            gameRepository.insert(game);
//            return game;
//        } catch (MongoException e) {
//            throw new DatabaseException("Database error");
//        }
//    }
//
//    public Game findGameById(UUID id) throws GameNotFoundException {
//        return gameRepository.findById(id)
//                .orElseThrow(() -> new GameNotFoundException("Game with ID " + id + " not found"));
//    }
//
//    public List<Game> getAllGames() throws DatabaseException {
//        try {
//            return gameRepository.findAll();
//        } catch (Exception e) {
//            throw new DatabaseException("Database error");
//        }
//    }
//
//    public Game updateGame(Game game) throws DatabaseException, GameNotFoundException {
//        try {
//            // Check if the game exists in the repository
//            Game foundGame = gameRepository.findById(game.getId())
//                    .orElseThrow(() -> new GameNotFoundException("Game with ID " + game.getId() + " not found"));
//
//            if (!game.getName().equals(foundGame.getName())) {
//                foundGame.setName(game.getName());
//            }
//            if (game.getPricePerDay() != foundGame.getPricePerDay()) {
//                foundGame.setPricePerDay(game.getPricePerDay());
//            }
//
//            return gameRepository.update(foundGame);
//        } catch (GameNotFoundException e) {
//            throw e;
//        } catch (Exception e) {
//            throw new DatabaseException("Database error");
//        }
//    }
//
//    /*public void deleteGameById(UUID id) {
//        try {
//            if (rentRepository.isGameRented(id)) {
//                throw new LogicException("Game with ID " + id + " is rented.");
//            }
//            if (!boardGameRepository.deleteById(id)) {
//                throw new NotFoundException("Game with ID " + id + " not found.");
//            }
//        } catch (LogicException | NotFoundException e) {
//            throw e;
//        } catch (Exception e) {
//            throw new DatabaseException("Database error");
//        }
//    }*/
//}
