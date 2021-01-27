package jp.co.demo.service.impl;

import jp.co.demo.entity.Account;
import jp.co.demo.model.UserModel;
import jp.co.demo.repository.AccountRepository;
import jp.co.demo.service.AccountService;
import org.apache.tomcat.util.buf.UDecoder;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * AccountServiceImpl
 */
@Service
@Transactional(rollbackFor = Throwable.class, propagation = Propagation.REQUIRED)
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final PasswordEncoder passwordEncoder;

    public AccountServiceImpl(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Account findByLoginId(String loginId) {
        Account account = accountRepository.findByLoginId(loginId);
        return account;
    }

    public void createUser(Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountRepository.save(account);
    }

    public void updateUser(Account account) {
        accountRepository.findById(account.getId());
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountRepository.save(account);
    }

    public void deleteUser(Account account) {
        accountRepository.delete(account);
    }

    @Override
    public Page<Account> findPaginated(int pageNo, int pageSize, Account account) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        if (account.getFullName() != null && !account.getFullName().equals("")) {
            return accountRepository.findAllByNameContaining(account.getFullName(), pageable);
        } else {
            return accountRepository.findAll(pageable);
        }
    }

    @Override
    public boolean checkIfValidOldPassword(final UserModel userModel, final String oldPassword) {
        return passwordEncoder.matches(userModel.getPassword(), oldPassword);
    }
}
