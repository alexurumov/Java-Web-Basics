package app.services.impl;

import app.domains.entities.User;
import app.domains.models.service.UserServiceModel;
import app.repositories.UserRepository;
import app.services.UserService;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Inject
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserServiceModel getByUsername(String username) {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            return null;
        }

        return modelMapper.map(user, UserServiceModel.class);
    }

    @Override
    public void save(UserServiceModel model) {

        userRepository.save(modelMapper.map(model, User.class));
    }

    @Override
    public UserServiceModel getByUsernameAndPassword(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, password);

        if (user == null) {
            return null;
        }

        return modelMapper.map(user, UserServiceModel.class);
    }
}
