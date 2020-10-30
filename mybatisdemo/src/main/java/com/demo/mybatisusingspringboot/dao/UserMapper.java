package com.demo.mybatisusingspringboot.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.demo.mybatisusingspringboot.model.UserVo;

@Mapper
public interface UserMapper {

	@Select("select * from User")
	List<UserVo> getUserList();
}
