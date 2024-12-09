package study.cloud_user_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

// server that can be registered with the Eureka Server
@EnableDiscoveryClient
@SpringBootApplication
public class CloudUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudUserServiceApplication.class, args);
	}

}
