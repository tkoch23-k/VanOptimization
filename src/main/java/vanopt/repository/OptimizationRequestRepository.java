package vanopt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vanopt.entity.OptimizationRequestEntity;

import java.util.UUID;

public interface OptimizationRequestRepository
        extends JpaRepository<OptimizationRequestEntity, UUID> {
}
