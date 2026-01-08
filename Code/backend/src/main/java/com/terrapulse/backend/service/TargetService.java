package com.terrapulse.backend.service;

import com.terrapulse.backend.model.Company;
import com.terrapulse.backend.model.Target;
import com.terrapulse.backend.model.TargetStatus;
import com.terrapulse.backend.recommendation.Recommendation;
import com.terrapulse.backend.recommendation.RecommendationService;
import com.terrapulse.backend.repository.EnvironmentalMetricsRepository;
import com.terrapulse.backend.repository.GovernanceMetricRepository;
import com.terrapulse.backend.repository.SocialMetricsRepository;
import com.terrapulse.backend.repository.TargetRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class TargetService {

    private final EnvironmentalMetricsService environmentalMetricsService;
    private final SocialMetricService socialMetricService;
    private final GovernanceMetricsService governanceMetricsService;



    private final EnvironmentalMetricsRepository environmentalMetricsRepository;
    private final SocialMetricsRepository socialMetricsRepository;
    private final GovernanceMetricRepository governanceMetricRepository;
    private final RecommendationService recommendationService;

    public TargetRepository targetRepository;
    public TargetService(TargetRepository targetRepository,
                         EnvironmentalMetricsRepository environmentalMetricsRepository,
                         SocialMetricsRepository socialMetricsRepository,
                         GovernanceMetricRepository governanceMetricRepository,

                         EnvironmentalMetricsService environmentalMetricsService, SocialMetricService socialMetricService, GovernanceMetricsService governanceMetricsService, RecommendationService recommendationService){
        this.targetRepository =targetRepository;
        this.environmentalMetricsRepository = environmentalMetricsRepository;
        this.socialMetricsRepository = socialMetricsRepository;
        this.governanceMetricRepository = governanceMetricRepository;
        this.environmentalMetricsService = environmentalMetricsService;
        this.socialMetricService = socialMetricService;
        this.governanceMetricsService = governanceMetricsService;
        this.recommendationService = recommendationService;
    }

    public Target createTarget(Target target){
        return targetRepository.save(target);
    }

    public List<Target> getAllTarget(){
        return targetRepository.findAll();
    }

    public Target getTargetById(Long id){
        return targetRepository.findById(id).orElseThrow(()->new RuntimeException("Metric Not found"));
    }

    public Target updateTarget(Long id, Target updatedTarget){
        Target existing = getTargetById(id);
        existing.setTargetId(updatedTarget.getTargetId());
        existing.setCompanyId(updatedTarget.getCompanyId());
        existing.setMetric(updatedTarget.getMetric());
        existing.setTargetvalue(updatedTarget.getTargetvalue());
        existing.setsDate(updatedTarget.getsDate());
        existing.setdDate(updatedTarget.getdDate());
        existing.setStatus(updatedTarget.getStatus());

        return targetRepository.save(existing);
    }

    public void deleteTarget(Long id){
        targetRepository.deleteById(id);
    }

    public Target evaluateTarget(Target target){
        Double currentValue = resolveCurrentMetricValue(target);

        //NO DATA YET
        if (currentValue == null){
            target.setStatus(TargetStatus.NOT_STARTED);
            return target;
        }

        //ACHIEVED (for all esg metrics)
        if(currentValue <= target.getTargetvalue()){
            target.setStatus(TargetStatus.ACHIEVED);
            return target;
        }

        LocalDate today = LocalDate.now();

        if(today.isAfter(target.getdDate())){
            target.setStatus(TargetStatus.AT_RISK);
            return target;

        }

        long daysRemaining = ChronoUnit.DAYS.between(today, target.getdDate());

        if (daysRemaining<=90){
            target.setStatus(TargetStatus.AT_RISK);
        }else {
            target.setStatus(TargetStatus.IN_PROGRESS);
        }

        return target;

    }

    private Double resolveCurrentMetricValue(Target target) {

        String metric = target.getMetric().toLowerCase();

        // ENVIRONMENTAL
        if (isEnvironmentalMetric(metric)) {
            return environmentalMetricsService
                    .getLatestMetricValue(target.getCompanyId(), metric);
        }

        // SOCIAL
        if (isSocialMetric(metric)) {
            return socialMetricService
                    .getLatestMetricValue(target.getCompanyId(), metric);
        }

        // GOVERNANCE
        if (isGovernanceMetric(metric)) {
            return governanceMetricsService
                    .getLatestMetricValue(target.getCompanyId(), metric);
        }

        return null;
    }


    private boolean isEnvironmentalMetric(String metric) {
        return metric.equals("carbon_emissions")
                || metric.equals("energy_usage")
                || metric.equals("renewable_percent");
    }

    private boolean isSocialMetric(String metric) {
        return metric.equals("injury_rate")
                || metric.equals("turnover_rate")
                || metric.equals("gender_diversity_percent");
    }

    private boolean isGovernanceMetric(String metric) {
        return metric.equals("policy_violations")
                || metric.equals("board_independence_percent")
                || metric.equals("audit_findings");
    }

    public List<Recommendation> getRecommendationForCompany(Long companyId){
        List<Target> targets = targetRepository.findByCompanyId(companyId);
        List<Recommendation> recommendations = new ArrayList<>();

        for (Target target : targets){
            Target evaluatedTarget = evaluateTarget(target);

            Recommendation recommendation = recommendationService.generateRecommendation(evaluatedTarget);

            if (recommendation !=null){
                recommendations.add(recommendation);
            }

            System.out.println("Evaluating target: " + target.getMetric());
            System.out.println("Status after evaluation: " + evaluatedTarget.getStatus());
            if (recommendation != null) {
                System.out.println("Recommendation generated for: " + recommendation.getMetricName());
            }


        }
        return recommendations;


    }

    public List<Target> getAllTargetForCompany(Long companyId) {
        return targetRepository.findByCompanyId(companyId);
    }
}
