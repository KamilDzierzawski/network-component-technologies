package pl.edu.dik.userrabbitmq.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import pl.edu.dik.ports._interface.AccountService;
import pl.edu.dik.userrabbitmq.model.AccountMessage;

@Component
@RequiredArgsConstructor
@Slf4j
public class RabbitMQDeleteAccountListener implements DeleteAccountListener {

    private final AccountService accountService;

    @Override
    @RabbitListener(queues = "${rabbitmq.delete.client.queue.name}")
    public void deleteAccount(AccountMessage accountMessage) {
        log.info("Received account deletion request for login: {}", accountMessage.getLogin());
        accountService.deleteByLogin(accountMessage.getLogin());
    }
}
