package pl.edu.dik.tks.model.rent;

import lombok.Getter;
import lombok.Setter;
import pl.edu.dik.tks.model.UuidIdentifiedEntity;
import pl.edu.dik.tks.model.game.Game;
import pl.edu.dik.tks.model.account.Account;

import java.time.LocalDate;
import java.util.UUID;

@Setter
@Getter
public class Rent extends UuidIdentifiedEntity {
    private LocalDate startDate;

    private LocalDate endDate;

    private Account account;

    private Game game;

    private int rentalPrice;

    public Rent(UUID id, LocalDate startDate, LocalDate endDate, Account account, Game game, int rentalPrice) {
        super(id);
        this.startDate = startDate;
        this.endDate = endDate;
        this.account = account;
        this.game = game;
        this.rentalPrice = rentalPrice;
    }
}
