package jalau.usersapi.infrastructure.mysql.repositories;

import jalau.usersapi.core.domain.entities.User;
import jalau.usersapi.infrastructure.mysql.entities.UserJpaEntity;
import jalau.usersapi.infrastructure.mysql.mappers.UserPersistenceMapper;
import jalau.usersapi.infrastructure.mysql.mybatis.UserMyBatisMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * Unit tests for UserRepository.
 * Verifies interaction with MyBatis mapper and entity mapping.
 */
@ExtendWith(MockitoExtension.class)
class UserRepositoryTest {

	/** Mocked MyBatis mapper */
	@Mock
	private UserMyBatisMapper myBatisMapper;

	/** Real mapper instance */
	private UserPersistenceMapper mapper;

	/** Repository under test */
	private UserRepository userRepository;

	/**
	 * Initializes test dependencies before each test.
	 */
	@BeforeEach
	void setUp() {
		mapper = new UserPersistenceMapper();
		userRepository = new UserRepository(myBatisMapper, mapper);
	}

	/**
	 * Should return mapped users from database results.
	 */
	@Test
	void shouldReturnUsersFromDatabase() {
		UserJpaEntity entity = new UserJpaEntity();
		entity.setId("1");
		entity.setName("Javier");
		entity.setLogin("jroca");
		entity.setPassword("123");

		List<UserJpaEntity> entities = List.of(entity);
		when(myBatisMapper.findAllUsers()).thenReturn(entities);

		List<User> result = userRepository.getUsers();

		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals("Javier", result.get(0).getName());
	}

	@Test
	void shouldReturnUserByIdFromDatabase() {
		UserJpaEntity entity = new UserJpaEntity();
		entity.setId("1");
		entity.setName("Javier");
		entity.setLogin("jroca");
		entity.setPassword("123");

		when(myBatisMapper.getUserById("1")).thenReturn(entity);

		User result = userRepository.getUser("1");

		assertNotNull(result);
		assertEquals("Javier", result.getName());
	}

	@Test
	void shouldReturnNullWhenUserNotFoundInDatabase() {
		when(myBatisMapper.getUserById("2")).thenReturn(null);

		User result = userRepository.getUser("2");

		org.junit.jupiter.api.Assertions.assertNull(result);
	}
}
