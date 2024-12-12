package co.istad.bankingapplication.feature.accountType;


import co.istad.bankingapplication.feature.accountType.dto.AccountTypeCreateRequest;
import co.istad.bankingapplication.feature.accountType.dto.AccountTypeResponse;
import co.istad.bankingapplication.feature.accountType.dto.AccountTypeUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/account-types")
@RequiredArgsConstructor
public class AccountTypeController {
    private final AccountTypeService accountTypeService;

    @GetMapping
    public List<AccountTypeResponse> getAllAccountTypes() {
        return accountTypeService.findAllAccountTypes();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public AccountTypeResponse createAccountType(@Valid @RequestBody AccountTypeCreateRequest accountTypeCreateRequest){
        return accountTypeService.createAccountType(accountTypeCreateRequest);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{alias}")
    public AccountTypeResponse updateAccountTypeByAlias(@PathVariable String alias, @RequestBody AccountTypeUpdateRequest accountTypeUpdateRequest){
        return accountTypeService.updateAccountTypeByAlias(alias, accountTypeUpdateRequest);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{alias}")
    public void deleteAccountTypeByAlias(@PathVariable String alias){
        accountTypeService.deleteAccountTypeByAlias(alias);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{alias}")
    public AccountTypeResponse getAccountTypeByAlias(@PathVariable String alias){
        return accountTypeService.getAccountTypeByAlias(alias);
    }
}
