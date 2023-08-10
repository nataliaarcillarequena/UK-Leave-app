package com.user.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages = "com.user")
public class LeaveMvc1Application {

	public static void main(String[] args) {
		SpringApplication.run(LeaveMvc1Application.class, args);
	}
	
	/*rest template to be able to interact with the leave requests
	 * and employee microservices*/
	@Bean
	public RestTemplate gettemplate() {
		return new RestTemplate(); //from the spring web dependency 
	}

}
