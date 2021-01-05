package jp.co.demo.repository;

import jp.co.demo.entity.FileFilter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * FileFilterRepository
 */
@Repository
public interface FileFilterRepository extends JpaRepository<FileFilter, Long> {

    List<FileFilter> findAllByListeningFolderId(Long listeningFolderId);
}
