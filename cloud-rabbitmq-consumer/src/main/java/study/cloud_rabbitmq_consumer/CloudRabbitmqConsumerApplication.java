package study.cloud_rabbitmq_consumer;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class CloudRabbitmqConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudRabbitmqConsumerApplication.class, args);
	}

}
