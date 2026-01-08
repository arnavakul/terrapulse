package com.terrapulse.backend.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;

@Entity
@Table(name = "targets", schema = "terrapulse")
public class Target {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "targetId")
    private Long targetId;

    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "metric")
    private String metric;

    @Column(name = "taget_value")
    private Double targetvalue;

    @Column(name = "start_date")
    private LocalDate sDate;

    @Column(name = "deadline_date")
    private LocalDate dDate;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private TargetStatus status;

    public Target(Long targetId, Long companyId, String metric, Double targetvalue, LocalDate sDate, LocalDate dDate, TargetStatus status) {
        this.targetId = targetId;
        this.companyId = companyId;
        this.metric = metric;
        this.targetvalue = targetvalue;
        this.sDate = sDate;
        this.dDate = dDate;
        this.status = status;
    }


    public Target() {

    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

    public Double getTargetvalue() {
        return targetvalue;
    }

    public void setTargetvalue(Double targetvalue) {
        this.targetvalue = targetvalue;
    }

    public LocalDate getsDate() {
        return sDate;
    }

    public void setsDate(LocalDate sDate) {
        this.sDate = sDate;
    }

    public LocalDate getdDate() {
        return dDate;
    }

    public void setdDate(LocalDate dDate) {
        this.dDate = dDate;
    }

    public TargetStatus getStatus() {
        return status;
    }

    public void setStatus(TargetStatus status) {
        this.status = status;
    }





}
