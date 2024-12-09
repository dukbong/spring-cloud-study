package study.cloud_gate_way;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
public class CloudGateWayApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudGateWayApplication.class, args);
	}

}
