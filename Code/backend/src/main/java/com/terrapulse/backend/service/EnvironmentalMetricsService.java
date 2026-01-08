package com.terrapulse.backend.service;

import com.terrapulse.backend.model.EnvironmentalMetrics;
import com.terrapulse.backend.repository.EnvironmentalMetricsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnvironmentalMetricsService {

    public EnvironmentalMetricsRepository environmentalMetricsRepository;
    public EnvironmentalMetricsService(EnvironmentalMetricsRepository environmentalMetricsRepository){
        this.environmentalMetricsRepository =environmentalMetricsRepository;
    }

    //CRUD Operations

    public EnvironmentalMetrics createMetric(EnvironmentalMetrics environmentalMetrics){
        return environmentalMetricsRepository.save(environmentalMetrics);
    }

//    public List<EnvironmentalMetrics> getValue(){
//        return environmentalMetricsRepository.findAll();
//    }

    public List<EnvironmentalMetrics> getAllMetrics(){
        return environmentalMetricsRepository.findAll();
    }

    public EnvironmentalMetrics getMetricsById(Long id){
        return environmentalMetricsRepository.findById(id).orElseThrow(()->new RuntimeException("Metric Not found"));
    }

    public EnvironmentalMetrics updateMetric(Long id, EnvironmentalMetrics updatedMetric){
        EnvironmentalMetrics existing = getMetricsById(id);
        existing.setEnvId(updatedMetric.getEnvId());
        existing.setCompanyId(updatedMetric.getCompanyId());
        existing.setMetricName(updatedMetric.getMetricName());
        existing.setTimePeriod(updatedMetric.getTimePeriod());
        existing.setUnit(updatedMetric.getUnit());
        existing.setValue(updatedMetric.getValue());

        return environmentalMetricsRepository.save(existing);
    }

    public void deleteMetric(Long id){
        environmentalMetricsRepository.deleteById(id);
    }

    //Logic Layer

    public int calculateCarbonEmissionScore(double emissionValue){
        if (emissionValue <= 80){
            return 95; //excellent
        } else if (emissionValue <=120) {
            return 75; //acceptable
        } else if (emissionValue <= 160){
            return 55; //poor
        }
        else{
            return 30;
        }
    } //metric one //unit is tons //Carbon Emission Score

    public int calculateEnergyUsageScore(double energyValue){
        if (energyValue <= 10000){
            return 90; //Very efficient
        } else if (energyValue >= 10001 && energyValue <= 20000) {
            return 75; //acceptable
        } else if (energyValue >= 20001 && energyValue <= 30000) {
            return 55; //inefficient
        } else{
            return 30; //very inefficient
        }
    } //metric two: Energy Usage. Unit is KWh yearly preferrable

    public int calculateRenewablePercentage(double percent){

        if (percent < 70){
            return 95; //Excellent Transition
        } else if (percent >= 40 && percent <= 69) {
            return 75; //Good Progress
        } else if (percent >= 20 && percent <39) {
            return 55; //weak transition
        } else {
            return 20; //poor
        }

    } //metric three; Renewable Percentage. Unit is % lies between 0-100.

    public Integer getEnvironmentMetricScore(EnvironmentalMetrics metric){
        if (metric == null || metric.getMetricName()==null){
            return null;
        }

        String metricName = metric.getMetricName().toLowerCase();

        if (metricName.equals("Carbon_Emissions")){
            return calculateCarbonEmissionScore(metric.getValue());
        } else if (metricName.equals("Energy_Usage")) {
            return calculateEnergyUsageScore(metric.getValue());
        } else if (metricName.equals("Renewable_Percent")) {
            return calculateRenewablePercentage(metric.getValue());
        }

        return null;
    }

    public int calculateEnvironmentalScoreForCompany(Long companyId){
        List<EnvironmentalMetrics> metrics = environmentalMetricsRepository.findByCompanyId(companyId);

        int totalScore = 0;
        int count = 0;

        for (EnvironmentalMetrics metric : metrics){
            Integer score = getEnvironmentMetricScore(metric);

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
        return environmentalMetricsRepository.findTopByCompanyIdAndMetricNameOrderByTimePeriodDesc(companyId,metricName)
                .map(EnvironmentalMetrics::getValue)
                .orElse(null);
    }
}
