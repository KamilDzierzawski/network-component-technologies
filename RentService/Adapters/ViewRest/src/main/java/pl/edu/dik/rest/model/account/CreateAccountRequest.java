package pl.edu.dik.rest.model.account;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.UUID;

@Data
public class CreateAccountRequest {

    @NotNull
    @NotEmpty
    @Size(min = 4, max = 40, message = "Login must be between 4 and 40 characters")
    private String login;
}
