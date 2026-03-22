package jalau.usersapi.core.domain.services;

import jalau.usersapi.core.domain.entities.User;

public interface IUserCommandService {
    User createUser(User user);
    User updateUser(User user);
    void deleteUser(String id);
}
