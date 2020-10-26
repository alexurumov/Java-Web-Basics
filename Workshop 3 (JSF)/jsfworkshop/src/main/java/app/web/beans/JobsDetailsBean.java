package app.web.beans;

import app.domains.models.view.JobViewModel;
import app.services.JobApplicationService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

@Named
@RequestScoped
public class JobsDetailsBean extends BaseBean implements Serializable {

    private JobApplicationService jobApplicationService;
    private ModelMapper modelMapper;

    public JobsDetailsBean() {
    }

    @Inject
    public JobsDetailsBean(JobApplicationService jobApplicationService, ModelMapper modelMapper) {
        this.jobApplicationService = jobApplicationService;
        this.modelMapper = modelMapper;
    }

    public JobViewModel getJobById(int id) {
        return modelMapper.map(jobApplicationService.getById(id), JobViewModel.class);
    }

//    public void deleteJob() {
//        int id = Integer.parseInt(((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getParameter("id"));
//
//        jobApplicationService.removeById(id);
//        this.redirect("/home");
//    }
}
