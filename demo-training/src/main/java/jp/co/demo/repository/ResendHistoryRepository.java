package jp.co.demo.repository;

import jp.co.demo.entity.ResendHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ResendHistoryRepository
 */
@Repository
public interface ResendHistoryRepository extends JpaRepository<ResendHistory, Long> {
}
