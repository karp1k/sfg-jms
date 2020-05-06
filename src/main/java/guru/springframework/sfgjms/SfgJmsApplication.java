package guru.springframework.sfgjms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/** don't forget to bring up local ActiveMQ server
 * or activate spring profile "embedded-activemq-server" for embedded server
 * https://github.com/vromero/activemq-artemis-docker
 * @author kas
 */
@SpringBootApplication
public class SfgJmsApplication {

    public static void main(String[] args) throws Exception {

        SpringApplication.run(SfgJmsApplication.class, args);
    }

}
