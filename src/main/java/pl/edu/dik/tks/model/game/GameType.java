package pl.edu.dik.tks.model.game;

import org.bson.codecs.pojo.annotations.BsonProperty;

public enum GameType {
    @BsonProperty("BOARD_GAME")
    BOARD_GAME,

    @BsonProperty("VIDEO_GAME")
    VIDEO_GAME
}
