package app.service.impl;

import app.domain.entity.Hero;
import app.domain.entity.HeroClass;
import app.domain.model.service.HeroServiceModel;
import app.repository.HeroRepository;
import app.service.HeroService;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class HeroServiceImpl implements HeroService {

    private final HeroRepository heroRepository;
    private final ModelMapper mapper;

    @Inject
    public HeroServiceImpl(HeroRepository heroRepository, ModelMapper mapper) {
        this.heroRepository = heroRepository;
        this.mapper = mapper;
    }

    @Override
    public void register(HeroServiceModel hero) {
        Hero map = mapper.map(hero, Hero.class);
        map.setHeroClass(HeroClass.valueOf(hero.getHeroClass()));
        this.heroRepository.save(map);
    }

    @Override
    public HeroServiceModel getById(String id) {
        return mapper.map(this.heroRepository.findById(id), HeroServiceModel.class);
    }

    @Override
    public List<HeroServiceModel> getAll() {
        return this.heroRepository.findAll().stream().map(h -> mapper.map(h, HeroServiceModel.class)).collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {
        this.heroRepository.delete(id);
    }
}
