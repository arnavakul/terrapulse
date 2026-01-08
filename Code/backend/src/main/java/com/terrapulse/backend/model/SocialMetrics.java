package com.terrapulse.backend.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "social_metric", schema = "terrapulse")
public class SocialMetrics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "social_id")
    private Long sId;

    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "empl_count")
    private Long employeeCount;
    @Column(name = "metric_name")
    private String metricName;

    @Column(name = "value")
    private Double value;

    @Column(name = "unit")
    private String unit;


    @Column(name = "time_period")
    private LocalDateTime timePeriod;

    public SocialMetrics(Long sId, Long companyId, Long employeeCount, String metricName, Double value, String unit, LocalDateTime timePeriod) {
        this.sId = sId;
        this.companyId = companyId;
        this.employeeCount = employeeCount;
        this.metricName = metricName;
        this.value = value;
        this.unit = unit;
        this.timePeriod = timePeriod;
    }

    public Long getsId() {
        return sId;
    }

    public void setsId(Long sId) {
        this.sId = sId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(Long employeeCount) {
        this.employeeCount = employeeCount;
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



    public SocialMetrics() {

    }
}

