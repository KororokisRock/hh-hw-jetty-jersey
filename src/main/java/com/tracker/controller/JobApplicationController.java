package com.tracker.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tracker.dto.CreateJobApplicationRequest;
import com.tracker.model.JobApplication;
import com.tracker.service.JobApplicationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/applications")
public class JobApplicationController {

    private final JobApplicationService service;
    
    public JobApplicationController(JobApplicationService service) {
        this.service = service;
    }

    @GetMapping
    public List<JobApplication> getAllApplications() {
        return service.getAllApplications();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public JobApplication createApplication(@Valid @RequestBody CreateJobApplicationRequest applicationRequest) {
        return service.createApplication(applicationRequest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobApplication> getApplicationById(@PathVariable Long id) {
        return ResponseEntity.of(service.getApplicationById(id));
    }
}