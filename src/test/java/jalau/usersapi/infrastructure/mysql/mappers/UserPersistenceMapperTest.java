package jalau.usersapi.infrastructure.mysql.mappers;

import jalau.usersapi.core.domain.entities.User;
import jalau.usersapi.infrastructure.mysql.entities.UserJpaEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for UserPersistenceMapper.
 * Verifies mapping from UserJpaEntity to User domain entity.
 */
@ExtendWith(MockitoExtension.class)
public class UserPersistenceMapperTest {
	
	/** Mapper under test */
	@InjectMocks
	private UserPersistenceMapper mapper;
	
	/**
	 * Should correctly map JPA entity to domain entity.
	 */
	@Test
	void shouldMapJpaToDomain() {
		UserJpaEntity entity = new UserJpaEntity();
		entity.setName("Javier");
		
		User user = mapper.toDomainEntity(entity);
		assertEquals("Javier", user.getName());
	}
}
