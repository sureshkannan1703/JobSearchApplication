package com.microservices.JobMs.job.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.microservices.JobMs.job.Job;
import com.microservices.JobMs.job.external.Company;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class JobCompanyDto {

    private int id;

    private String name;

    private String description;

    private Company company;
}
