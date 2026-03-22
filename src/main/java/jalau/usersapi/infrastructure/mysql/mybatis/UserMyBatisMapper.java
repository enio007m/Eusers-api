package jalau.usersapi.infrastructure.mysql.mybatis;

import jalau.usersapi.infrastructure.mysql.entities.UserJpaEntity;

import java.util.List;

/**
 * MyBatis mapper interface for executing SQL queries on the users table.
 */
public interface UserMyBatisMapper {
	/**
	 * Retrieves all users from the database.
	 *
	 * @return a list of UserJpaEntity objects
	 */
	List<UserJpaEntity> findAllUsers();
}
