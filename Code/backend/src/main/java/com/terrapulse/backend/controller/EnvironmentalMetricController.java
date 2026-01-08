package com.terrapulse.backend.controller;

import com.terrapulse.backend.model.EnvironmentalMetrics;
import com.terrapulse.backend.service.EnvironmentalMetricsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Emetric")
public class EnvironmentalMetricController {

    private final EnvironmentalMetricsService environmentalMetricsService;

    public EnvironmentalMetricController(EnvironmentalMetricsService environmentalMetricsService){
        this.environmentalMetricsService=environmentalMetricsService;
    }

    @PostMapping
    public EnvironmentalMetrics createMetric(@RequestBody EnvironmentalMetrics environmentalMetrics){
        return environmentalMetricsService.createMetric(environmentalMetrics);
    }

    @GetMapping
    public List<EnvironmentalMetrics> getAllMetrics(){
        return environmentalMetricsService.getAllMetrics();
    }

    @GetMapping("/{id}")
    public EnvironmentalMetrics getMetrics(@PathVariable Long id){
        return environmentalMetricsService.getMetricsById(id);
    }


    @PutMapping("/{id}")
    public EnvironmentalMetrics updateMetric(
            @PathVariable Long id,
            @RequestBody EnvironmentalMetrics environmentalMetrics
    ){
        return environmentalMetricsService.updateMetric(id, environmentalMetrics);
    }

    @DeleteMapping("/{id}")
    public void deleteMetric(@PathVariable Long id){
        environmentalMetricsService.deleteMetric(id);
    }
}
