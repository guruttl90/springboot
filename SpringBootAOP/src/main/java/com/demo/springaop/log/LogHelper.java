package com.demo.springaop.log;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogHelper {

	private static final Logger log = LoggerFactory.getLogger(LogHelper.class);
	@Before("execution(public String get())")
	public void Logger() {
		log.info("inside the logHelper");		
	}
}


/*								ASPECT ORIENTED PROGRAMMING
 * 								===========================
 *	Cross Cutting Concerns  -> kept inside the aspect. i.e we are going to use the same logic or methods 
 * 	ADVICE  -> When we calling some method along with that method, we need to execute the common logics implement inside the Aspect automatically
 * 				Also the advice says when to call the common logic automatically for this , 
 * 						- before (Run advice before the method execution.)
 * 						- after (Run advice after the method execution, regardless of its outcome.)
 * 						- after-returning (Run advice after the method execution, only if the method completes successfully.)
 * 						- after-throwing (Run advice after the method execution, only if the method exits by throwing an exception.)
 * 						- around - (Run advice before and after the advised method is invoked.)
 * 
 * 			POINTCUT -> Also this advice is going to inform, which particular method are going to call the common logics that we need to mention in "execution" 
 * 					execution value mentioned in expression 
 * 					expression to cover a group of methods
 * 				
 * 
 * Refer:
 * 	https://docs.spring.io/spring/docs/3.0.0.M3/reference/html/ch08.html
 * 
 * */

