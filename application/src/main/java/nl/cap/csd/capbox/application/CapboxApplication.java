package nl.cap.csd.capbox.application;

import nl.cap.csd.capbox.application.config.CapboxConfiguration;
import nl.cap.csd.capbox.commons.config.DatabaseConfiguration;

import nl.cap.csd.capbox.users.configuration.UserServiceConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({CapboxConfiguration.class, DatabaseConfiguration.class, UserServiceConfiguration.class})
public class CapboxApplication {

    public static void main(String[] args) {
        final ApplicationContext ctx = SpringApplication.run(
                CapboxApplication.class, args);
    }


}
