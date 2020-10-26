package app.web.beans;

import app.domains.models.binding.job.JobBindingModel;
import app.domains.models.view.JobViewModel;
import app.services.JobApplicationService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

@Named
@RequestScoped
public class JobsDeleteBean extends BaseBean implements Serializable {

    private JobApplicationService jobApplicationService;
    private ModelMapper modelMapper;

    public JobsDeleteBean() {
    }

    @Inject
    public JobsDeleteBean(JobApplicationService jobApplicationService, ModelMapper modelMapper) {
        this.jobApplicationService = jobApplicationService;
        this.modelMapper = modelMapper;
    }

    public JobViewModel getJobById(int id) {
        return modelMapper.map(jobApplicationService.getById(id), JobViewModel.class);
    }

    public void deleteJob() {
        // GET ID PARAMETER FROM REQUEST

        int id = Integer.parseInt(((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getParameter("id"));

        jobApplicationService.removeById(id);
        this.redirect("/home");
    }
}
