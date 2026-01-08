package com.terrapulse.backend.dashboard;

import com.terrapulse.backend.recommendation.Recommendation;

import java.util.List;

public class DashboardResponse {

    private Long companyId;
    private String companyName;

    private Integer environmentalScore;
    private Integer socialScore;
    private Integer governanceScore;
    private Integer overallScore;

    private int totalTargets;
    private int achievedTargets;
    private int atRiskTargets;
    private int missedTargets;

    private List<Recommendation> recommendations;


    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getEnvironmentalScore() {
        return environmentalScore;
    }

    public void setEnvironmentalScore(Integer environmentalScore) {
        this.environmentalScore = environmentalScore;
    }

    public Integer getSocialScore() {
        return socialScore;
    }

    public void setSocialScore(Integer socialScore) {
        this.socialScore = socialScore;
    }

    public Integer getGovernanceScore() {
        return governanceScore;
    }

    public void setGovernanceScore(Integer governanceScore) {
        this.governanceScore = governanceScore;
    }

    public Integer getOverallScore() {
        return overallScore;
    }

    public void setOverallScore(Integer overallScore) {
        this.overallScore = overallScore;
    }

    public int getTotalTargets() {
        return totalTargets;
    }

    public void setTotalTargets(int totalTargets) {
        this.totalTargets = totalTargets;
    }

    public int getAchievedTargets() {
        return achievedTargets;
    }

    public void setAchievedTargets(int achievedTargets) {
        this.achievedTargets = achievedTargets;
    }

    public int getAtRiskTargets() {
        return atRiskTargets;
    }

    public void setAtRiskTargets(int atRiskTargets) {
        this.atRiskTargets = atRiskTargets;
    }

    public int getMissedTargets() {
        return missedTargets;
    }

    public void setMissedTargets(int missedTargets) {
        this.missedTargets = missedTargets;
    }

    public List<Recommendation> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(List<Recommendation> recommendations) {
        this.recommendations = recommendations;
    }



}
