package guru.springframework.sfgjms.config;

import org.apache.activemq.artemis.core.config.impl.ConfigurationImpl;
import org.apache.activemq.artemis.core.server.ActiveMQServer;
import org.apache.activemq.artemis.core.server.ActiveMQServers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * explicitly setting up ActiveMQ server
 * Not needed bc SpringBoot will autoconfigure server if next dependencies presented: artemis-server and artemis-jms-server
 * To enabled this dependencies, please activate the profile "embedded-activemq-server"
 * @author kas
 */
@Profile("embedded-activemq-server")
@Configuration
public class EmbeddedActiveMqServerConfig {

    @Bean
    public ActiveMQServer activeMQServer() throws Exception {
        // setting up embedded ActiveMQ server (from official doc)
        ActiveMQServer server = ActiveMQServers.newActiveMQServer(
                new ConfigurationImpl()
                        .setPersistenceEnabled(false)
                        .setJournalDirectory("target/data/journal")
                        .setSecurityEnabled(false)
                /*.addAcceptorConfiguration("invm", "vm://1")*/);

        server.start();
        return server;
    }

}
