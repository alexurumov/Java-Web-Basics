package app.repositories;

import app.domains.entities.User;

import java.util.List;

public interface UserRepository {

    void save(User user);

    List<User> findAll();

    User findById(int id);

    User findByUsernameAndPassword(String username, String password);

    User findByUsername(String username);
}
