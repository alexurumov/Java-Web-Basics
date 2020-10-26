package app.service;

import app.domain.model.service.HeroServiceModel;

import java.util.List;

public interface HeroService {

    void register(HeroServiceModel user);

    HeroServiceModel getById(String id);

    List<HeroServiceModel> getAll();

    void delete(String id);
}
