package jp.co.demo.repository;

import jp.co.demo.entity.ListeningFolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ListeningFolderRepository
 */
@Repository
public interface ListeningFolderRepository extends JpaRepository<ListeningFolder, Long> {
}
