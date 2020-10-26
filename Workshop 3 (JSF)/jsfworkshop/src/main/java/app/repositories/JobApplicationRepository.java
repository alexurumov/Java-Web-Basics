package app.repositories;

import app.domains.entities.JobApplication;

import java.util.List;

public interface JobApplicationRepository {

    void save(JobApplication jobApplication);

    List<JobApplication> findAll();

    JobApplication findJobApplicationById(int id);

    void deleteById(int id);
}
