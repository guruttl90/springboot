package com.spring.security.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.spring.security.model.UserInfo;

@Mapper
public interface UserDetailsRepository {
	@Select("select * from User")
	public List<UserInfo> getAllUser();
	
	@Select("select * from user where username=#{userName};")
	public UserInfo getUserInfoByUserName(String userName);
}
