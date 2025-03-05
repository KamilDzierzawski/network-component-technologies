package pl.edu.dik.tks.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString(callSuper = true)
public class Account extends AbstractEntity implements UserDetails {
    
    private String firstName;

    private String lastName;

    private Role role;

    private boolean isEnable;

    private String login;

    private String password;

    public Account(UUID id, String firstName, String lastName, Role role, boolean isEnable, String login, String password) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.isEnable = isEnable;
        this.login = login;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> "ROLE_" + role.name());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnable;
    }
}
