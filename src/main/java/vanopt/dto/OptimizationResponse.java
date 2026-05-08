package vanopt.dto;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class OptimizationResponse {
    private UUID requestId;
    private List<Shipment> selectedShipments;
    private int totalV;
    private int totalR;
    private LocalDateTime createdAt;

    public UUID getRequestId() {
        return requestId;
    }

    public void setRequestId(UUID requestId) {
        this.requestId = requestId;
    }

    public List<Shipment> getSelectedShipments() {
        return selectedShipments;
    }

    public void setSelectedShipments(List<Shipment> selectedShipments) {
        this.selectedShipments = selectedShipments;
    }

    public int getTotalVolume() {
        return totalV;
    }

    public void setTotalVolume(int totalVolume) {
        this.totalV = totalVolume;
    }

    public int getTotalRevenue() {
        return totalR;
    }

    public void setTotalRevenue(int totalRevenue) {
        this.totalR = totalRevenue;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
