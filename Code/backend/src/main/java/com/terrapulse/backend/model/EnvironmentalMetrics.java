package com.terrapulse.backend.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "environmental_metrics", schema = "terrapulse")
public class EnvironmentalMetrics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "env_id")
    private Long envId;

    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "metric_name")
    private String metricName;

    @Column(name = "value")
    private Double value;

    @Column(name = "unit")
    private String unit;

    @Column(name = "time_period")
    private LocalDate timePeriod;


    public EnvironmentalMetrics(Long envId, Long companyId, String metricName, Double value, String unit, LocalDate timePeriod) {
        this.envId = envId;
        this.companyId = companyId;
        this.metricName = metricName;
        this.value = value;
        this.unit = unit;
        this.timePeriod = timePeriod;
    }


    public EnvironmentalMetrics() {

    }


    public Long getEnvId() {
        return envId;
    }

    public void setEnvId(Long envId) {
        this.envId = envId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getMetricName() {
        return metricName;
    }

    public void setMetricName(String metricName) {
        this.metricName = metricName;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public LocalDate getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(LocalDate timePeriod) {
        this.timePeriod = timePeriod;
    }


}
