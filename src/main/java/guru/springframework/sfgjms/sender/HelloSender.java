package guru.springframework.sfgjms.sender;

import guru.springframework.sfgjms.config.JmsConfig;
import guru.springframework.sfgjms.model.HelloWorldMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author kas
 */
@Component
@RequiredArgsConstructor
@Log4j2
public class HelloSender {

    private final JmsTemplate jmsTemplate;

    @Scheduled(fixedRate = 2000)
    public void sendMessage() {
        log.debug("sending message method");
        HelloWorldMessage message = HelloWorldMessage.builder().message("Hello world!").id(UUID.randomUUID()).build();
        jmsTemplate.convertAndSend(JmsConfig.MY_QUEUE, message);

    }

}
