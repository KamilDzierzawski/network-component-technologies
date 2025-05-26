package pl.edu.dik.rabbitmq.model;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AccountMessage {
    private String login;
}
