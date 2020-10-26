package app.repository.impl;

import app.domain.entity.User;
import app.repository.UserRepository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private final EntityManager manager;

    @Inject
    public UserRepositoryImpl(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void save(User user) {
        manager.getTransaction().begin();
        manager.persist(user);
        manager.getTransaction().commit();
    }

    @Override
    public User findById(String id) {
        return manager.createQuery("SELECT u FROM User u WHERE u.id = :id", User.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        return manager.createQuery("SELECT u FROM User u WHERE u.username = :username AND u.password = :password", User.class)
                .setParameter("username", username)
                .setParameter("password", password)
                .getSingleResult();
    }

}
