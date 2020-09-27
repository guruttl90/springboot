package com.demo.mybatisusingspringboot.business;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.mybatisusingspringboot.dao.UserMapper;
import com.demo.mybatisusingspringboot.model.UserVo;

@Component
public class SampleBusiness {
	
	@Autowired UserMapper userMapper;
	
	@Autowired SqlSession sqlSession;
	
	public List<UserVo> getUserList(){
		return userMapper.getUserList();
	}
	
	
	public UserVo getUser(int id) {
		List<UserVo> usersList = sqlSession.selectList("getUserList");
		UserVo response = null;
		for(UserVo user: usersList) {
			if(user.getId() == id) {
				response = user;
			}
		}
		return response;
	}		
}
