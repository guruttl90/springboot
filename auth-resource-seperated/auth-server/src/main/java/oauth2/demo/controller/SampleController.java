package oauth2.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//lets make the Resource present inside the auth server 
@RestController
public class SampleController {
	
	
	@RequestMapping(value = "/test")
	public Sample getData() {
		Sample response = new Sample("XYZ","ACTIVE");
		return response;
	}
	
	@RequestMapping(value = "/test1")
	public Sample getData1() {
		Sample response = new Sample("XYZ","ACTIVE");
		return response;
	}
	
	@RequestMapping(value = "/test2")
	public Sample getData2() {
		Sample response = new Sample("XYZ","ACTIVE");
		return response;
	}
	
}

class Sample{
	
	private String name;
	private String status;
	
	
	public Sample(String name, String status) {
		super();
		this.name = name;
		this.status = status;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}