package jp.co.demo.repository;

import jp.co.demo.entity.TransferHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * TransferHistoryRepository
 */
@Repository
public interface TransferHistoryRepository extends JpaRepository<TransferHistory, Long> {
}
