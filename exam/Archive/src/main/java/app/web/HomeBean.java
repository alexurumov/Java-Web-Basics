package app.web;

import app.domain.model.view.HomeViewModel;
import app.service.HeroService;
import app.service.UserService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class HomeBean extends BaseBean {

    private List<HomeViewModel> models;
    private HeroService heroService;
    private ModelMapper modelMapper;

    public HomeBean() {
    }

    @Inject
    public HomeBean(HeroService heroService, ModelMapper modelMapper) {
        this.heroService = heroService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init() {
        this.setModels(heroService
                .getAll()
                .stream()
                .sorted((h1, h2) -> Integer.compare(h2.getLevel(), h1.getLevel()))
                .map(h -> modelMapper.map(h, HomeViewModel.class)).collect(Collectors.toList()));
    }

    public List<HomeViewModel> getModels() {
        return models;
    }

    public void setModels(List<HomeViewModel> models) {
        this.models = models;
    }
}
