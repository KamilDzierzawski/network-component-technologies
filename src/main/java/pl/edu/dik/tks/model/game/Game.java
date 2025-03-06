package pl.edu.dik.tks.model.game;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bson.codecs.pojo.annotations.BsonDiscriminator;
import pl.edu.dik.tks.model.UuidIdentifiedEntity;

import java.util.UUID;

@Getter
@ToString(callSuper = true)
@BsonDiscriminator(key = "type")
public abstract class Game extends UuidIdentifiedEntity {
    @Setter
    private String name;

    //private final GameType type;

    @Setter
    private int pricePerDay;

    @Setter
    private int rentalStatusCount;

    public Game(UUID id, String name, /*GameType type,*/ int pricePerDay, int rentalStatusCount) {
        super(id);
        this.name = name;
        //this.type = type;
        this.pricePerDay = pricePerDay;
        this.rentalStatusCount = rentalStatusCount;
    }
}
