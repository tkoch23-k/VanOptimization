package vanopt.service;
import org.springframework.stereotype.Service;
import vanopt.dto.OptimizationRequest;
import vanopt.dto.OptimizationResponse;
import vanopt.algorithm.VanLoadingOptimizer;
import java.util.List;
import vanopt.dto.Shipment;
import vanopt.entity.OptimizationRequestEntity;
import vanopt.repository.OptimizationRequestRepository;
import vanopt.repository.SelectedShipmentRepository;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.Collectors;

import vanopt.entity.SelectedShipmentEntity;
@Service
public class OptimizationService {
    private final VanLoadingOptimizer vanLoadingOptimizer;
    private final OptimizationRequestRepository optimizationRequestRepository;

    private final SelectedShipmentRepository selectedShipmentRepository;
    public OptimizationService(VanLoadingOptimizer vanLoadingOptimizer,
                               OptimizationRequestRepository optimizationRequestRepository,
                               SelectedShipmentRepository selectedShipmentRepository) {
        this.vanLoadingOptimizer = vanLoadingOptimizer;
        this.optimizationRequestRepository = optimizationRequestRepository;
        this.selectedShipmentRepository = selectedShipmentRepository;
    }

    public OptimizationResponse optimize(OptimizationRequest request) {
        int totalVolume = 0;
        int totalRevenue = 0;
        UUID requestId = UUID.randomUUID();
        LocalDateTime startTime = LocalDateTime.now();
        List<Shipment> selectedShipments =
                vanLoadingOptimizer.optimize(
                        request.getMaxVolume(),
                        request.getShipments()
                );
        for(Shipment selectedShipment : selectedShipments) {
            totalVolume += selectedShipment.getVolume();
            totalRevenue += selectedShipment.getRevenue();
        }
        OptimizationRequestEntity optimizationRequestEntity =
                new OptimizationRequestEntity();

        optimizationRequestEntity.setId(requestId);

        optimizationRequestEntity.setMaxVolume(
                request.getMaxVolume()
        );

        optimizationRequestEntity.setTotalVolume(totalVolume);

        optimizationRequestEntity.setTotalRevenue(totalRevenue);

        optimizationRequestEntity.setCreatedAt(startTime);
        optimizationRequestRepository.save(
                optimizationRequestEntity
        );
        for(Shipment selectedShipment : selectedShipments) {
            SelectedShipmentEntity selectedShipmentEntity = new SelectedShipmentEntity();
            selectedShipmentEntity.setRequestId(requestId);

            selectedShipmentEntity.setName(
                    selectedShipment.getName()
            );

            selectedShipmentEntity.setVolume(
                    selectedShipment.getVolume()
            );

            selectedShipmentEntity.setRevenue(
                    selectedShipment.getRevenue()
            );

            selectedShipmentRepository.save(
                    selectedShipmentEntity
            );

        }
        OptimizationResponse optimizationResponse =
                new OptimizationResponse();

        optimizationResponse.setRequestId(requestId);

        optimizationResponse.setSelectedShipments(
                selectedShipments
        );

        optimizationResponse.setTotalVolume(totalVolume);

        optimizationResponse.setTotalRevenue(totalRevenue);

        optimizationResponse.setCreatedAt(startTime);

        return optimizationResponse;
    }
    public OptimizationResponse getById(UUID requestId) {
        OptimizationRequestEntity entity = optimizationRequestRepository
                .findById(requestId)
                .orElseThrow(() -> new RuntimeException(
                        "Optimization request not found with id: " + requestId
                ));
        List<SelectedShipmentEntity> shipmentEntities =
                selectedShipmentRepository.findByRequestId(requestId);

        List<Shipment> selectedShipments = shipmentEntities.stream()
                .map(s -> {
                    Shipment shipment = new Shipment();
                    shipment.setName(s.getName());
                    shipment.setVolume(s.getVolume());
                    shipment.setRevenue(s.getRevenue());
                    return shipment;
                })
                .collect(Collectors.toList());


        OptimizationResponse response = new OptimizationResponse();
        response.setRequestId(entity.getId());
        response.setSelectedShipments(selectedShipments);
        response.setTotalVolume(entity.getTotalVolume());
        response.setTotalRevenue(entity.getTotalRevenue());
        response.setCreatedAt(entity.getCreatedAt());

        return response;
    }


    public List<OptimizationResponse> getAll() {

        List<OptimizationRequestEntity> allEntities =
                optimizationRequestRepository.findAll();

        return allEntities.stream()
                .map(entity -> {
                    List<SelectedShipmentEntity> shipmentEntities =
                            selectedShipmentRepository.findByRequestId(entity.getId());

                    List<Shipment> selectedShipments = shipmentEntities.stream()
                            .map(s -> {
                                Shipment shipment = new Shipment();
                                shipment.setName(s.getName());
                                shipment.setVolume(s.getVolume());
                                shipment.setRevenue(s.getRevenue());
                                return shipment;
                            })
                            .collect(Collectors.toList());

                    OptimizationResponse response = new OptimizationResponse();
                    response.setRequestId(entity.getId());
                    response.setSelectedShipments(selectedShipments);
                    response.setTotalVolume(entity.getTotalVolume());
                    response.setTotalRevenue(entity.getTotalRevenue());
                    response.setCreatedAt(entity.getCreatedAt());

                    return response;
                })
                .collect(Collectors.toList());
    }
}
