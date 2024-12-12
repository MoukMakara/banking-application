package co.istad.bankingapplication.mapper;

import co.istad.bankingapplication.domain.Account;
import co.istad.bankingapplication.feature.account.dto.AccountCreateRequest;
import co.istad.bankingapplication.feature.account.dto.AccountResponse;
import co.istad.bankingapplication.feature.account.dto.AccountUpdateRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    // map Account to AccountResponse
    // source = Account
    // target = AccountResponse
//    @Mapping(source = "accountType.alias", target = "accountTypeAlias")
    AccountResponse toAccountResponse(Account account);

    // map DTO to domain model
    // source = AccountCreateRequest
    // target = Account
    Account fromAccountCreateRequest(AccountCreateRequest accountCreateRequest);

    Account fromAccountUpdateRequest(AccountUpdateRequest accountUpdateRequest);

    // partial update
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void fromAccountUpdateRequest(AccountUpdateRequest accountUpdateRequest,
                                  @MappingTarget Account account);
}
