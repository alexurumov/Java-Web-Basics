package services.base;

import models.service.CarServiceModel;

import java.util.List;

public interface CarsService {
    List<CarServiceModel> getAll();

    void createCar(String brand, String model, String year, String engine, String username) throws Exception;

}
