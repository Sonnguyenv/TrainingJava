package jp.co.demo.service.impl;

import jp.co.demo.common.BaseConst;
import jp.co.demo.entity.Account;
import jp.co.demo.entity.Hospital;
import jp.co.demo.enums.AccountStatus;
import jp.co.demo.repository.AccountRepository;
import jp.co.demo.security.AccountDetails;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * UserDetailsServiceImpl
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AccountRepository accountRepository;

    public UserDetailsServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        // Check loginId
        if (StringUtils.isAllBlank(loginId)) {
            throw new BadCredentialsException(BaseConst.ERROR);
        }

        // Search the account by loginId
        Account account = accountRepository.findByLoginId(loginId);

        if (account == null || account.isDeleted()) {
            throw new UsernameNotFoundException(BaseConst.NOT_FOUND);
        }

        if (AccountStatus.LOCKING == account.getAccountStatus()) {
            throw new LockedException(BaseConst.LOCKED);
        }

        List<Long> hospitalIds = new ArrayList<>();
        if (!CollectionUtils.isEmpty(account.getHospitals())) {
            hospitalIds = account.getHospitals().stream()
                    .map(Hospital::getId)
                    .collect(Collectors.toList());
        }

        return new AccountDetails(account.getId(), account.getLoginId(), account.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(account.getAuthorities())), hospitalIds);
    }
}
