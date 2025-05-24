package pl.edu.dik.domain.model.account;

import lombok.*;

import java.util.UUID;

@Getter @Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    private UUID id;
    private String login;
    private int rentalCount;
}
