package com.terrapulse.backend.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "governance_metric", schema = "terrapulse")
public class GovernanceMetrics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gov_id")
    private Long govId;
    @Column(name = "company_id")
    private Long companyId;
    @Column(name = "metric_name")
    private String metricName;

    @Column(name = "value")
    private Double value;

    @Column(name = "unit")
    private String unit;



    @Column(name = "time_period")
    private LocalDateTime timePeriod;

    public GovernanceMetrics(Long govId, Long companyId, String metricName, Double value, String unit, LocalDateTime timePeriod) {
        this.govId = govId;
        this.companyId = companyId;
        this.metricName = metricName;
        this.value = value;
        this.unit = unit;
        this.timePeriod=timePeriod;
    }

    public GovernanceMetrics() {

    }

    public Long getGovId() {
        return govId;
    }

    public void setGovId(Long govId) {
        this.govId = govId;
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
    public LocalDateTime getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(LocalDateTime timePeriod) {
        this.timePeriod = timePeriod;
    }

}
