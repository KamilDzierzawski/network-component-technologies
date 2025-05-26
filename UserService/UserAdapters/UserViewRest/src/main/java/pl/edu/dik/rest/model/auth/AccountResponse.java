package pl.edu.dik.rest.model.auth;

import lombok.*;

import java.util.UUID;

@Data
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponse {
    private UUID id;
    private String firstName;
    private String lastName;
    private String login;
    private int rentalCount;
    private boolean isEnable;
}
