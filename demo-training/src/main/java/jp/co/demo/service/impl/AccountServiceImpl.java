package jp.co.demo.service.impl;

import jp.co.demo.entity.Account;
import jp.co.demo.repository.AccountRepository;
import jp.co.demo.service.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * AccountServiceImpl
 */
@Service
@Transactional(rollbackFor = Throwable.class, propagation = Propagation.REQUIRED)
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account findByLoginId(String loginId) {
        return accountRepository.findByLoginId(loginId);
    }

    public void putUser(Account account) {
        accountRepository.save(account);
    }

    public void postUser(Account account) {
        accountRepository.save(account);
    }

    public void deleteUser(Account account)  {
        accountRepository.delete(account);
    }
}
