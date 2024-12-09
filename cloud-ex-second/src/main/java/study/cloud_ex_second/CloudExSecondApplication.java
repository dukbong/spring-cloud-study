package study.cloud_ex_second;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class CloudExSecondApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudExSecondApplication.class, args);
	}

}
