package guru.springframework.sfgjms.listener;

import guru.springframework.sfgjms.config.JmsConfig;
import guru.springframework.sfgjms.model.HelloWorldMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import java.util.UUID;

/**
 * @author kas
 */
@Component
@RequiredArgsConstructor
public class HelloListener {

    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsConfig.MY_QUEUE)
    public void listen(@Payload HelloWorldMessage helloWorldMessage, @Headers MessageHeaders headers, Message message) {
        System.out.println("receive message");
        System.out.println(helloWorldMessage);
    }

    @JmsListener(destination = JmsConfig.SEND_AND_RECIVE_QUEUE)
    public void listenForSendAndReceived(@Payload HelloWorldMessage helloWorldMessage, @Headers MessageHeaders headers, Message message) throws JMSException {
        System.out.println("receive message from SendAndReceived method");
        HelloWorldMessage replyMsg = HelloWorldMessage.builder().message("Hello reply!").id(UUID.randomUUID()).build();
        jmsTemplate.convertAndSend(message.getJMSReplyTo(), replyMsg);
        System.out.println(helloWorldMessage);
    }
}
