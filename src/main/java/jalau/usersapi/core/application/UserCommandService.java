package jalau.usersapi.core.application;

import jalau.usersapi.core.domain.entities.User;
import jalau.usersapi.core.domain.services.IUserCommandService;
import jalau.usersapi.core.exception.NotImplementedException;

public class UserCommandService implements IUserCommandService {
    @Override
    public User createUser(User user) {
        throw new NotImplementedException("createUser() is not implemented");
    }

    @Override
    public User updateUser(User user) {
        throw new NotImplementedException("updateUser() is not implemented");
    }

    @Override
    public void deleteUser(String id) {
        throw new NotImplementedException("deleteUser() is not implemented");
    }
}
