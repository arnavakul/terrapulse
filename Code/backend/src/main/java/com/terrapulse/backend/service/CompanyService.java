package com.terrapulse.backend.service;

import com.terrapulse.backend.model.Company;
import com.terrapulse.backend.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService( CompanyRepository companyRepository ){
        this.companyRepository=companyRepository;
    }

    public Company createCompany(Company company){
        return companyRepository.save(company);
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Company getCompanyById(Long id){
        return companyRepository.findById(id).orElseThrow(() -> new RuntimeException("Company Not Found"));
    }

    public Company updateCompany(Long id, Company updatedCompany){
        Company existing = getCompanyById(id);
        existing.setCompanyName(updatedCompany.getCompanyName());
        existing.setCompanyIndustry(updatedCompany.getCompanyIndustry());
        existing.setCompanySize(updatedCompany.getCompanySize());
        existing.setCompanyLocation(updatedCompany.getCompanyLocation());
        return companyRepository.save(existing);
    }

    public void deleteCompany(Long id){
        companyRepository.deleteById(id);
    }

}
