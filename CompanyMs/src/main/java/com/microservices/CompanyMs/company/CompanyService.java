package com.microservices.CompanyMs.company;

import java.util.List;

public interface CompanyService {

    public Company getCompany(String companyName);

    public List<Company> getAllCompanies();

    public Company addCompany(Company company);

    public Company getCompanyById(int id);

}
