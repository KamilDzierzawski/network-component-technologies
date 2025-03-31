package pl.edu.dik.soap.endpoint;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import pl.edu.dik.domain.model.game.Game;
import pl.edu.dik.ports._interface.GameService;
import pl.edu.dik.ports.exception.business.IncorrectPlayerNumberException;
import pl.edu.dik.soap.model.game.CreateGameRequest;
import pl.edu.dik.soap.model.game.GameResponse;

@Endpoint
@RequiredArgsConstructor
public class GameEndpoint {

    private final GameService gameService;
    private final ModelMapper modelMapper;


    @PayloadRoot(namespace = "http://viewsoap.adapters.tks/games", localPart = "createGameRequest")
    @ResponsePayload
    public GameResponse createGame(@RequestPayload CreateGameRequest createGameRequest) throws IncorrectPlayerNumberException {

        Game game = modelMapper.map(createGameRequest, Game.class);
        Game createdGame = gameService.createGame(game);

        return modelMapper.map(createdGame, GameResponse.class);
    }

}
