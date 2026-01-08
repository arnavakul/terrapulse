package com.terrapulse.backend.recommendation;

public class Recommendation {

    private String metricName;
    private String category;
    private String severity;
    private String message;

    public Recommendation(String metricName, String category, String severity, String message) {
        this.metricName = metricName;
        this.category = category;
        this.severity = severity;
        this.message = message;
    }
    public String getMetricName() {
        return metricName;
    }

    public void setMetricName(String metricName) {
        this.metricName = metricName;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
