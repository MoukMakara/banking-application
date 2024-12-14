package co.istad.bankingapplication.init;

import co.istad.bankingapplication.domain.AccountType;
import co.istad.bankingapplication.domain.Role;
import co.istad.bankingapplication.domain.User;
import co.istad.bankingapplication.feature.accountType.AccountTypeRepository;
import co.istad.bankingapplication.feature.user.UserRepository;
import co.istad.bankingapplication.feature.user.UserRoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DataInit {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final AccountTypeRepository accountTypeRepository;


    @PostConstruct
    void init() {
        // User
        if (userRepository.count() == 0) {
            Role user = new Role();
            user.setName("USER");

            Role customer = new Role();
            customer.setName("CUSTOMER");

            Role admin = new Role();
            admin.setName("ADMIN");

            userRoleRepository.saveAll(List.of(user, customer, admin));

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
            user1.setRoles(List.of(user));

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
            user2.setRoles(List.of(user, customer));

            User user3 = new User();
            user3.setUuid(UUID.randomUUID().toString());
            user3.setName("Pich Nora");
            user3.setPhoneNumber("0975495989");
            user3.setPassword("Qwer123%#$");
            user3.setPin("1239");
            user3.setGender("Male");
            user3.setNationalCardId("316312344");
            user3.setStudentCardId("000012946");
            user3.setIsBlocked(false);
            user3.setIsDeleted(false);
            user3.setRoles(List.of(user, customer, admin));

            userRepository.saveAll(List.of(user1, user2, user3));
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
