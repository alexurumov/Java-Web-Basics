package app.web.beans;

import app.domain.entities.Gender;
import app.domain.models.binding.UserRegisterBindingModel;
import app.domain.models.service.UserRegisterServiceModel;
import app.services.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@RequestScoped
public class UserRegisterBean extends BaseBean {

    private UserRegisterBindingModel userModel;
    private UserService userService;
    private ModelMapper modelMapper;

    public UserRegisterBean() {
    }

    @Inject
    public UserRegisterBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init() {
        this.userModel = new UserRegisterBindingModel();
    }

    public void register() {

        if (!isValid(userModel)) {
            this.redirect("/register");
            return;
        }

        UserRegisterServiceModel user = modelMapper.map(userModel, UserRegisterServiceModel.class);
        user.setPassword(DigestUtils.sha256Hex(userModel.getPassword()));
        userService.save(user);
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        this.redirect("/login");

    }

    private boolean isValid(UserRegisterBindingModel user) {
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            return false;
        }

        if (!isValidGender(user.getGender())) {
            return false;
        }

        UserRegisterServiceModel testUser = userService.getByUsername(user.getUsername());
        return testUser == null;
    }

    private boolean isValidGender(String gender) {
        for (Gender g : Gender.values()) {
            if (g.name().equals(gender.toLowerCase())) {
                return true;
            }
        }

        return false;
    }

    public UserRegisterBindingModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserRegisterBindingModel userModel) {
        this.userModel = userModel;
    }
}
