package jp.co.demo.repository;

import jp.co.demo.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * HospitalRepository
 */
@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {
}
