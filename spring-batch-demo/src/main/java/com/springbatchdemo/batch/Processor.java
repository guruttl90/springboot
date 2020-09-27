package com.springbatchdemo.batch;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.springbatchdemo.model.User;

@Component
public class Processor implements ItemProcessor<User, User>{
	private static final Logger log = LoggerFactory.getLogger(Processor.class);
	private static final Map<String,String> DEPT_NAMES = new HashMap<>();
	
	public Processor() {
		DEPT_NAMES.put("001", "Technology");
		DEPT_NAMES.put("002", "Operation");
		DEPT_NAMES.put("003", "Accounts");
	}
	@Override
	public User process(User user) throws Exception {
		String deptCode = user.getDept();
		String deptName = DEPT_NAMES.get(deptCode);
		log.info("deptName = {}, deptCode = {} ",deptName,deptCode);
		user.setDept(deptName);
		user.setTime(new Date());
		return user;
	}

}
