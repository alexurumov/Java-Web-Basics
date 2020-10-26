package app.web.beans;

import app.domains.models.view.JobViewModel;
import app.services.JobApplicationService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class HomeBean extends BaseBean implements Serializable {

    private List<JobViewModel> jobs;
    private JobApplicationService jobApplicationService;
    private ModelMapper modelMapper;

    public HomeBean() {
    }

    @Inject
    public HomeBean(JobApplicationService jobApplicationService, ModelMapper modelMapper) {
        this.jobApplicationService = jobApplicationService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init() {
        this.setJobs(jobApplicationService.getAll()
                .stream()
                .map(j -> modelMapper.map(j, JobViewModel.class))
                .collect(Collectors.toList()));
        this.getJobs().forEach(j -> j.setSector(j.getSector().toLowerCase()));
    }

    public List<JobViewModel> getJobs() {
        return jobs;
    }

    public void setJobs(List<JobViewModel> jobs) {
        this.jobs = jobs;
    }
}
