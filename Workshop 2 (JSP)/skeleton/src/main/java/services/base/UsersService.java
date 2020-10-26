package services.base;

import models.service.UserServiceModel;

public interface UsersService {
    void register(String username, String password, String confirmPassword, String email) throws Exception;

    UserServiceModel login(String username, String password);
}
