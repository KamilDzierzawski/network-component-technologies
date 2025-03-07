package pl.edu.dik.tks.model.account;

import lombok.*;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonIgnore;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.edu.dik.tks.model.UuidIdentifiedEntity;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Getter @Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Account extends UuidIdentifiedEntity { //extends AbstractEntity

    @BsonProperty("first_name")
    private String firstName;

    @BsonProperty("last_name")
    private String lastName;

    @BsonProperty("role")
    private Role role;

    @BsonProperty("is_enable")
    private boolean isEnable;

    @BsonProperty("login")
    private String login;

    @BsonProperty("password")
    private String password;

    @BsonProperty("rental_count")
    private int rentalCount;

}
