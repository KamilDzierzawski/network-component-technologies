package pl.edu.dik.tks.model.game;

import lombok.Getter;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;
import pl.edu.dik.tks.model.UuidIdentifiedEntity;

import java.util.UUID;

@Getter
public class BoardGame extends Game {

    @BsonProperty
    private final int minPlayers;

    private final int maxPlayers;

    public BoardGame(UUID id, String name, int pricePerDay, int rentalStatusCount, int minPlayers, int maxPlayers) {
        super(id, name, /*GameType.BOARD_GAME,*/ pricePerDay, rentalStatusCount);
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
    }
}
