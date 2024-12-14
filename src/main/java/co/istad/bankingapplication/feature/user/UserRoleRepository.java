package co.istad.bankingapplication.feature.user;

import co.istad.bankingapplication.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<Role, Integer> {
}
