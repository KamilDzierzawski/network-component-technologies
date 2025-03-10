package pl.edu.dik.tks.auth.dto;

import lombok.Data;

@Data
public class RegisterResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String login;
    private String role;
}
