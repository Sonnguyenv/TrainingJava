package jp.co.demo.repository;

import jp.co.demo.entity.ReceivingServer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ReceivingServerRepository
 */
@Repository
public interface ReceivingServerRepository extends JpaRepository<ReceivingServer, Long> {

    ReceivingServer findByHospitalId(Long hospitalId);
}
