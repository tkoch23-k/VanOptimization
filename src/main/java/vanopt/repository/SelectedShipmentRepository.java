package vanopt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vanopt.entity.SelectedShipmentEntity;

public interface SelectedShipmentRepository
        extends JpaRepository<SelectedShipmentEntity, Long> {
}