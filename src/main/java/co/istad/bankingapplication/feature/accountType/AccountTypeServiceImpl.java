package co.istad.bankingapplication.feature.accountType;

import co.istad.bankingapplication.domain.AccountType;

import co.istad.bankingapplication.feature.accountType.dto.AccountTypeCreateRequest;
import co.istad.bankingapplication.feature.accountType.dto.AccountTypeResponse;
import co.istad.bankingapplication.mapper.AccountTypeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountTypeServiceImpl implements AccountTypeService{

    private final AccountTypeRepository accountTypeRepository;
    private final AccountTypeMapper accountTypeMapper;

    @Override
    public List<AccountTypeResponse> findAllAccountTypes() {
        Sort sortById = Sort.by(Sort.Direction.DESC, "id");
        List<AccountType> accountTypes = accountTypeRepository.findAll(sortById);

        return accountTypeMapper.toAccountTypeResponseList(accountTypes);
    }

    @Override
    public AccountTypeResponse createAccountType(AccountTypeCreateRequest accountTypeCreateRequest) {

        // validate alias
        if (accountTypeRepository.existsByAlias(accountTypeCreateRequest.alias())){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    String.format("Account type %s already exists", accountTypeCreateRequest.alias()));
        }
        // Transfer DTO to domain model using mapper
        AccountType accountType = accountTypeMapper.fromAccountTypeCreateRequest(accountTypeCreateRequest);
        accountType.setIsDeleted(false);

        // save new Account Type into accountTypeRepository
        accountTypeRepository.save(accountType);

        // Transfer domain model to DTO to be Response data
        return accountTypeMapper.toAccountTypeResponse(accountType);
    }
}
