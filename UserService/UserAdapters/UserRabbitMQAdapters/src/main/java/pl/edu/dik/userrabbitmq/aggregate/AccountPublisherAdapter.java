package pl.edu.dik.userrabbitmq.aggregate;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pl.edu.dik.domain.model.account.Account;
import pl.edu.dik.ports.infrastructure.accountevent.CreateAccountEventPort;
import pl.edu.dik.userrabbitmq.model.AccountMessage;
import pl.edu.dik.userrabbitmq.publisher.CreateAccountPublisher;


@Component
@RequiredArgsConstructor
public class AccountPublisherAdapter implements CreateAccountEventPort {

    private final CreateAccountPublisher createAccountPublisher;
    private final ModelMapper modelMapper;

    @Override
    public void publish(Account object) {
        createAccountPublisher.publish(modelMapper.map(object, AccountMessage.class));
    }
}
