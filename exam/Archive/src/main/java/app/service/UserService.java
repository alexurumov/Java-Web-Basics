package app.service;

import app.domain.model.service.UserServiceModel;

import java.util.List;

public interface UserService {

    void register(UserServiceModel user);

    UserServiceModel getById(String id);

    UserServiceModel getByUsernameAndPassword(String username, String password);

}
