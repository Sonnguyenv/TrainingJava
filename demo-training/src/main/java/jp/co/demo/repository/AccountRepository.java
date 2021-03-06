package jp.co.demo.repository;

import jp.co.demo.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

/**
 * AccountRepository
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    /**
     * Find account by loginID
     *
     * @param loginId loginID
     * @return {@link Account}
     */
    Account findByLoginId(String loginId);

    @Query(value = "select account from Account account where account.fullName like %?1% or account.mailAddress like %?1%")
    List<Account> findByFullName(String keyword);
}
