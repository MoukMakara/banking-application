package co.istad.bankingapplication.feature.auth;

import co.istad.bankingapplication.domain.Role;
import co.istad.bankingapplication.domain.User;
import co.istad.bankingapplication.domain.UserSendVerification;
import co.istad.bankingapplication.feature.auth.dto.RegisterRequest;
import co.istad.bankingapplication.feature.auth.dto.RegisterResponse;
import co.istad.bankingapplication.feature.user.UserRepository;
import co.istad.bankingapplication.feature.user.UserRoleRepository;
import co.istad.bankingapplication.feature.user.UserVerificationRepository;
import co.istad.bankingapplication.mapper.UserMapper;
import co.istad.bankingapplication.util.RandomUtil;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserVerificationRepository userVerificationRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleRepository userRoleRepository;
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String adminEmail;

    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {
        // validate user phoneNumber
        if(userRepository.existsByPhoneNumber(registerRequest.phoneNumber())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    String.format("Phone number %s already exists", registerRequest.phoneNumber()));
        }
        // validate password
        if (!registerRequest.password().equals(registerRequest.confirmPassword())){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    String.format("Password does not match confirm password")
            );
        }
        // validate nationalCardId
        if (userRepository.existsByNationalCardId(registerRequest.nationalCardId())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    String.format("National Card ID %s already exists", registerRequest.nationalCardId())
            );
        }
        // validate email
        if (userRepository.existsByEmail(registerRequest.email())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    String.format("Email %s already exists", registerRequest.email())
            );
        }
        // validate acceptTerm
        if (!registerRequest.acceptTerm()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    String.format("You must accept term")
            );
        }
        User user = userMapper.fromRegisterRequest(registerRequest);

        // set default value
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setUuid(UUID.randomUUID().toString());
        user.setIsDeleted(false);
        user.setIsBlocked(false);
        user.setIsVerified(false);
        user.setProfileImage("profile.jpg");

        Role roleUser = userRoleRepository.findRoleUser();
        Role roleCustomer = userRoleRepository.findRoleCustomer();
        List<Role> roles = List.of(roleUser, roleCustomer);
        user.setRoles(roles);


        userRepository.save(user);

        return RegisterResponse.builder()
                .message("you are registered successfully, please verify your email")
                .email(user.getEmail())
                .build();
    }

    @Override
    public void sendVerification(String email) throws MessagingException {
        // validate email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, String.format("User with email %s not found", email)
                ));

        UserSendVerification userSendVerification = new UserSendVerification();
        userSendVerification.setUser(user);
        userSendVerification.setExpirationTime(LocalTime.now().plusMinutes(1));
        userSendVerification.setVerificationCode(RandomUtil.random6Digits());

        userVerificationRepository.save(userSendVerification);

        // prepare email sending
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setTo(email);
        helper.setFrom(adminEmail);
        helper.setSubject("Verification email");
        helper.setText(userSendVerification.getVerificationCode());


        mailSender.send(message);
    }
}
