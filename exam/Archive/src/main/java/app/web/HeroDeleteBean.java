package app.web;

import app.domain.model.view.HomeViewModel;
import app.service.HeroService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named
@ApplicationScoped
public class HeroDeleteBean extends BaseBean {

    private HeroService heroService;
    private ModelMapper modelMapper;

    public HeroDeleteBean() {
    }

    @Inject
    public HeroDeleteBean(HeroService heroService, ModelMapper modelMapper) {
        this.heroService = heroService;
        this.modelMapper = modelMapper;
    }

    public HomeViewModel getHeroById(String id){
        return this.modelMapper.map(this.heroService.getById(id), HomeViewModel.class);
    }

    public void delete(){
        String id = ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getParameter("id");
        this.heroService.delete(id);
        this.redirect("/home");
    }


}
