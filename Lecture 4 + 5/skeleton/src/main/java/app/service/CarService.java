package app.service;

import app.domain.models.CarModel;

import java.io.IOException;

public interface CarService {

    String getHomePageHtmlString() throws IOException;

    String getAllCarsPageHtmlString() throws IOException;

    String getCreateCarPageHtmlString() throws IOException;

    void uploadCar(CarModel carModel);
}
