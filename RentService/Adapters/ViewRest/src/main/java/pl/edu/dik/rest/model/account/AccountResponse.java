package pl.edu.dik.rest.model.account;

import lombok.*;

@Data
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponse {
    private String id;
    private String login;
    private String rentalCount;
}
