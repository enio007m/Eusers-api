package jalau.usersapi.core.application;

import jalau.usersapi.core.domain.entities.User;
import jalau.usersapi.core.domain.repositories.IUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Unit tests for UserQueryService.
 * Verifies service behavior using a mocked IUserRepository.
 */
@ExtendWith(MockitoExtension.class)
class UserQueryServiceTest {
	
	/** Mocked repository dependency */
	@Mock
	private IUserRepository userRepository;
	
	/** Service under test */
	@InjectMocks
	private UserQueryService userQueryService;
	
	/**
	 * Should return a list of users from the repository.
	 */
	@Test
	void shouldReturnUsers() throws Exception {
		List<User> users = List.of(new User("1", "Javier", "jroca", "123"));
		when(userRepository.getUsers()).thenReturn(users);
		
		CompletableFuture<List<User>> result = userQueryService.getUsers();
		assertEquals(1, result.get().size());
	}
}
