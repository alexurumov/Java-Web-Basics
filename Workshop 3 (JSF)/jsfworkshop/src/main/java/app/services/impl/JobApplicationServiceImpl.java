package app.services.impl;

import app.domains.entities.JobApplication;
import app.domains.models.service.JobAddServiceModel;
import app.domains.models.service.JobDetailsServiceModel;
import app.repositories.JobApplicationRepository;
import app.services.JobApplicationService;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class JobApplicationServiceImpl implements JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;
    private final ModelMapper modelMapper;

    @Inject
    public JobApplicationServiceImpl(JobApplicationRepository jobApplicationRepository, ModelMapper modelMapper) {
        this.jobApplicationRepository = jobApplicationRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void save(JobAddServiceModel job) {
        JobApplication jobApplication = modelMapper.map(job, JobApplication.class);
        jobApplicationRepository.save(jobApplication);
    }

    @Override
    public void removeById(int id) {
        this.jobApplicationRepository.deleteById(id);
    }

    @Override
    public List<JobDetailsServiceModel> getAll() {
        return this.jobApplicationRepository.findAll()
                .stream()
                .map(j -> modelMapper.map(j, JobDetailsServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public JobDetailsServiceModel getById(int id) {
        JobApplication job = jobApplicationRepository.findJobApplicationById(id);

        if (job == null) {
            return null;
        }

        return modelMapper.map(job, JobDetailsServiceModel.class);
    }
}
