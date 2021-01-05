package jp.co.demo.repository;

import jp.co.demo.entity.LocalServer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * LocalServerRepository
 */
@Repository
public interface LocalServerRepository extends JpaRepository<LocalServer, Long> {

    LocalServer findByListeningFolderId(Long listeningFolderId);
}
