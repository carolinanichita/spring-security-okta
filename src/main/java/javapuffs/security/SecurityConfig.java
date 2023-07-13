package javapuffs.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;


@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Bean
    DefaultSecurityFilterChain defaultChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(auth ->
                auth.requestMatchers("/api/courses").permitAll()
                        .requestMatchers("/api/developers/labs").authenticated()
                        .requestMatchers("/api/developers/grades").hasAnyAuthority("instructors")).oauth2Login(withDefaults()).cors(withDefaults()).oauth2ResourceServer(it -> it.jwt(withDefaults())).build();
    }
}
