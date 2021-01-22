package jp.co.demo.service;

import jp.co.demo.entity.Account;
import org.springframework.data.domain.Page;

/**
 * AccountService
 */
public interface AccountService {

    /**
     * Find account by loginID
     *
     * @param loginId loginID
     * @return {@link Account}
     */
    Account findByLoginId(String loginId);
    Page<Account> findPaginated(int pageNo, int pageSize, Account account);
    boolean checkIfValidOldPassword(Account account, String password);
}
