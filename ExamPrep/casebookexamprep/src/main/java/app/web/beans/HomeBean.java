package app.web.beans;

import app.domain.entities.User;
import app.domain.models.service.UserRegisterServiceModel;
import app.domain.models.service.UserServiceModel;
import app.domain.models.view.UserViewModel;
import app.services.UserService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class HomeBean extends BaseBean {

    private List<UserViewModel> users;
    private UserService userService;
    private ModelMapper modelMapper;

    public HomeBean() {
    }

    @Inject
    public HomeBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init() {
        String username = (String) ((HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false)).getAttribute("username");

        this.setUsers(this.userService.getAll()
                .stream()
                .filter(u -> !u.getUsername().equals(username) &&
                        !u.getFriends().stream()
                                .map(UserServiceModel::getUsername)
                                .collect(Collectors.toList()).contains(username))
                .map(u -> this.modelMapper.map(u, UserViewModel.class))
                .collect(Collectors.toList()));
    }

    public void addFriend(String friendUsername) {
        String username = (String) ((HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false)).getAttribute("username");

        userService.addFriend(username, friendUsername);



        this.redirect("/home");
    }

    public List<UserViewModel> getUsers() {
        return users;
    }

    public void setUsers(List<UserViewModel> users) {
        this.users = users;
    }
}
