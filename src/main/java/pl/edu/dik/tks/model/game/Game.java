package pl.edu.dik.tks.model.game;

import lombok.*;
import org.bson.codecs.pojo.annotations.BsonProperty;
import pl.edu.dik.tks.model.UuidIdentifiedEntity;

@Getter @Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Game extends UuidIdentifiedEntity {

    @BsonProperty("name")
    private String name;

    @BsonProperty("price_per_day")
    private int pricePerDay;

    @BsonProperty("rental_status_count")
    private int rentalStatusCount;

    @BsonProperty("min_players")
    private int minPlayers;

    @BsonProperty("max_players")
    private int maxPlayers;
}
