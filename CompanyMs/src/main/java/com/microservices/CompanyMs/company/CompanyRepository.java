package com.microservices.CompanyMs.company;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

    public Company findByName(String name);
}
