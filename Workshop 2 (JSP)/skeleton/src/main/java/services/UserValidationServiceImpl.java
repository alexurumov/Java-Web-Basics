package services;

import models.entity.User;
import repositories.UserRepository;
import services.base.UsersValidationService;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidationServiceImpl implements UsersValidationService {

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private final EntityManager entityManager;
    private final UserRepository userRepository;

    @Inject
    public UserValidationServiceImpl(EntityManager entityManager, UserRepository userRepository) {
        this.entityManager = entityManager;
        this.userRepository = userRepository;
    }

    @Override
    public boolean canCreateUser(String username, String password, String confirmPassword, String email) {
        return arePasswordsMatching(password, confirmPassword) && isEmailValid(email) && isUsernameAvailable(username);
    }

    private boolean isEmailValid(String email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(email);
        return matcher.find();
    }

    private boolean arePasswordsMatching(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

    private boolean isUsernameAvailable(String username) {
        User user = userRepository.getUserByUsername(username);

        return user == null;
    }
}
