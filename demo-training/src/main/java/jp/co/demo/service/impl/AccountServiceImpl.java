package jp.co.demo.service.impl;

import jp.co.demo.entity.Account;
import jp.co.demo.repository.AccountRepository;
import jp.co.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

    public void createUser(Account account) {
        accountRepository.save(account);
    }

    public void updateUser(Account account) {
        accountRepository.findById(account.getId());
        accountRepository.save(account);
    }

    public void deleteUser(Account account) {
        accountRepository.delete(account);
    }

    @Override
    public Page<Account> findPaginated(int pageNo, int pageSize, Account account) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        if (account.getFullName() != null && account.getFullName() != "") {
            return accountRepository.findAllByNameContaining(account.getFullName(), pageable);
        } else {
            Page<Account> pagedResult = accountRepository.findAll(pageable);
            return pagedResult;
        }
    }
}
