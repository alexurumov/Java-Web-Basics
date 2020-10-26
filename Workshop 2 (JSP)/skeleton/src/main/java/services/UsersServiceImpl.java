package services;

import models.entity.User;
import models.service.UserCreateServiceModel;
import models.service.UserServiceModel;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;
import repositories.UserRepository;
import services.base.UsersService;
import services.base.UsersValidationService;
import util.ValidationUtil;

import javax.inject.Inject;

public class UsersServiceImpl implements UsersService {

    private final ModelMapper modelMapper;
    private final UsersValidationService usersValidationService;
    private final UserRepository userRepository;
    private final ValidationUtil validationUtil;

    @Inject
    public UsersServiceImpl(ModelMapper modelMapper, UsersValidationService usersValidationService, UserRepository userRepository, ValidationUtil validationUtil) {
        this.modelMapper = modelMapper;
        this.usersValidationService = usersValidationService;
        this.userRepository = userRepository;
        this.validationUtil = validationUtil;
    }

    @Override
    public void register(String username, String password, String confirmPassword, String email) throws Exception {

        //Validation of input
        if (username.trim().isEmpty() || password.trim().isEmpty() || email.trim().isEmpty()) {
            throw new Exception("User cannot be created");
        }

        if (!usersValidationService.canCreateUser(username, password, confirmPassword, email)) {
            throw new Exception("User cannot be created");
        }

        UserCreateServiceModel model = new UserCreateServiceModel();
        model.setUsername(username);
        String passwordHex = DigestUtils.sha256Hex(password);
        model.setPassword(passwordHex);
        model.setEmail(email);

        User user = modelMapper.map(model, User.class);
        if (!validationUtil.isValid(user)) {
            throw new Exception("User cannot be created");
        }
        userRepository.createUser(user);
    }

    @Override
    public UserServiceModel login(String username, String password) {
        User user = userRepository.getUserByUsername(username);

        if (user == null) {
            return null;
        }

        if (!user.getPassword().equals(DigestUtils.sha256Hex(password))) {
            return null;
        }

        return modelMapper.map(user, UserServiceModel.class);
    }
}
