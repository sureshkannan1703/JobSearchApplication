package com.microservices.JobMs.job.clients;

import com.microservices.JobMs.job.external.Company;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="CompanyMs")
public interface CompanyClient {

    @GetMapping("company/{id}")
    Company getCompanyById(@PathVariable("id") int id);
}
