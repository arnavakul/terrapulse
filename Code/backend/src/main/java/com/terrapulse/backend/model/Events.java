package com.terrapulse.backend.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "events", schema = "terrapulse")
public class Events {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eventId")
    private Long eventId;

    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "category")
    private String category;

    @Column(name = "severity")
    private String severity;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "description")
    private String description;

    public Events(Long companyId, Long eventId, String category, String severity, LocalDateTime date, String description) {
        this.eventId = eventId;
        this.companyId = companyId;
        this.category = category;
        this.severity = severity;
        this.date = date;
        this.description = description;
    }

    public Events() {

    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

}
