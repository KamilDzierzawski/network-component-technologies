package pl.edu.dik.domain.model.account;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Getter @Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account implements UserDetails {

    private UUID id;

    private String login;

    private int rentalCount;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> "ROLE_CLIENT");
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
