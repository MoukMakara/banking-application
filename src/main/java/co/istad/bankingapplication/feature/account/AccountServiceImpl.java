package co.istad.bankingapplication.feature.account;

import co.istad.bankingapplication.domain.Account;
import co.istad.bankingapplication.domain.AccountType;
import co.istad.bankingapplication.domain.User;
import co.istad.bankingapplication.feature.account.dto.AccountCreateRequest;
import co.istad.bankingapplication.feature.account.dto.AccountResponse;
import co.istad.bankingapplication.feature.accountType.AccountTypeRepository;
import co.istad.bankingapplication.feature.user.UserRepository;
import co.istad.bankingapplication.mapper.AccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{

    private final AccountTypeRepository accountTypeRepository;
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Override
    public AccountResponse createAccount(AccountCreateRequest accountCreateRequest) {

        // validate AccountType
        AccountType accountType = accountTypeRepository.findByAlias(accountCreateRequest.accountTypeAlias())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Account Type %s not found" ,accountCreateRequest.accountTypeAlias())
                ));

        // validate User
        User user = userRepository.findByUuid(accountCreateRequest.userUuid())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("User %s not found" ,accountCreateRequest.userUuid())
                ));

        // validate ActNo
        if (accountRepository.existsByActNo(accountCreateRequest.actNo())){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    String.format("Account Number %s already exist" ,accountCreateRequest.actNo())
            );
        }

        // validate Alias
        if (accountRepository.existsByAlias(accountCreateRequest.alias())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    String.format("Account Alias %s already exist" ,accountCreateRequest.alias())
            );
        }

        // validate Balance
        if (accountCreateRequest.balance().compareTo(BigDecimal.valueOf(10)) < 0){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    String.format("Balance should be greater than 10$" ,accountCreateRequest.balance())
            );
        }


        // Transfer DTO to domain model

        Account account = accountMapper.fromAccountCreateRequest(accountCreateRequest);
//        account.setAlias(accountCreateRequest.alias());
//        account.setActNo(accountCreateRequest.actNo());
//        account.setBalance(accountCreateRequest.balance());
        account.setAccountType(accountType);
        account.setUser(user);

        // Auto generate data
        account.setIsHidden(false);
        account.setActName(user.getName());
        account.setTransferLimit(BigDecimal.valueOf(1000));

        // save new account into database and get back last data back
        accountRepository.save(account);

        // Transfer domain model to DTO
        return accountMapper.toAccountResponse(account);
    }

    @Override
    public Page<AccountResponse> findAllAccounts(int pageNumber, int pageSize) {
        Sort sortById = Sort.by(Sort.Direction.DESC, "id");
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById);

        Page<Account> accounts = accountRepository.findAll(pageRequest);

        return accounts.map(accountMapper::toAccountResponse);
    }

    @Override
    public AccountResponse findAccountByActNo(String actNo) {
        Account account = accountRepository.findByActNo(actNo)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, String.format("Account %s not found" ,actNo)
                ));

        return accountMapper.toAccountResponse(account);
    }
}
