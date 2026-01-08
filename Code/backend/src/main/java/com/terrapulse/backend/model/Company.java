package com.terrapulse.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "company", schema = "terrapulse")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long companyId;

    @Column(name = "name")
    private String companyName;

    @Column(name = "industry")
    private String companyIndustry;

    @Column(name = "size")
    private double companySize;

    @Column(name = "location")
    private String companyLocation;

    public Company(Long companyId, String companyName, String companyIndustry, double companySize, String companyLocation) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.companyIndustry = companyIndustry;
        this.companySize = companySize;
        this.companyLocation = companyLocation;
    }

    public Company() {

    }

    public long getCompanyId() {
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

    public String getCompanyIndustry() {
        return companyIndustry;
    }

    public void setCompanyIndustry(String companyIndustry) {
        this.companyIndustry = companyIndustry;
    }

    public double getCompanySize() {
        return companySize;
    }

    public void setCompanySize(double companySize) {
        this.companySize = companySize;
    }

    public String getCompanyLocation() {
        return companyLocation;
    }

    public void setCompanyLocation(String companyLocation) {
        this.companyLocation = companyLocation;
    }


}
