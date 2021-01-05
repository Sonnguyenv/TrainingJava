package jp.co.demo.repository;

import jp.co.demo.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
}
