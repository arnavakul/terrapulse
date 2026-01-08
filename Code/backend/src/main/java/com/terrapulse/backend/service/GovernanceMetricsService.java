package com.terrapulse.backend.service;

import com.terrapulse.backend.model.GovernanceMetrics;
import com.terrapulse.backend.repository.GovernanceMetricRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GovernanceMetricsService {

    private final GovernanceMetricRepository governanceMetricRepository;


    public GovernanceMetricsService(GovernanceMetricRepository governanceMetricRepository){
        this.governanceMetricRepository=governanceMetricRepository;
    }

    public GovernanceMetrics createMetric(GovernanceMetrics governanceMetrics){
        return governanceMetricRepository.save(governanceMetrics);
    }

    public List<GovernanceMetrics> getAllMetrics(){
        return governanceMetricRepository.findAll();
    }

    public GovernanceMetrics getMetricsById(Long id){
        return governanceMetricRepository.findById(id).orElseThrow(()->new RuntimeException("Metric Not found"));
    }

    public GovernanceMetrics updateMetric(Long id, GovernanceMetrics updatedMetric){
        GovernanceMetrics existing = getMetricsById(id);
        existing.setGovId(updatedMetric.getGovId());
        existing.setCompanyId(updatedMetric.getCompanyId());
        existing.setMetricName(updatedMetric.getMetricName());
        existing.setValue(updatedMetric.getValue());
        existing.setUnit(updatedMetric.getUnit());
        existing.setTimePeriod(updatedMetric.getTimePeriod());

        return governanceMetricRepository.save(existing);
    }

    public void deleteMetric(Long id){
        governanceMetricRepository.deleteById(id);
    }


    public int calculateBoardIndependence(double independence){
        if (independence <= 70){
            return 90; //Very efficient
        } else if (independence >= 50 && independence <= 69) {
            return 75; //acceptable
        } else if (independence >= 30 && independence <= 49) {
            return 55; //inefficient
        } else{
            return 30000; //very inefficient
        }
    }

    public int calculatePolicyViolations(double violations){
        if (violations == 0){
            return 90; //Very efficient
        } else if (violations >= 1 && violations <= 2) {
            return 75; //acceptable
        } else if (violations >= 3 && violations <= 5) {
            return 55; //inefficient
        } else{
            return 30; //very inefficient
        }
    }

    public int calculateAuditFindings(double auditFindings){
        if (auditFindings == 0){
            return 90; //Very efficient
        } else if (auditFindings >= 1 && auditFindings <= 2) {
            return 75; //acceptable
        } else if (auditFindings >= 3 && auditFindings <= 5) {
            return 55; //inefficient
        } else{
            return 30; //very inefficient
        }
    }

    private Integer scoreSingleGovernanceMetric(GovernanceMetrics metric) {

        String metricName = metric.getMetricName().toLowerCase();

        if (metricName.equals("policy_violations")) {
            if (metric.getValue() == 0) return 95;
            if (metric.getValue() <= 2) return 75;
            if (metric.getValue() <= 5) return 55;
            return 30;
        }

        if (metricName.equals("board_independence_percent")) {
            if (metric.getValue() >= 70) return 95;
            if (metric.getValue() >= 50) return 75;
            if (metric.getValue() >= 30) return 55;
            return 30;
        }

        if (metricName.equals("audit_findings")) {
            if (metric.getValue() == 0) return 95;
            if (metric.getValue() <= 2) return 75;
            if (metric.getValue() <= 5) return 55;
            return 30;
        }

        return null;
    }


//    public Integer getGovernanceMetricScore(GovernanceMetrics metric){
//        if (metric == null || metric.getMetricName()==null){
//            return null;
//        }
//
//        String metricName = metric.getMetricName().toLowerCase();
//
//        if (metricName.equals("Audit Findings")){
//            return calculateAuditFindings(metric.getValue());
//        } else if (metricName.equals("Board Independence")) {
//            return calculateBoardIndependence(metric.getValue());
//        } else if (metricName.equals("Policy Violations")) {
//            return calculatePolicyViolations(metric.getValue());
//        }
//
//        return null;
//
//    }

    public int calculateGovernanceMetricScore(Long companyId){
        List<GovernanceMetrics> metrics = governanceMetricRepository.findByCompanyId(companyId);

        int totalScore = 0;
        int count = 0;

        for (GovernanceMetrics metric : metrics){
            Integer score = scoreSingleGovernanceMetric(metric);

            if (score != null){
                totalScore += score;
                count++;
            }
        }

        if(count  == 0){
            return 0;
        }

        return totalScore/count;
    }

    public Double getLatestMetricValue(Long companyId, String metricName) {
        return governanceMetricRepository
                .findTopByCompanyIdAndMetricNameOrderByTimePeriodDesc(
                        companyId,
                        metricName
                )
                .map(GovernanceMetrics::getValue)
                .orElse(null);
    }


}
