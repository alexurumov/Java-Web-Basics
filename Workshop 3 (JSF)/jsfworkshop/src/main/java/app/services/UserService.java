package app.services;

import app.domains.models.service.UserServiceModel;

public interface UserService {

    UserServiceModel getByUsernameAndPassword(String username, String password);

    void save(UserServiceModel model);

    UserServiceModel getByUsername(String username);
}
