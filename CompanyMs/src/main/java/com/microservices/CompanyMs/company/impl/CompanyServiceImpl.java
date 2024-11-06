package com.microservices.CompanyMs.company.impl;

import com.microservices.CompanyMs.company.Company;
import com.microservices.CompanyMs.company.CompanyRepository;
import com.microservices.CompanyMs.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    CompanyRepository companyRepository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Company getCompany(String companyName) {
        return companyRepository.findByName(companyName);
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company addCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public Company getCompanyById(int id) {
        Optional<Company> company =  companyRepository.findById(id);
        if (company.isPresent()) {
            return company.get();
        }
        return null;
    }

}
