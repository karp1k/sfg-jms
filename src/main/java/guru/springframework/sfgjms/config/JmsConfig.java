package guru.springframework.sfgjms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

/**
 * @author kas
 */
@Configuration
public class JmsConfig {

    public static final String MY_QUEUE = "hellow-world-queue";
    public static final String SEND_AND_RECIVE_QUEUE = "reply-back-to-me";

    @Bean
    public MessageConverter messageConverter() {
        // will convert POJO to JSON string
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        // for spring processing. Spring sets value of property equal to specific java class. Check @Payload in HelloListener
        // for @Payload HelloWorldMessage would be: _type = guru.springframework.sfgjms.model.HelloWorldMessage
        converter.setTypeIdPropertyName("_type");
        return converter;
    }
}
