package study.cloud_rabbitmq_consumer.service.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import study.cloud_rabbitmq_consumer.dao.Order;
import study.cloud_rabbitmq_consumer.repository.OrderRepository;

import java.nio.ByteBuffer;

@Service
@RequiredArgsConstructor
public class OrderListener {

    private final OrderRepository orderRepository;
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @RabbitListener(queues = "testQueue")
    public Message handlerOrder(String orderMessage,
                                @Header(AmqpHeaders.REPLY_TO) String replyTo,
                                @Header(AmqpHeaders.CORRELATION_ID) String correlationId) {
        try {
            System.out.println(orderMessage);
            Order order = objectMapper.readValue(orderMessage, Order.class);
            processOrder(order);  // 주문 처리 결과
            byte[] byteArray = ByteBuffer.allocate(4).putInt(Math.toIntExact(order.getId())).array();
            MessageProperties messageProperties = new MessageProperties();
            messageProperties.setCorrelationId(correlationId);  // 고유한 ID 설정
            messageProperties.setReplyTo(replyTo);  // 응답을 보낼 큐 설정
            return new Message(byteArray, messageProperties);  // 결과 반환
        } catch (Exception e) {
            throw new ListenerExecutionFailedException(">>>>>>>>>>>>>>>> 메시지 처리 실패", e);
        }
    }


    private void processOrder(Order order) {
        if(order.getWorkStatus() != null) {
            if (order.getWorkStatus().equals("SAVE")) {
                orderSave(order);
            }
        }
    }

    private void orderSave(Order order) {
        orderRepository.createOrder(order);
    }

    private void orderUpdate(Order order) {
    }

    private void orderDelete(Order order) {
    }
}
