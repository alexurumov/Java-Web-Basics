package app.web.beans;

import app.domains.entities.Sector;
import app.domains.models.binding.job.JobBindingModel;
import app.domains.models.service.JobAddServiceModel;
import app.domains.models.service.JobDetailsServiceModel;
import app.services.JobApplicationService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@RequestScoped
public class JobsAddBean extends BaseBean implements Serializable {

    private JobBindingModel job;
    private JobApplicationService jobApplicationService;
    private ModelMapper modelMapper;

    public JobsAddBean() {
    }

    @Inject
    public JobsAddBean(JobApplicationService jobApplicationService, ModelMapper modelMapper) {
        this.jobApplicationService = jobApplicationService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init() {
        this.job = new JobBindingModel();
    }

    public void addJob() {
        if (!sectorIsValid(job.getSector())) {
            this.redirect("/add-job");
        } else {
            job.setSector(job.getSector().toUpperCase());
            jobApplicationService.save(modelMapper.map(this.job, JobAddServiceModel.class));
            this.redirect("/home");
        }
    }

    private boolean sectorIsValid(String sector) {
        for (Sector s : Sector.values()) {
            if (s.name().equals(sector.toUpperCase())) {
                return true;
            }
        }

        return false;
    }

    public JobBindingModel getJob() {
        return job;
    }

    public void setJob(JobBindingModel job) {
        this.job = job;
    }
}
