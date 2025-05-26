package pl.edu.dik.rabbitmq.model.account;

import lombok.*;
import org.bson.codecs.pojo.annotations.BsonProperty;
import pl.edu.dik.rabbitmq.model.AbstractEntityEnt;

import java.util.UUID;

@Getter @Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class AccountEnt extends AbstractEntityEnt {

    @BsonProperty("login")
    private String login;

    @BsonProperty("rental_count")
    private int rentalCount;

    public AccountEnt(UUID id, String login, int rentalCount) {
        super(id);
        this.login = login;
        this.rentalCount = rentalCount;
    }
}
