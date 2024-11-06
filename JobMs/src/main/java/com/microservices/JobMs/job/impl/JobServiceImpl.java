package com.microservices.JobMs.job.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.JobMs.job.Job;
import com.microservices.JobMs.job.JobRepository;
import com.microservices.JobMs.job.JobService;
import com.microservices.JobMs.job.clients.CompanyClient;
import com.microservices.JobMs.job.dtos.JobCompanyDto;
import com.microservices.JobMs.job.external.Company;
import feign.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {

    JobRepository jobRepository;

    //RestTemplate restTemplate;        //if use RestTemplate for communication.

    CompanyClient companyClient;

    ObjectMapper objectMapper;

    @Autowired
    public JobServiceImpl(JobRepository jobRepository, ObjectMapper objectMapper, CompanyClient companyClient) {
        this.jobRepository = jobRepository;
        //this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
        this.companyClient = companyClient;
    }

    @Override
    public List<JobCompanyDto> getAllJobs() {

        List<Job> jobs = jobRepository.findAll();
        List<JobCompanyDto> jobCompanyDtos = jobs.stream().map(job-> convertJobCompanyDto(job)).collect(Collectors.toList());
        return jobCompanyDtos;
    }

    private JobCompanyDto convertJobCompanyDto(Job job) {

       // Company company = restTemplate.getForObject("http://COMPANYMS:8082/company/"+job.getCompanyId(), Company.class);  //Communication using RestTemplate.
        Company company = companyClient.getCompanyById(job.getCompanyId());           //Communication using feign client.
        JobCompanyDto jobCompanyDto = new JobCompanyDto();
        jobCompanyDto = objectMapper.convertValue(job, jobCompanyDto.getClass());
        jobCompanyDto.setCompany(company);
        return jobCompanyDto;
    }

    @Override
    public Job getJob(int id) {
        Optional<Job> optionalJob = jobRepository.findById(Long.valueOf(id));
        if (optionalJob.isPresent()) {
            return optionalJob.get();
        }
        return null;
    }

    @Override
    public boolean addJob(Job job) {
        if(job != null){
            jobRepository.save(job);
            return true;
        }
        return false;
    }
}
