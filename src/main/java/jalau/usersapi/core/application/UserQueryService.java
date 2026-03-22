package jalau.usersapi.core.application;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import jalau.usersapi.core.domain.entities.User;
import jalau.usersapi.core.domain.repositories.IUserRepository;
import jalau.usersapi.core.domain.services.IUserQueryService;
import jalau.usersapi.core.exception.NotImplementedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Implementation of IUserQueryService.
 * Handles asynchronous retrieval of users from the repository.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserQueryService implements IUserQueryService {
    
    /** Repository interface for user data access */
    private final IUserRepository userRepository;
    
    /**
     * Retrieves all users asynchronously.
     *
     * @return a CompletableFuture containing a list of users
     */
    @Override
    public CompletableFuture<List<User>> getUsers() {
        return CompletableFuture.supplyAsync(() -> {
            log.info("Reading users");
            return userRepository.getUsers();
        });
    }
    
    /**
     * TODO: Retrieves a single user by ID.
     *
     * @param id the user ID
     * @return the user
     * @throws NotImplementedException because this method is not implemented yet
     */
    @Override
    public User getUser(String id) {
        throw new NotImplementedException("getUser() is not implemented");
    }
}
