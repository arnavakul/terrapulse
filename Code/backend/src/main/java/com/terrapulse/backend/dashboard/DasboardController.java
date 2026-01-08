package com.terrapulse.backend.dashboard;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
public class DasboardController {
    private final DashboardService dashboardService;

    public DasboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/company/{companyId}")
    public DashboardResponse getDashboard(@PathVariable Long companyId){
        return dashboardService.getDashboardForCompany(companyId);
    }

}
