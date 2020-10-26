package app.repositories;

import app.domain.entities.User;
import java.util.List;

public interface UserRepository {

    void save(User user);

    List<User> findAll();

    User findById(int id);

    User findByUsernameAndPassword(String username, String password);

    User findByUsername(String username);

    void update(User user);
}
