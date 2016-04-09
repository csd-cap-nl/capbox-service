package nl.cap.csd.capbox.commons.config;

import nl.cap.csd.capbox.commons.nl.cap.csd.capbox.controllers.KeepAliveController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonsConfiguration {

    @Bean
    public KeepAliveController keepAliveController() {
        return new KeepAliveController();
    }

}
