package app.web.servlets;

import app.domain.models.CarModel;
import app.service.CarService;
import app.util.FileUtil;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/create")
public class CreateServlet extends HttpServlet {

    private final CarService carService;

    @Inject
    public CreateServlet(CarService carService) {
        this.carService = carService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String html = carService.getCreateCarPageHtmlString();
        resp.getWriter().println(html);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CarModel carModel = new CarModel();
        carModel.setBrand(req.getParameter("brand"));
        carModel.setModel(req.getParameter("model"));
        carModel.setYear(Integer.parseInt(req.getParameter("year")));
        carModel.setEngine(req.getParameter("engine"));

        carService.uploadCar(carModel);
        resp.sendRedirect("/");
    }
}
