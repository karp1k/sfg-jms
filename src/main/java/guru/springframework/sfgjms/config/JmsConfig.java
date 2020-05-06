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

    @Bean
    public MessageConverter messageConverter() {
        // will convert POJO to JSON string
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        // for spring processing
        converter.setTypeIdPropertyName("_type");
        return converter;
    }
}
