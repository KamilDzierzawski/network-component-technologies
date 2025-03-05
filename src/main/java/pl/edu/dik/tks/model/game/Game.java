package pl.edu.dik.tks.model.game;

public abstract class Game extends AbstractEntity {
    @Getter @Setter
    private String name;

    @Getter
    private final GameType type;

    @Getter @Setter
    private int pricePerDay;

    @Getter @Setter
    private int rentalStatusCount;
}
