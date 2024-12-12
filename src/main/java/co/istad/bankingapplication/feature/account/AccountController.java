package co.istad.bankingapplication.feature.account;

import co.istad.bankingapplication.feature.account.dto.AccountCreateRequest;
import co.istad.bankingapplication.feature.account.dto.AccountRenameRequest;
import co.istad.bankingapplication.feature.account.dto.AccountResponse;
import co.istad.bankingapplication.feature.account.dto.AccountTransferLimitRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public AccountResponse createAccount(@Valid @RequestBody AccountCreateRequest accountCreateRequest){
        return accountService.createAccount(accountCreateRequest);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public Page<AccountResponse> findAllAccounts(@RequestParam(required = false, defaultValue = "0") int pageNumber,
                                                 @RequestParam(required = false, defaultValue = "1") int pageSize){
        return accountService.findAllAccounts(pageNumber, pageSize);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{actNo}")
    public AccountResponse findAccountByActNo(@PathVariable String actNo){
        return accountService.findAccountByActNo(actNo);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{actNo}/rename")
    public AccountResponse renameAccountByActNo( @PathVariable String actNo,
                                        @Valid @RequestBody AccountRenameRequest accountRenameRequest){
        return accountService.renameAccountByActNo(actNo, accountRenameRequest);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{actNo}/hideAccount")
    public void hideAccountByActNo(@PathVariable String actNo){
        accountService.hideAccountByActNo(actNo);
    }
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{actNo}/transfer-limit")
    public void updateTransferLimitByActNo(@PathVariable String actNo,
                                           @Valid @RequestBody AccountTransferLimitRequest accountTransferLimitRequest){
        accountService.updateTransferLimitByActNo(actNo, accountTransferLimitRequest);
    }
}
