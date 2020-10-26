package services.base;

public interface UsersValidationService {
    boolean canCreateUser(String username, String password, String confirmPassword, String email);
}
