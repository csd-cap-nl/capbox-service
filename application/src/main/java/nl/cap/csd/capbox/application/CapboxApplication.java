package nl.cap.csd.capbox.application;

import nl.cap.csd.capbox.application.config.CapboxConfiguration;
import nl.cap.csd.capbox.commons.config.DatabaseConfiguration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;

//localhost:8080/version
//localhost:8080/keepalive

@SpringBootApplication
@Import({CapboxConfiguration.class, DatabaseConfiguration.class})
foobar
public class CapboxApplication {

    public static void main(String[] args) {
        final ApplicationContext ctx = SpringApplication.run(
                CapboxApplication.class, args);
    }


}
