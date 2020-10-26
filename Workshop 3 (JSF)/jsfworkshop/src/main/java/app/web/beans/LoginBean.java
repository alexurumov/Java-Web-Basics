package app.web.beans;

import app.domains.models.binding.user.UserLoginBindingModel;
import app.domains.models.service.UserServiceModel;
import app.services.UserService;
import org.apache.commons.codec.digest.DigestUtils;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@RequestScoped
public class LoginBean extends BaseBean implements Serializable {

    private UserLoginBindingModel user;
    private UserService userService;

    public LoginBean() {
    }

    @Inject
    public LoginBean(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void init() {
        this.user = new UserLoginBindingModel();
    }

    public void login() {

        UserServiceModel user = userService.getByUsernameAndPassword(this.user.getUsername(), DigestUtils.sha256Hex(this.user.getPassword()));

        if (user == null) {
            this.redirect("/login");
            return;
        }

        FacesContext.getCurrentInstance().getExternalContext()
                .getSessionMap()
                .put("username", this.getUser().getUsername());
        this.redirect("/home");
    }

    public UserLoginBindingModel getUser() {
        return user;
    }

    public void setUser(UserLoginBindingModel user) {
        this.user = user;
    }
}
