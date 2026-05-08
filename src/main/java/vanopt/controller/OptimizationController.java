package vanopt.controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vanopt.dto.OptimizationRequest;
import vanopt.dto.OptimizationResponse;
import vanopt.service.OptimizationService;

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
}
