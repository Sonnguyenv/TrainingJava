package jp.co.demo.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.UserDetailsAwareConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * AuthenticationConfigurer
 */
public class AuthenticationConfigurer extends UserDetailsAwareConfigurer<AuthenticationManagerBuilder, UserDetailsService> {

    private AuthenticationProvider provider = new AuthenticationProvider();

    private final UserDetailsService userDetailsService;

    public AuthenticationConfigurer(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
        this.provider.setUserDetailsService(userDetailsService);
    }

    public AuthenticationConfigurer passwordEncoder(PasswordEncoder passwordEncoder) {
        this.provider.setPasswordEncoder(passwordEncoder);
        return this;
    }

    @Override
    public void configure(AuthenticationManagerBuilder builder) {
        this.provider = postProcess(this.provider);
        builder.authenticationProvider(this.provider);
    }

    @Override
    public UserDetailsService getUserDetailsService() {
        return this.userDetailsService;
    }
}
