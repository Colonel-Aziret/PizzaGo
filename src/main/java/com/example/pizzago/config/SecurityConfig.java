package com.example.pizzago.config;

import com.example.pizzago.enums.Role;
import com.example.pizzago.model.User;
import com.example.pizzago.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableOAuth2Sso
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    CustomizeAuthenticationSuccessHandler customizeAuthenticationSuccessHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/login", "/register", "/registration", "/change_password", "/forgotPassword", "/passwordRecoveryEmail", "/newPasswordUser").permitAll()
                .antMatchers("/add_pizza", "save_pizza").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and().csrf().disable()
                .formLogin().successHandler(customizeAuthenticationSuccessHandler)
                .loginPage("/login").failureUrl("/login?error=true")
                .defaultSuccessUrl("/")
                .permitAll()
                .usernameParameter("login")
                .passwordParameter("password")
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login").and()
                .exceptionHandling()
                .accessDeniedPage("/access-denied");
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/templates/**", "/static/**", "registration");
    }

    @Bean
    public PrincipalExtractor principalExtractor(UserRepository userRepository) {
            return map -> {
                Long id = (Long) map.get("sub");

            User user = userRepository.findById(id).orElseGet(() -> {
                User newUser = new User();

                newUser.setId((id));
                newUser.setLogin((String) map.get("login"));
                newUser.setEmail((String) map.get("email"));
                newUser.setPassword("12345");
                newUser.setOrders(null);
                newUser.setRole(Role.USER);

                return newUser;
            });

            return userRepository.save(user);
        };
    }

}