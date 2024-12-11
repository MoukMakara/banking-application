package co.istad.bankingapplication.feature.accountType;

import co.istad.bankingapplication.domain.AccountType;

import co.istad.bankingapplication.feature.accountType.dto.AccountTypeCreateRequest;
import co.istad.bankingapplication.feature.accountType.dto.AccountTypeResponse;
import co.istad.bankingapplication.feature.accountType.dto.AccountTypeUpdateRequest;
import co.istad.bankingapplication.mapper.AccountTypeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
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

    @Override
    public AccountTypeResponse updateAccountTypeByAlias(String alias, AccountTypeUpdateRequest accountTypeUpdateRequest) {

        AccountType accountType = accountTypeRepository.findByAlias(alias)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Account type %s not found", alias)));

        // accountType.setIsDeleted(accountType.getIsDeleted());
        // accountType.setDescription(accountType.getDescription());

        log.info("Before map: {}, {}" , accountType.getDescription(), accountType.getIsDeleted());
        // use map to setIsDeleted and setDescription
        accountTypeMapper.fromAccountTypeUpdateRequest(accountTypeUpdateRequest, accountType);
        log.info("Before map: {}, {}" , accountType.getDescription(), accountType.getIsDeleted());

        accountTypeRepository.save(accountType);

        return accountTypeMapper.toAccountTypeResponse(accountType);
    }
}
