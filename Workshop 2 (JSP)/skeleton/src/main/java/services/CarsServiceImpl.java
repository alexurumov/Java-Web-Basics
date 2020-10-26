package services;

import models.entity.Car;
import models.entity.Engines;
import models.service.CarServiceModel;
import org.modelmapper.ModelMapper;
import repositories.CarRepository;
import repositories.UserRepository;
import services.base.CarsService;
import util.ValidationUtil;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class CarsServiceImpl implements CarsService {

    private final ModelMapper mapper;
    private final CarRepository carRepository;
    private final UserRepository userRepository;
    private final ValidationUtil validationUtil;

    @Inject
    public CarsServiceImpl(ModelMapper mapper, CarRepository carRepository, UserRepository userRepository, ValidationUtil validationUtil) {
        this.mapper = mapper;
        this.carRepository = carRepository;
        this.userRepository = userRepository;
        this.validationUtil = validationUtil;
    }

    @Override
    public List<CarServiceModel> getAll() {
        return carRepository.getAllCars()
                .stream()
                .map(car -> mapper.map(car, CarServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void createCar(String brand, String model, String year, String engine, String username) throws Exception {

        //Validation of input
        if (brand.trim().isEmpty() || model.trim().isEmpty() || year.trim().isEmpty() || engine.trim().isEmpty()) {
            throw new Exception("Car cannot be created");
        }

        CarServiceModel carModel = new CarServiceModel();
        carModel.setBrand(brand);
        carModel.setModel(model);
        carModel.setYear(year);
        carModel.setEngine(Engines.valueOf(engine));

        Car car = mapper.map(carModel, Car.class);
        if (!validationUtil.isValid(car)) {
            throw new Exception("Car cannot be created");
        }
        car.setUser(userRepository.getUserByUsername(username));

        carRepository.createCar(car);

    }
}
