package app.repositories;

import app.domain.entities.Car;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class CarRepository {

    private final EntityManager entityManager;
    private final ModelMapper modelMapper;

    @Inject
    public CarRepository(EntityManager entityManager, ModelMapper modelMapper) {
        this.entityManager = entityManager;
        this.modelMapper = modelMapper;
    }

    public List<Car> getAllCars() {
        return entityManager.createQuery("FROM Car").getResultList();
    }
}
