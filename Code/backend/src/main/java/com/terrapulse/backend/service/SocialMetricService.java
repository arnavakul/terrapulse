package com.terrapulse.backend.service;


import com.terrapulse.backend.model.SocialMetrics;
import com.terrapulse.backend.repository.SocialMetricsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocialMetricService {

    public SocialMetricsRepository socialMetricsRepository;
    public SocialMetricService(SocialMetricsRepository socialMetricsRepository){
        this.socialMetricsRepository = socialMetricsRepository;
    }

    public SocialMetrics createMetric(SocialMetrics socialMetrics){
        return socialMetricsRepository.save(socialMetrics);
    }

    public List<SocialMetrics> getAllMetrics(){
        return socialMetricsRepository.findAll();
    }

    public SocialMetrics getMetricsById(Long id){
        return socialMetricsRepository.findById(id).orElseThrow(()->new RuntimeException("Metric Not found"));
    }

    public SocialMetrics updateMetric(Long id, SocialMetrics updatedMetric){
        SocialMetrics existing = getMetricsById(id);
        existing.setCompanyId(updatedMetric.getCompanyId());
        existing.setsId(updatedMetric.getsId());
        existing.setEmployeeCount(updatedMetric.getEmployeeCount());
        existing.setMetricName(updatedMetric.getMetricName());
        existing.setUnit(updatedMetric.getUnit());
        existing.setValue(updatedMetric.getValue());
        existing.setTimePeriod(updatedMetric.getTimePeriod());
        return socialMetricsRepository.save(existing);
    }

    public void deleteMetric(Long id){
        socialMetricsRepository.deleteById(id);
    }

    public int calculateInjuryRate(double injuryRate){
        if(injuryRate <= 1 ) {
            return 95; //Excellent Safety
        } else if (injuryRate>= 2 && injuryRate <=3 ) {
            return 75; //Acceptable
        } else if (injuryRate >=4 && injuryRate <=6) {
            return 55; //Concerning
        }else {
            return 30; //Unsafe
        }
    } // Metric 1: Employee Injury Rate | Number of workplace injuries per year (or per 100 employees). Lower = better. |

    public int calculateEmployeeTurnoverRate(double turnoverRate){
        if(turnoverRate <= 10 ) {
            return 95; //Excellent Safety
        } else if (turnoverRate>= 11 && turnoverRate <=20 ) {
            return 75; //Acceptable
        } else if (turnoverRate >=21 && turnoverRate <=30) {
            return 55; //Concerning
        }else {
            return 30; //Unsafe
        }
    } //Metric 2: Injury Rate. In Percentage.

    public int calculateGenderDiversity(double diversityRate){
        if(diversityRate <= 40 ) {
            return 95; //Excellent Safety
        } else if (diversityRate>= 30 && diversityRate <=39 ) {
            return 75; //Acceptable
        } else if (diversityRate >=20 && diversityRate <=29) {
            return 55; //Concerning
        }else {
            return 30; //Unsafe
        }
    } //Metric 3: Gender Diversity. Percentage

    public Integer getSocialMetricScore(SocialMetrics metric){
        if (metric == null || metric.getMetricName()==null){
            return null;
        }

        String metricName = metric.getMetricName().toLowerCase();

        if (metricName.equals("Injury Rate")){
            return calculateInjuryRate(metric.getValue());
        } else if (metricName.equals("Employee_Turnover")) {
            return calculateEmployeeTurnoverRate(metric.getValue());
        } else if (metricName.equals("Gender_Diversity")) {
            return calculateGenderDiversity(metric.getValue());
        }

        return null;
    }

    public int calculateSocialMetricScoreForCompany(Long companyId){
        List<SocialMetrics> metrics = socialMetricsRepository.findByCompanyId(companyId);

        int totalScore = 0;
        int count = 0;


        for (SocialMetrics metric : metrics){
            Integer score = getSocialMetricScore(metric);

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
        return socialMetricsRepository.findTopByCompanyIdAndMetricNameOrderByTimePeriodDesc(companyId,metricName)
                .map(SocialMetrics::getValue)
                .orElse(null);

    }
}
