package co.istad.bankingapplication.mapper;

import co.istad.bankingapplication.domain.AccountType;
import co.istad.bankingapplication.feature.account.dto.AccountCreateRequest;
import co.istad.bankingapplication.feature.account.dto.AccountResponse;
import co.istad.bankingapplication.feature.accountType.dto.AccountTypeCreateRequest;
import co.istad.bankingapplication.feature.accountType.dto.AccountTypeResponse;
import co.istad.bankingapplication.feature.accountType.dto.AccountTypeUpdateRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountTypeMapper {

    // Find all list of Account Type
    // source = AccountType
    // target = AccountTypeResponse
    List<AccountTypeResponse> toAccountTypeResponseList(List<AccountType> accountTypes);

    // Create new Account Type
    // source = AccountTypeCreateRequest
    // target = AccountType
    AccountType fromAccountTypeCreateRequest(AccountTypeCreateRequest accountTypeCreateRequest);

    // Update Account Type
    AccountType fromAccountTypeUpdateRequest(AccountTypeUpdateRequest accountTypeUpdateRequest);

    // Partially map Data Update
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void fromAccountTypeUpdateRequest(AccountTypeUpdateRequest accountTypeUpdateRequest,
                                      @MappingTarget AccountType accountType);

    // Response a single Account Type when create and Update
    // source = AccountType
    // target = AccountTypeResponse
    AccountTypeResponse toAccountTypeResponse(AccountType accountType);
}
