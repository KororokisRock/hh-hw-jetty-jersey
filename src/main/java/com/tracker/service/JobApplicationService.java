package com.tracker.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.tracker.dto.CreateJobApplicationRequest;
import com.tracker.model.ApplicationStatus;
import com.tracker.model.JobApplication;

@Service
public class JobApplicationService {

    private final Map<Long, JobApplication> applications = new LinkedHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public List<JobApplication> getAllApplications() {
        return new ArrayList<>(applications.values());
    }

    public JobApplication createApplication(CreateJobApplicationRequest createJobApplication) {
        JobApplication application = new JobApplication();

        application.setId(idGenerator.getAndIncrement());
        application.setApplicantName(createJobApplication.getApplicantName());
        application.setCompanyName(createJobApplication.getCompanyName());
        application.setVacancyTitle(createJobApplication.getVacancyTitle());
        application.setStatus(ApplicationStatus.PENDING);
        application.setCreatedAt(LocalDateTime.now());

        applications.put(application.getId(), application);
        return application;
    }

    public Optional<JobApplication> getApplicationById(Long id) {
        return Optional.ofNullable(applications.get(id));
    }
}
