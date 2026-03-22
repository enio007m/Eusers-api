package jalau.usersapi.infrastructure.mysql.repositories;

import jalau.usersapi.core.domain.entities.User;
import jalau.usersapi.core.domain.repositories.IUserRepository;
import jalau.usersapi.core.exception.NotImplementedException;
import jalau.usersapi.infrastructure.mysql.entities.UserJpaEntity;
import jalau.usersapi.infrastructure.mysql.mappers.UserPersistenceMapper;
import jalau.usersapi.infrastructure.mysql.mybatis.UserMyBatisMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository implementation for accessing users in the MySQL database.
 */
@Repository
@RequiredArgsConstructor
public class UserRepository implements IUserRepository {
	
	private final UserMyBatisMapper myBatisMapper;
	private final UserPersistenceMapper mapper;
	
	/**
	 * Retrieves all users from the database.
	 *
	 * @return a list of domain User entities
	 */
	@Override
	public List<User> getUsers() {
		List<UserJpaEntity> entities = myBatisMapper.findAllUsers();
		return entities.stream().map(mapper::toDomainEntity).toList();
	}
	
	@Override
	public User getUser(String id) {
		throw new NotImplementedException("getUser() is not implemented yet");
	}
	
	@Override
	public User createUser(User user) {
		throw new NotImplementedException("createUser() is not implemented yet");
	}
	
	@Override
	public User updateUser(User user) {
		throw new NotImplementedException("updateUser() is not implemented yet");
	}
	
	@Override
	public void deleteUser(String id) {
		throw new NotImplementedException("deleteUser() is not implemented yet");
	}
}