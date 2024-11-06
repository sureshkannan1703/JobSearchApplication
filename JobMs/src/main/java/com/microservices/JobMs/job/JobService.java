package com.microservices.JobMs.job;

import com.microservices.JobMs.job.dtos.JobCompanyDto;

import java.util.List;

public interface JobService {

    public List<JobCompanyDto> getAllJobs();

    public Job getJob(int id);

    public boolean addJob(Job job);

}
