package pl.edu.dik.userrabbitmqadapters.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.edu.dik.userrabbitmqadapters.model.AccountEnt;

import java.io.Serializable;

@AllArgsConstructor
@Setter @Getter
public class AccountEntMessage implements Serializable {

    private AccountEnt accountEnt;


}
