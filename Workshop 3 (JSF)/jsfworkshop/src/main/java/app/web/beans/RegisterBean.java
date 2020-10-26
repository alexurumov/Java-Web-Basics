package app.web.beans;

import app.domains.models.binding.user.UserRegisterBindingModel;
import app.domains.models.service.UserServiceModel;
import app.services.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@RequestScoped
public class RegisterBean extends BaseBean implements Serializable {

    private UserRegisterBindingModel user;
    private UserService userService;
    private ModelMapper modelMapper;

    public RegisterBean() {
    }

    @Inject
    public RegisterBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init() {
        this.user = new UserRegisterBindingModel();
    }

    public void register() {

        if (!isValid(user)) {
            this.redirect("/register");
        } else {
            UserServiceModel user = modelMapper.map(this.user, UserServiceModel.class);
            user.setPassword(DigestUtils.sha256Hex(this.user.getPassword()));
            userService.save(user);
            this.redirect("/login");
        }
    }

    private boolean isValid(UserRegisterBindingModel user) {
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            return false;
        }

        UserServiceModel testUser = userService.getByUsername(user.getUsername());
        return testUser == null;
    }

    public UserRegisterBindingModel getUser() {
        return user;
    }

    public void setUser(UserRegisterBindingModel user) {
        this.user = user;
    }
}
