package guru.springframework.sfgjms.sender;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.sfgjms.config.JmsConfig;
import guru.springframework.sfgjms.model.HelloWorldMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import java.util.UUID;

/**
 * @author kas
 */
@Component
@RequiredArgsConstructor
public class HelloSender {

    private final JmsTemplate jmsTemplate;
    private final ObjectMapper mapper;

    @Scheduled(fixedRate = 10000)
    public void sendMessage() {
        HelloWorldMessage message = HelloWorldMessage.builder().message("Hello world!").id(UUID.randomUUID()).build();
        jmsTemplate.convertAndSend(JmsConfig.MY_QUEUE, message);

    }

    @Scheduled(fixedRate = 5000)
    public void sendAndReceiveMessage() throws JMSException {
        System.out.println("Sending from SendAndReceiveMessage method");
        HelloWorldMessage message = HelloWorldMessage.builder().message("Hello world! from SendAndReceived").id(UUID.randomUUID()).build();
        Message receivedMsg = jmsTemplate.sendAndReceive(JmsConfig.SEND_AND_RECIVE_QUEUE, session -> {
            Message helloMessage = session.createTextMessage();
            String textPayload = null;
            try {
                textPayload = mapper.writeValueAsString(message);
            } catch (JsonProcessingException e) {
                throw new JMSException(e.getMessage());
            }
            ((TextMessage) helloMessage).setText(textPayload);
            helloMessage.setStringProperty("_type", "guru.springframework.sfgjms.model.HelloWorldMessage");
            return helloMessage;
        });
        System.out.println(receivedMsg.getBody(String.class));

    }

}
