package pl.edu.dik.tks.auth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RegisterAccountRequest {
    private String firstName;
    private String lastName;
    private String login;
    private String password;
}