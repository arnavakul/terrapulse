package com.terrapulse.backend.controller;

import com.terrapulse.backend.model.SocialMetrics;
import com.terrapulse.backend.service.SocialMetricService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Smetric")
public class SocialMetricController {

    private final SocialMetricService socialMetricService;

    public SocialMetricController(SocialMetricService socialMetricService){
        this.socialMetricService = socialMetricService;
    }

    @PostMapping
    public SocialMetrics createMetric(@RequestBody SocialMetrics socialMetrics){
        return socialMetricService.createMetric(socialMetrics);
    }

    @GetMapping
    public List<SocialMetrics> getAllMetrics(){
        return socialMetricService.getAllMetrics();
    }

    @GetMapping("/{id}")
    public SocialMetrics getMetrics(@PathVariable Long id){
        return socialMetricService.getMetricsById(id);
    }


    @PutMapping("/{id}")
    public SocialMetrics updateMetric(
            @PathVariable Long id,
            @RequestBody SocialMetrics socialMetrics
    ){
        return socialMetricService.updateMetric(id, socialMetrics);
    }

    @DeleteMapping("/{id}")
    public void deleteMetric(@PathVariable Long id){
        socialMetricService.deleteMetric(id);
    }

}
