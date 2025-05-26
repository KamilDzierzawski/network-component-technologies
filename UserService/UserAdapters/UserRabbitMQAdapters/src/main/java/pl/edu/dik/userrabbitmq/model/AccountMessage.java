package pl.edu.dik.userrabbitmq.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AccountMessage implements Serializable {
    private String login;
}
