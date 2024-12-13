package co.istad.bankingapplication.security;

//import co.istad.bankingapplication.password.PasswordEncoderConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // way 2 to do BCryptPasswordEncoder
//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Make stateless session
        http.sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // Endpoint Security config
        http.authorizeHttpRequests(endpoint -> endpoint
                        // Centralized Authorization
                .requestMatchers(HttpMethod.GET,"/api/v1/accounts/**").hasAnyRole("USER")
                .requestMatchers(HttpMethod.POST, "api/v1/accounts/**").hasAnyRole("USER")
                .requestMatchers(HttpMethod.PUT,"api/v1/accounts/**").hasAnyRole("USER")
                .requestMatchers(HttpMethod.PATCH,"api/v1/accounts/**").hasAnyRole("USER")
                .requestMatchers(HttpMethod.DELETE,"api/v1/accounts/**").hasRole("ADMIN")

                .requestMatchers(HttpMethod.GET, "api/v1/account-types/**").hasAnyRole("USER")
                .requestMatchers(HttpMethod.POST, "api/v1/account-types/**").hasAnyRole("CUSTOMER")
                .requestMatchers(HttpMethod.PUT, "api/v1/account-types/**").hasAnyRole("CUSTOMER")
                .requestMatchers(HttpMethod.PATCH, "api/v1/account-types/**").hasAnyRole("CUSTOMER")
                .requestMatchers(HttpMethod.DELETE, "api/v1/account-types/**").hasRole("ADMIN")
                .anyRequest().authenticated());

        // Security Mechanism (HTTP Basic Auth)
        // HTTP Basic Auth (username & password)
        http.httpBasic(Customizer.withDefaults());

        // Disable CSRF(Cross-Site Request Forgery) token
        http.csrf(token -> token.disable());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails user = User
                .withUsername("user")
                .password(bCryptPasswordEncoder.encode("user123"))
//                .password(bCryptPasswordEncoder().encode("user123"))
                .roles("USER")
                .build();
        UserDetails customer = User
                .withUsername("customer")
                .password(bCryptPasswordEncoder.encode("customer123"))
//                .password(bCryptPasswordEncoder().encode("editor123"))
                .roles("USER", "CUSTOMER")
                .build();
        UserDetails admin = User
                .withUsername("admin")
                .password(bCryptPasswordEncoder.encode("admin123"))
//                .password(bCryptPasswordEncoder().encode("admin123"))
                .roles("USER","CUSTOMER", "ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, customer, admin);
    }
}
