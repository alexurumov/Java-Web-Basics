package app.web.beans;

import app.domain.models.binding.UserLoginBindingModel;
import app.domain.models.service.UserRegisterServiceModel;
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
public class UserLoginBean extends BaseBean {

    private UserLoginBindingModel user;
    private UserService userService;

    public UserLoginBean() {
    }

    @Inject
    public UserLoginBean(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void init() {
        this.user = new UserLoginBindingModel();
    }

    public void login() {

        UserRegisterServiceModel user = userService.getByUsernameAndPassword(this.user.getUsername(), DigestUtils.sha256Hex(this.user.getPassword()));

        if (user == null) {
            this.redirect("/login");
            return;
        }

        FacesContext.getCurrentInstance().getExternalContext()
                .getSessionMap()
                .put("username", user.getUsername());

        this.redirect("/home");
    }

    public UserLoginBindingModel getUser() {
        return user;
    }

    public void setUser(UserLoginBindingModel user) {
        this.user = user;
    }
}
