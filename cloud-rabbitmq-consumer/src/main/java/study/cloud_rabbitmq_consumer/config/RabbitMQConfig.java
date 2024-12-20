package study.cloud_rabbitmq_consumer.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue testQueue() {
        // 큐를 durable로 설정, 즉 RabbitMQ 서버가 재시작되어도 큐는 사라지지 않음
        return new Queue("testQueue", true, false, false);
    }

    @Bean
    public Queue replyQueue() {
        return new Queue("replyQueue", false);  // 큐 이름은 "replyQueue"
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        // 큐를 선언
        rabbitAdmin.declareQueue(testQueue());
        return rabbitAdmin;
    }
}
