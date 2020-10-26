package app.services;

import app.domains.models.service.JobAddServiceModel;
import app.domains.models.service.JobDetailsServiceModel;

import java.util.List;

public interface JobApplicationService {

    void save(JobAddServiceModel job);

    List<JobDetailsServiceModel> getAll();

    JobDetailsServiceModel getById(int id);

    void removeById(int id);

}
