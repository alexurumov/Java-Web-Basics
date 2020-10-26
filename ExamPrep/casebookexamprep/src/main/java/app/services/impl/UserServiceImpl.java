package app.services.impl;

import app.domain.entities.User;
import app.domain.models.service.UserRegisterServiceModel;
import app.domain.models.service.UserServiceModel;
import app.repositories.UserRepository;
import app.services.UserService;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Inject
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserRegisterServiceModel getByUsername(String username) {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            return null;
        }

        return modelMapper.map(user, UserRegisterServiceModel.class);
    }

    @Override
    public void save(UserRegisterServiceModel model) {

        userRepository.save(modelMapper.map(model, User.class));
    }

    @Override
    public UserRegisterServiceModel getByUsernameAndPassword(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, password);

        if (user == null) {
            return null;
        }

        return modelMapper.map(user, UserRegisterServiceModel.class);
    }

    @Override
    public List<UserServiceModel> getAll() {
        return userRepository.findAll().stream().map(user -> modelMapper.map(user, UserServiceModel.class)).collect(Collectors.toList());
    }

    @Override
    public void addFriend(String username, String friendUsername) {
        User user = userRepository.findByUsername(username);
        User friend = userRepository.findByUsername(friendUsername);

        user.getFriends().add(friend);
        friend.getFriends().add(user);

        userRepository.update(user);
        userRepository.update(friend);
    }
}
