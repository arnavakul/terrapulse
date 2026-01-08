package com.terrapulse.backend.recommendation;

import com.terrapulse.backend.model.Target;
import com.terrapulse.backend.model.TargetStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class RecommendationService {
    public Recommendation generateRecommendation(Target target){

        if (target.getStatus() == TargetStatus.ACHIEVED ||
            target.getStatus() == TargetStatus.IN_PROGRESS) {
            return null;
        }

        String metric = target.getMetric().toLowerCase();

        if (metric.equals("carbon_emissions")){
            return buildCarbonRecommendation(target);
        }

        if (metric.equals("injury_rate")){
            return buildInjuryRecommendation(target);
        }

        if (metric.equals("policy_violations")){
            return buildGovernanceRecommendation(target);
        }

        return null;
    }



    private Recommendation buildCarbonRecommendation(Target target) {

        String severity;

        if(target.getStatus() == TargetStatus.MISSED){
            severity = "HIGH";
        }else {
            severity = "MEDIUM";
        }


        return new Recommendation(
                "carbon_emission",
                "ENVIRONMENTAL",
                severity,
                "Reduce the use of fossil fuel." +
                         "Increase the use of renewable energy to meet the carbon emission targets."
        );

    }

    private Recommendation buildGovernanceRecommendation(Target target) {

        String severity;

        if(target.getStatus() == TargetStatus.MISSED){
            severity = "HIGH";
        }else {
            severity = "MEDIUM";
        }

        return new Recommendation(
                "policy_violations",
                "Governance",
                severity,
                "Impose strict actions against policy violations." +
                "Ensure fairness and equality between the employees in the workspace."
        );
    }

    private Recommendation buildInjuryRecommendation(Target target) {
        String severity;

        if(target.getStatus() == TargetStatus.MISSED){
            severity = "HIGH";
        }else {
            severity = "MEDIUM";
        }

        return new Recommendation(
                "injury_rate",
                "Social",
                severity,
                "Increase safety in the work-place area." +
                        "Reduce the usage of hazardous items/substances without appropriate safety measures." +
                        "Conduct 'Work-Space Safety' workshops and events regularly, regarding the importance of the same and also to keep the workspace interactive and good."
        );
    }

}
