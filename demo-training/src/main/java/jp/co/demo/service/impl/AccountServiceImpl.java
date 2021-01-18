package jp.co.demo.service.impl;

import jp.co.demo.entity.Account;
import jp.co.demo.repository.AccountRepository;
import jp.co.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<Account> getUser(Account account, int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        if (account.getFullName() != null && account.getFullName() != "") {
            return accountRepository.findByFullName(account.getFullName());
        } else {
            Page<Account> pagedResult = accountRepository.findAll(paging);
            return pagedResult.getContent();
        }
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

    public Page<Account> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.accountRepository.findAll(pageable);
    }
}
