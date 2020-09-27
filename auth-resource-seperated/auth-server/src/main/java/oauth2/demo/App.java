package oauth2.demo;

import java.security.Principal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
	
	//This method will be used to check if the user has a valid token to access the resource
		@RequestMapping("/validateUser")
		public Principal user(Principal user) {
			return user;
		}				
}
