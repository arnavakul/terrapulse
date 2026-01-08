package com.terrapulse.backend.controller;

import com.terrapulse.backend.model.Company;
import com.terrapulse.backend.recommendation.Recommendation;
import com.terrapulse.backend.service.CompanyService;
import com.terrapulse.backend.service.TargetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService companyService;
    private final TargetService targetService;

    public CompanyController(CompanyService companyService, TargetService targetService){
        this.companyService=companyService;
        this.targetService = targetService;
    }

    @PostMapping
    public Company createCompany(@RequestBody Company company){
        return companyService.createCompany(company);
    }

    @GetMapping
    public List<Company> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    @GetMapping("/{id}")
    public Company getCompany(@PathVariable Long id) {
        return companyService.getCompanyById(id);
    }

    @GetMapping("/{companyId}/recommendations")
    public List<Recommendation> getRecommendations(
            @PathVariable Long companyId
    ) {
        return targetService.getRecommendationForCompany(companyId);
    }

    @PutMapping("/{id}")
    public Company updateCompany(
            @PathVariable Long id,
            @RequestBody Company company) {
        return companyService.updateCompany(id, company);
    }

    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable Long id) {
        companyService.deleteCompany(id);
    }



}
