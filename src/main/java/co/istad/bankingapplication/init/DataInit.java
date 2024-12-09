package co.istad.bankingapplication.init;

import co.istad.bankingapplication.domain.AccountType;
import co.istad.bankingapplication.domain.User;
import co.istad.bankingapplication.feature.accountType.AccountTypeRepository;
import co.istad.bankingapplication.feature.user.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DataInit {

    private final UserRepository userRepository;
    private final AccountTypeRepository accountTypeRepository;


    @PostConstruct
    void init() {
        // User
        if (userRepository.count() == 0) {
            User user1 = new User();
            user1.setUuid(UUID.randomUUID().toString());
            user1.setName("Mouk Makara");
            user1.setPhoneNumber("0975495981");
            user1.setPassword("Qwer123@#$");
            user1.setPin("1234");
            user1.setGender("Male");
            user1.setNationalCardId("312312343");
            user1.setStudentCardId("000012345");
            user1.setIsBlocked(false);
            user1.setIsDeleted(false);

            User user2 = new User();
            user2.setUuid(UUID.randomUUID().toString());
            user2.setName("Chan Bopha");
            user2.setPhoneNumber("0975495983");
            user2.setPassword("Qwer123%#$");
            user2.setPin("1235");
            user2.setGender("Female");
            user2.setNationalCardId("312312344");
            user2.setStudentCardId("000012346");
            user2.setIsBlocked(false);
            user2.setIsDeleted(false);

            userRepository.saveAll(List.of(user1, user2));
        }
        // Account Type
        if (accountTypeRepository.count() == 0){
            AccountType accountType1 = new AccountType();
            // Error creating bean with name 'dataInit': Invocation of init method failed
            // -> because id is Conflict id auto generate

//            accountType1.setId(1);
            accountType1.setAlias("payroll");
            accountType1.setName("payroll");
            accountType1.setDescription("payroll from account type");
            accountType1.setIsDeleted(false);

            AccountType accountType2 = new AccountType();
            // Error creating bean with name 'dataInit': Invocation of init method failed
            // -> because id is Conflict id auto generate

//            accountType2.setId(2);
            accountType2.setAlias("saving");
            accountType2.setName("saving");
            accountType2.setDescription("saving from account type");
            accountType2.setIsDeleted(false);

            accountTypeRepository.saveAll(List.of(accountType1, accountType2));
        }
    }
}
