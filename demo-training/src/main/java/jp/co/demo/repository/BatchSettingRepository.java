package jp.co.demo.repository;

import jp.co.demo.entity.BatchSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * BatchSettingRepository
 */
@Repository
public interface BatchSettingRepository extends JpaRepository<BatchSetting, Long> {
}
