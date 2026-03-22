package jalau.usersapi.infrastructure.mysql.mappers;

import jalau.usersapi.core.domain.entities.User;
import jalau.usersapi.infrastructure.mysql.entities.UserJpaEntity;
import org.springframework.stereotype.Component;

/**
 * Mapper class to convert database UserJpaEntity objects to domain User entities.
 */
@Component
public class UserPersistenceMapper {
	
	/**
	 * Maps a UserJpaEntity to a domain User entity.
	 *
	 * @param entity the database entity
	 * @return the domain entity, or null if input is null
	 */
	public User toDomainEntity(UserJpaEntity entity) {
		if (entity == null) return null;
		
		User user = new User();
		user.setId(entity.getId());
		user.setName(entity.getName());
		user.setLogin(entity.getLogin());
		user.setPassword(entity.getPassword());
		
		return user;
	}
}
