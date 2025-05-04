package pl.edu.dik.rest.model.account;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public class CreateAccountRequest {

    @NotNull
    private UUID id;

    @NotNull
    @NotEmpty
    @Size(min = 4, max = 20, message = "Login must be between 4 and 20 characters")
    private String login;
}
