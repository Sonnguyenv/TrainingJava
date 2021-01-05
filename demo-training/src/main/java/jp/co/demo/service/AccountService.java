package jp.co.demo.service;

import jp.co.demo.entity.Account;

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
}
