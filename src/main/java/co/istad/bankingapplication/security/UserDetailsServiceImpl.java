package co.istad.bankingapplication.security;

import co.istad.bankingapplication.domain.User;
import co.istad.bankingapplication.feature.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    // loadUserByUsername(String username)
    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        User user = userRepository.findByPhoneNumberAndIsDeletedFalse(phoneNumber)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with phone number " + phoneNumber));

        log.info("User: {}" + user.getPhoneNumber());
        CustomUserDetails customUserDetails = new CustomUserDetails();
        customUserDetails.setUser(user);

        return customUserDetails;
    }
}
