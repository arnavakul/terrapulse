package com.terrapulse.backend.dashboard;

import com.terrapulse.backend.model.Company;
import com.terrapulse.backend.model.Target;
import com.terrapulse.backend.model.TargetStatus;
import com.terrapulse.backend.recommendation.Recommendation;
import com.terrapulse.backend.service.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService {
    private final CompanyService companyService;
    private final TargetService targetService;
    private final EnvironmentalMetricsService environmentalMetricsService;
    private final SocialMetricService socialMetricService;
    private final GovernanceMetricsService governanceMetricsService;


    public DashboardService(CompanyService companyService, TargetService targetService, EnvironmentalMetricsService environmentalMetricsService, SocialMetricService socialMetricService, GovernanceMetricsService governanceMetricsService) {
        this.companyService = companyService;
        this.targetService = targetService;
        this.environmentalMetricsService = environmentalMetricsService;
        this.socialMetricService = socialMetricService;
        this.governanceMetricsService = governanceMetricsService;
    }

    public DashboardResponse getDashboardForCompany(Long companyId){
        Company company = companyService.getCompanyById(companyId);

        DashboardResponse response = new DashboardResponse();
        response.setCompanyId(company.getCompanyId());
        response.setCompanyName(company.getCompanyName());


        List<Target> targets = targetService.getAllTargetForCompany(companyId);

        int achived = 0;
        int atRisk = 0;
        int missed = 0;

        for(Target target : targets){
            Target evaluated = targetService.evaluateTarget(target);

            if (evaluated.getStatus() == TargetStatus.ACHIEVED) achived++;
            if (evaluated.getStatus() == TargetStatus.AT_RISK) atRisk++;
            if (evaluated.getStatus() == TargetStatus.MISSED) missed++;
        }

        response.setTotalTargets(targets.size());
        response.setAchievedTargets(achived);
        response.setAtRiskTargets(atRisk);
        response.setMissedTargets(missed);

        int envScore = environmentalMetricsService.calculateEnvironmentalScoreForCompany(companyId);
        int socScore = socialMetricService.calculateSocialMetricScoreForCompany(companyId);
        int govScore = governanceMetricsService.calculateGovernanceMetricScore(companyId);

        int overAll = (envScore+socScore+govScore)/3;

        List<Recommendation> recommendations = targetService.getRecommendationForCompany(companyId);
        response.setRecommendations(recommendations);

        return response;

    }
}
