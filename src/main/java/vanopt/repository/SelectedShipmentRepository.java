package vanopt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vanopt.entity.SelectedShipmentEntity;

import java.util.List;
import java.util.UUID;

public interface SelectedShipmentRepository
        extends JpaRepository<SelectedShipmentEntity, Long> {


    List<SelectedShipmentEntity> findByRequestId(UUID requestId);
}