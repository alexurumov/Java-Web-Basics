package web;

import services.base.UsersService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/users/register")
public class UsersRegisterServlet extends HttpServlet {

    private final UsersService usersService;

    @Inject
    public UsersRegisterServlet(UsersService usersService) {
        this.usersService = usersService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/user-register.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username").toString();
        String password = req.getParameter("password").toString();
        String confirmPassword = req.getParameter("confirmPassword").toString();
        String email = req.getParameter("email").toString();

        try {
            usersService.register(username, password, confirmPassword, email);
            resp.sendRedirect("/users/login");
        } catch (Exception e) {
            resp.sendRedirect("/users/register");
        }
    }
}
