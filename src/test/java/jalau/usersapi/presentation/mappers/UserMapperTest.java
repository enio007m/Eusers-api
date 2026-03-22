package jalau.usersapi.presentation.mappers;

import jalau.usersapi.core.domain.entities.User;
import jalau.usersapi.presentation.dtos.UserResponseDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for UserMapper.
 * Verifies mapping from domain entity to response DTO.
 */
class UserMapperTest {
	
	/** Mapper under test */
	private final UserMapper mapper = new UserMapper();
	
	/**
	 * Should correctly map User to UserResponseDto.
	 */
	@Test
	void shouldMapDomainToResponseDto() {
		User user = new User("1", "Javier", "jroca", "123");
		
		UserResponseDto dto = mapper.toResponseDtoEntity(user);
		
		assertEquals(user.getName(), dto.getName());
	}
}
