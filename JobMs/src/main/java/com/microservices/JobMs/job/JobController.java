package com.microservices.JobMs.job;

import com.microservices.JobMs.job.dtos.JobCompanyDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<JobCompanyDto>> getAllJobs(){
        List<JobCompanyDto> jobs = jobService.getAllJobs();
        if(jobs.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(jobs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable("id") int id){
        Job job = jobService.getJob(id);
        if (job == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(job);
    }

    @PostMapping
    public ResponseEntity<String> addJob(@RequestBody Job job){
        if(jobService.addJob(job))
            return new ResponseEntity<>("Job Created",HttpStatus.CREATED);
        else
            return new ResponseEntity<>("Job not created",HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
