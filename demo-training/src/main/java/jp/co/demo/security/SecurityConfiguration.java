package jp.co.demo.security;

import jp.co.demo.common.RequestPathConst;
import jp.co.demo.security.repository.UserContextRepository;
import jp.co.demo.service.impl.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * SecurityConfiguration
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsService;
    private final SuccessHandler successHandler;
    private final FailureHandler failureHandler;
    private final LogoutSuccessHandler logoutSuccessHandler;
    private final UserContextRepository userContextRepository;

    public SecurityConfiguration(UserDetailsServiceImpl userDetailsService,
                                 SuccessHandler successHandler,
                                 FailureHandler failureHandler,
                                 LogoutSuccessHandler logoutSuccessHandler,
                                 UserContextRepository userContextRepository) {
        this.userDetailsService = userDetailsService;
        this.successHandler = successHandler;
        this.failureHandler = failureHandler;
        this.logoutSuccessHandler = logoutSuccessHandler;
        this.userContextRepository = userContextRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.securityContext().securityContextRepository(userContextRepository);
        http.sessionManagement().enableSessionUrlRewriting(true);

        http.antMatcher("/M/**")
                .authorizeRequests()
                .antMatchers("/favicon-16x16.png", RequestPathConst.M, RequestPathConst.M01, RequestPathConst.M01_LOGIN, RequestPathConst.PAGE_ERROR).permitAll()
                .antMatchers("/M/**").hasAnyRole("ADMIN", "MANAGE")
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(adminAccessDeniedHandler())
        ;

        http.formLogin()
                .loginPage(RequestPathConst.M01)
                .loginProcessingUrl(RequestPathConst.M01_LOGIN)
                .successHandler(successHandler)
                .failureHandler(failureHandler)
                .usernameParameter("username").passwordParameter("password")
        ;

        http.logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID", "remember-me")
                .logoutRequestMatcher(new AntPathRequestMatcher(RequestPathConst.M01_LOGOUT))
                .logoutSuccessHandler(logoutSuccessHandler)
                .permitAll()
        ;

        http.sessionManagement()
                .enableSessionUrlRewriting(true)
        ;

        http.csrf()
                .ignoringAntMatchers(RequestPathConst.M, RequestPathConst.M01, RequestPathConst.M01_LOGIN)
        ;

        http.headers()
                .contentTypeOptions()
        ;

        http.headers()
                .frameOptions()
                .sameOrigin()
        ;

        http.headers()
                .xssProtection()
                .xssProtectionEnabled(true)
        ;

        http.headers()
                .httpStrictTransportSecurity()
                .maxAgeInSeconds(31536000)
                .includeSubDomains(true)
        ;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/images/**", "/js/**", "/css/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        AuthenticationConfigurer configurer = new AuthenticationConfigurer(userDetailsService).passwordEncoder(adminPasswordEncoder());
        auth.apply(configurer);
    }

    @Bean
    public PasswordEncoder adminPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AccessDeniedHandler adminAccessDeniedHandler() {
        return new UserAccessDeniedHandler();
    }
}
