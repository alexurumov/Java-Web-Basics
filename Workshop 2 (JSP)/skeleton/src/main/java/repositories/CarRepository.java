package repositories;

import models.entity.Car;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class CarRepository {
    private final EntityManager entityManager;

    @Inject
    public CarRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Car> getAllCars() {
        return entityManager.createQuery("SELECT c FROM Car c", Car.class).getResultList();
    }

    public void createCar(Car car) {
        entityManager.getTransaction().begin();
        entityManager.persist(car);
        entityManager.getTransaction().commit();
    }
}
