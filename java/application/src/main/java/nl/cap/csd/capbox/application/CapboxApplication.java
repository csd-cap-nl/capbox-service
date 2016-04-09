package nl.cap.csd.capbox.application;

import nl.cap.csd.capbox.application.config.CapboxConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(CapboxConfiguration.class)
public class CapboxApplication {

    public static void main(String[] args) {
        final ApplicationContext ctx = SpringApplication.run(
                CapboxApplication.class, args);
    }

}
