package nl.cap.csd.capbox.application.config;

import nl.cap.csd.capbox.commons.config.CommonsConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(CommonsConfiguration.class)
public class CapboxConfiguration {

}
