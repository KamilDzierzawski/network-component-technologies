package pl.edu.dik.userrabbitmqadapters.aggregate;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pl.edu.dik.domain.model.account.Account;
import pl.edu.dik.ports.infrastructure.accountevent.CreateAccountEventPort;
import pl.edu.dik.userrabbitmqadapters.model.AccountEnt;
import pl.edu.dik.userrabbitmqadapters.publisher.CreateAccountPublisher;


@Component
@RequiredArgsConstructor
public class AccountPublisherAdapter implements CreateAccountEventPort {

    private final CreateAccountPublisher createAccountPublisher;
    private final ModelMapper modelMapper;

    @Override
    public void publish(Account object) {
        createAccountPublisher.publish(modelMapper.map(object, AccountEnt.class));
    }
}
