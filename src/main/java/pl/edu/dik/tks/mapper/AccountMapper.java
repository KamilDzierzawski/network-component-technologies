package pl.edu.dik.tks.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import pl.edu.dik.tks.auth.dto.RegisterAccountRequest;
import pl.edu.dik.tks.model.account.Account;

@Component
@Mapper(componentModel = "spring")
public interface AccountMapper {
    Account toAccount(RegisterAccountRequest registerAccountRequest);
}
