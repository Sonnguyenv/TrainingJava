package jp.co.demo.config;

import jp.co.demo.entity.Account;
import jp.co.demo.enums.AccountStatus;
import jp.co.demo.enums.Authorities;
import jp.co.demo.repository.AccountRepository;
import jp.co.demo.repository.ErrorCodeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * LoadDatabase
 */
@Configuration
public class LoadDatabase {

    private static final String USER_NAME = "admin";
    private static final String PASSWORD = "$2a$10$eFMTFaCvgs2GnIsFprfwKeOEY/v/6cPh.EwtBVKEQqtfNEJx8/4HK";
    private static final String FULL_NAME = "admin";

    private static final String MAIL_ADDRESS = "sonnv@ominext.com";
    @Bean
    public CommandLineRunner initDatabase(AccountRepository accountRepository,
                                          ErrorCodeRepository errorCodeRepository) {
        return (args) -> {
            // Add Account
            createAccount(accountRepository);
        };
    }

    private void createAccount(AccountRepository accountRepository) {
        Account account = accountRepository.findByLoginId(USER_NAME);
        if (account == null) {
            account = new Account();
            account.setAccountStatus(AccountStatus.VALID);
            account.setAuthorities(Authorities.ROLE_ADMIN.name());
            account.setLoginId(USER_NAME);
            account.setPassword(PASSWORD);
            account.setFullName(FULL_NAME);
            account.setMailAddress(MAIL_ADDRESS);
            accountRepository.save(account);
        }
    }
}
