package jp.co.demo.service.impl;

import jp.co.demo.entity.Account;
import jp.co.demo.repository.AccountRepository;
import jp.co.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * AccountServiceImpl
 */
@Service
@Transactional(rollbackFor = Throwable.class, propagation = Propagation.REQUIRED)
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account findByLoginId(String loginId) {
        return accountRepository.findByLoginId(loginId);
    }

    public List<Account> getUser(Account account) {
        if (account.getLoginId() != "" || account.getLoginId() != null) {
            List<Account> accounts = new ArrayList<>();
            accounts.add(accountRepository.findByLoginId(account.getLoginId()));
            return accounts;
        }
        return accountRepository.findAll();
    }

    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    public void updateUser(Account account) {
        accountRepository.findByLoginId(account.getLoginId());
        accountRepository.save(account);
    }

    public void createUser(Account account) {
        accountRepository.save(account);
    }

    public void deleteUser(Account account)  {
        accountRepository.delete(account);
    }
}
