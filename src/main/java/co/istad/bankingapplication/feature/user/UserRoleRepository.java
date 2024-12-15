package co.istad.bankingapplication.feature.user;

import co.istad.bankingapplication.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRoleRepository extends JpaRepository<Role, Integer> {
    @Query("SELECT r FROM Role as r WHERE r.name = 'USER'")
    Role findRoleUser();

    @Query("SELECT r FROM Role as r WHERE r.name = 'CUSTOMER'")
    Role findRoleCustomer();
}
