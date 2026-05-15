package vanopt.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vanopt.dto.OptimizationRequest;
import vanopt.dto.OptimizationResponse;
import vanopt.service.OptimizationService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/optimizations")

public class OptimizationController {
    private final OptimizationService optimizationService;

    public OptimizationController(OptimizationService optimizationService) {
        this.optimizationService = optimizationService;
    }

    @PostMapping
    public OptimizationResponse optimize(@RequestBody OptimizationRequest request) {
        return optimizationService.optimize(request);
    }

    @GetMapping("/{requestId}")
    public ResponseEntity<OptimizationResponse> getById(
            @PathVariable UUID requestId) {
        OptimizationResponse response = optimizationService.getById(requestId);
        return ResponseEntity.ok(response);
    }


    @GetMapping
    public ResponseEntity<List<OptimizationResponse>> getAll() {
        List<OptimizationResponse> responses = optimizationService.getAll();
        return ResponseEntity.ok(responses);
    }
}
