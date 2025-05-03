package pl.edu.dik.soap.endpoint;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import pl.edu.dik.domain.model.game.Game;
import pl.edu.dik.ports._interface.GameService;
import pl.edu.dik.ports.exception.business.GameNotFoundException;
import pl.edu.dik.ports.exception.business.GameRentedException;
import pl.edu.dik.ports.exception.business.IncorrectPlayerNumberException;
import pl.edu.dik.soap.model.game.*;

import java.util.List;

@Endpoint
@RequiredArgsConstructor
public class GameEndpoint {

    private final GameService gameService;
    private final ModelMapper modelMapper;
    private static final String NAMESPACE_URI = "http://viewsoap.adapters.soap/games";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createGameRequest")
    @ResponsePayload
    public GameResponse createGame(@RequestPayload CreateGameRequest createGameRequest) throws IncorrectPlayerNumberException {

        Game game = modelMapper.map(createGameRequest, Game.class);
        Game createdGame = gameService.createGame(game);

        return modelMapper.map(createdGame, GameResponse.class);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getGameByIdRequest")
    @ResponsePayload
    public GameResponse getGameById(@RequestPayload GetGameByIdRequest request) throws GameNotFoundException {
        Game foundGame = gameService.findGameById(request.getId());
        return modelMapper.map(foundGame, GameResponse.class);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllGamesRequest")
    @ResponsePayload
    public GetAllGamesResponse getAllGames(@RequestPayload GetAllGamesRequest request) {
        List<Game> games = gameService.getAllGames();
        List<GameResponse> responses = games.stream()
                .map(game -> modelMapper.map(game, GameResponse.class))
                .toList();

        GetAllGamesResponse response = new GetAllGamesResponse();
        response.setGames(responses);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateGameRequest")
    @ResponsePayload
    public GameResponse updateGame(@RequestPayload UpdateGameRequest updateGameRequest) throws GameNotFoundException {
        Game game = modelMapper.map(updateGameRequest, Game.class);
        Game updatedGame = gameService.updateGame(game);
        return modelMapper.map(updatedGame, GameResponse.class);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteGameRequest")
    @ResponsePayload
    public void deleteGame(@RequestPayload DeleteGameRequest request) throws GameRentedException, GameNotFoundException {
        gameService.deleteGameById(request.getId());
    }
}
