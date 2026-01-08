package com.terrapulse.backend.controller;

import com.terrapulse.backend.model.GovernanceMetrics;
import com.terrapulse.backend.service.GovernanceMetricsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Gmetric")
public class GovernanceMetricController {

    private final GovernanceMetricsService governanceMetricsService;

    public GovernanceMetricController(GovernanceMetricsService governanceMetricsService){
        this.governanceMetricsService = governanceMetricsService;
    }

    @PostMapping
    public GovernanceMetrics createMetric(@RequestBody GovernanceMetrics governanceMetrics){
        return governanceMetricsService.createMetric(governanceMetrics);
    }

    @GetMapping
    public List<GovernanceMetrics> getAllMetrics(){
        return governanceMetricsService.getAllMetrics();
    }

    @GetMapping("/{id}")
    public GovernanceMetrics getMetrics(@PathVariable Long id){
        return governanceMetricsService.getMetricsById(id);
    }


    @PutMapping("/{id}")
    public GovernanceMetrics updateMetric(
            @PathVariable Long id,
            @RequestBody GovernanceMetrics governanceMetrics
    ){
        return governanceMetricsService.updateMetric(id, governanceMetrics);
    }

    @DeleteMapping("/{id}")
    public void deleteMetric(@PathVariable Long id){
        governanceMetricsService.deleteMetric(id);
    }

}
