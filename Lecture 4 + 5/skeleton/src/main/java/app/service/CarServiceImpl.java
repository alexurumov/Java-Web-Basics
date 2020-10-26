package app.service;

import app.domain.entities.Car;
import app.domain.models.CarModel;
import app.repositories.CarRepository;
import app.util.FileUtil;
import org.modelmapper.ModelMapper;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.io.IOException;

public class CarServiceImpl implements CarService {
    private static final String HOME_FILE_PATH = "/Users/macbookair/Downloads/Java Web Basics/Lecture4/skeleton/src/main/webapp/views/home.html";
    private static final String CREATE_FILE_PATH = "/Users/macbookair/Downloads/Java Web Basics/Lecture4/skeleton/src/main/webapp/views/create.html";
    private static final String ALL_FILE_PATH = "/Users/macbookair/Downloads/Java Web Basics/Lecture4/skeleton/src/main/webapp/views/all.html";

    private final FileUtil fileUtil;
    private final EntityManager entityManager;
    private final ModelMapper modelMapper;
    private final CarRepository carRepository;

    @Inject
    public CarServiceImpl(FileUtil fileUtil, EntityManager entityManager, ModelMapper modelMapper, CarRepository carRepository) {
        this.fileUtil = fileUtil;
        this.entityManager = entityManager;
        this.modelMapper = modelMapper;
        this.carRepository = carRepository;
    }


    @Override
    public String getHomePageHtmlString() throws IOException {
        return fileUtil.readFile(HOME_FILE_PATH);
    }

    @Override
    public String getAllCarsPageHtmlString() throws IOException {
        String html = fileUtil.readFile(ALL_FILE_PATH);
        StringBuilder sb = new StringBuilder();
        carRepository.getAllCars().stream()
                .map(car -> modelMapper.map(car, CarModel.class))
                .forEach(model -> {
                    sb.append(String.format(" <li class=\"d-flex justify-content-around\">\n" +
                            "<div class=\"col-md-4 d-flex flex-column text-center mb-3\">\n" +
                            "<h2 class=\"text-white text-center\">Brand: %s<h2/>\n" +
                            "<h4 class=\"text-white text-center\">Model: %s<h4/>\n" +
                            "<h4 class=\"text-white text-center\">Year: %d<h4/>\n" +
                            "<h4 class=\"text-white text-center\">Engine: %s<h4/>\n" +
                            "</div>\n" +
                            "</li>", model.getBrand(), model.getModel(), model.getYear(), model.getEngine()));
                });

        html = html.replace("{{replace}}", sb.toString());

        return html;
    }

    @Override
    public String getCreateCarPageHtmlString() throws IOException {
        return fileUtil.readFile(CREATE_FILE_PATH);
    }

    @Override
    public void uploadCar(CarModel carModel) {
        entityManager.getTransaction().begin();

        Car car = modelMapper.map(carModel, Car.class);
        entityManager.persist(car);

        entityManager.getTransaction().commit();
    }

}
