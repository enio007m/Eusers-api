package jalau.usersapi.infrastructure.mysql.mybatis;

import jalau.usersapi.infrastructure.mysql.entities.UserJpaEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMyBatisMapper {
	List<UserJpaEntity> findAllUsers();
	UserJpaEntity getUserById(@Param("id") String id);
}
