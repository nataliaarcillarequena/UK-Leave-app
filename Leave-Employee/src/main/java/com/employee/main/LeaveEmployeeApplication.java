package com.employee.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages = "com.employee")
@EntityScan(basePackages = "com.employee.entity")
@EnableJpaRepositories(basePackages = "com.employee.persistence")
public class LeaveEmployeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeaveEmployeeApplication.class, args);
	}
	
	/*bean for the rest template resource (to test insert/delete/etc functionality)
	 * found inside of the resource package 
	 */
	@Bean
	public RestTemplate gettemplate() {
		return new RestTemplate(); //from the spring web dependency 
	}

}
