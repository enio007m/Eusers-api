package jalau.usersapi.core.application;

import jalau.usersapi.core.domain.entities.User;
import jalau.usersapi.core.domain.repositories.IUserRepository;
import jalau.usersapi.core.domain.services.IUserCommandService;
import jalau.usersapi.core.exception.UserNotFoundException;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;

/**
 * Application service responsible for handling user write operations.
 * <p>
 * This class orchestrates the creation of users by delegating persistence
 * to the {@link IUserRepository}. The operation is executed asynchronously
 * to improve responsiveness at the API layer.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserCommandService implements IUserCommandService {
    
    private final IUserRepository userRepository;
    
    /**
     * Creates a new user asynchronously.
     *
     * @param user the user to be created
     * @return a {@link CompletableFuture} containing the created user
     */
    @Override
    public CompletableFuture<User> createUser(User user) {
        return CompletableFuture.supplyAsync(() -> {
            log.info("Creating user");
            return userRepository.createUser(user);
        });
    }

    @Override
    public User updateUser(User user) {
        User existingUser = userRepository.getUser(user.getId());
        if (existingUser == null) {
            return null; // Return null if not found to decouple from HTTP Codes
        }
        
        existingUser.setName(user.getName());
        existingUser.setLogin(user.getLogin());
        existingUser.setPassword(user.getPassword());

        return userRepository.updateUser(existingUser);
    }

    @Override
    public CompletableFuture<Void> deleteUser(String id) {
        return CompletableFuture.runAsync(() -> {
            log.info("Attempting to delete user with ID: {}", id);

            User existingUser = userRepository.getUser(id);
            if (existingUser == null) {
                log.error("User not found with ID: {}", id);
                throw new UserNotFoundException("User not found with ID: " + id);
            }

            userRepository.deleteUser(id);
            log.info("User successfully deleted.");
        });
    }
}
