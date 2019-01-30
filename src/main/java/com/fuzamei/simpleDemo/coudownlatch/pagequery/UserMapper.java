package com.fuzamei.simpleDemo.coudownlatch.pagequery;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface UserMapper {
	
	List<User> findUsersByPage(@Param("start") Integer start,
							   @Param("rows") Integer rows);
	
	int findUsersCount();
}
