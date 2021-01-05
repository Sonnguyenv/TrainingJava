package jp.co.demo.repository;

import jp.co.demo.entity.ErrorCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ErrorCodeRepository
 */
@Repository
public interface ErrorCodeRepository extends JpaRepository<ErrorCode, Long> {

    ErrorCode findByCode(String errorCode);
}
