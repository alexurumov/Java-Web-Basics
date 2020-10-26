package app.web;

import app.domain.entity.HeroClass;
import app.domain.model.binding.HeroCreateBindingModel;
import app.domain.model.service.HeroServiceModel;
import app.service.HeroService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class HeroCreateBean extends BaseBean {

    private HeroCreateBindingModel model;
    private HeroService heroService;
    private ModelMapper modelMapper;

    public HeroCreateBean() {
    }

    @Inject
    public HeroCreateBean(HeroService heroService, ModelMapper modelMapper) {
        this.heroService = heroService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init(){
        this.model = new HeroCreateBindingModel();
    }

    public void create(){

        HeroServiceModel hero = this.modelMapper.map(this.model, HeroServiceModel.class);
        heroService.register(hero);
        this.redirect("/home");
    }

    public HeroCreateBindingModel getModel() {
        return model;
    }

    public void setModel(HeroCreateBindingModel model) {
        this.model = model;
    }
}
