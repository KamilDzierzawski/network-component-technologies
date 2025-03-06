package pl.edu.dik.tks.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.dik.tks.dto.CreateGameRequest;
import pl.edu.dik.tks.dto.GameResponse;
import pl.edu.dik.tks.exception.DatabaseException;
import pl.edu.dik.tks.model.game.Game;
import pl.edu.dik.tks.service.GameService;

@RestController
@RequestMapping("/api/games")
@RequiredArgsConstructor
public class GameController {

    private final ModelMapper modelMapper;
    private final GameService gameService;

    @PostMapping()
    public ResponseEntity<GameResponse> create(@Valid @RequestBody CreateGameRequest createGameRequest) throws DatabaseException {
        Game game = modelMapper.map(createGameRequest, Game.class);
        Game createdGame = gameService.createGame(game);
        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(createdGame, GameResponse.class));
    }
}
