package pl.edu.dik.userrabbitmqadapters.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AccountEnt implements Serializable {
    private String login;
}
