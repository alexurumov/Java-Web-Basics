package app.services;


import app.domain.models.service.UserRegisterServiceModel;
import app.domain.models.service.UserServiceModel;

import java.util.List;

public interface UserService {

    UserRegisterServiceModel getByUsernameAndPassword(String username, String password);

    void save(UserRegisterServiceModel model);

    UserRegisterServiceModel getByUsername(String username);

    List<UserServiceModel> getAll();

    void addFriend(String username, String friendUsername);
}
