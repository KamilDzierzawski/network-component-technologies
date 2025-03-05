package pl.edu.dik.tks.model.game;

import lombok.Getter;

import java.util.UUID;

@Getter
public class VideoGame extends Game {

    private final String platform;

    public VideoGame(UUID id, String name, int pricePerDay, int rentalStatusCount, String platform) {
        super(id, name, GameType.VIDEO_GAME, pricePerDay, rentalStatusCount);
        this.platform = platform;
    }
}
