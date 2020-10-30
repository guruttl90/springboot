package com.demo.mybatisusingspringboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.mybatisusingspringboot.business.SampleBusiness;
import com.demo.mybatisusingspringboot.model.UserVo;

@RestController
@RequestMapping(path = "/demo")
public class SampleController {

	@Autowired SampleBusiness sampleBusiness;
	
	@GetMapping(path = "/get-all")
	public List<UserVo> getUserList(){
		return sampleBusiness.getUserList();
	}
	
	@GetMapping(path = "/get-user-by-id/{id}")
	public UserVo getUserList(@PathVariable(value = "id") int id){
		return sampleBusiness.getUser(id);
	}
}
