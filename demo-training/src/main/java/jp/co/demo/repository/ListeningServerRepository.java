package jp.co.demo.repository;

import jp.co.demo.entity.ListeningServer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ListeningServerRepository
 */
@Repository
public interface ListeningServerRepository extends JpaRepository<ListeningServer, Long> {
}
