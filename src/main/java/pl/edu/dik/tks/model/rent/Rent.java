package pl.edu.dik.tks.model.rent;

import lombok.*;
import org.bson.codecs.pojo.annotations.BsonProperty;
import pl.edu.dik.tks.model.UuidIdentifiedEntity;
import pl.edu.dik.tks.model.game.Game;
import pl.edu.dik.tks.model.account.Account;

import java.time.LocalDate;

@Getter @Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Rent extends UuidIdentifiedEntity {

    @BsonProperty("start_date")
    private LocalDate startDate;

    @BsonProperty("end_date")
    private LocalDate endDate;

    @BsonProperty("account")
    private Account account;

    @BsonProperty("game")
    private Game game;

    @BsonProperty("rental_price")
    private int rentalPrice;
}
