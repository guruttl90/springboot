package com.springbatchdemo.batch;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springbatchdemo.model.User;
import com.springbatchdemo.repository.UserRepository;

@Component
public class DBWriter implements ItemWriter<User>{
	Logger log = LoggerFactory.getLogger(DBWriter.class);
	@Autowired private UserRepository userRepository;
	
	@Override
	public void write(List<? extends User> users) throws Exception {		
		log.info("inside write DBWriter - Data saved in DB"+users);
		userRepository.saveAll(users);
	}

}
