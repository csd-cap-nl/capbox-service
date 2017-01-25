package nl.cap.csd.capbox.users.configuration;

import nl.cap.csd.capbox.users.controllers.UserController;
import nl.cap.csd.capbox.users.service.DataProvider;
import nl.cap.csd.capbox.users.service.impl.StubbedDataProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserServiceConfiguration {

    @Bean
    public DataProvider dataProvider() {
        return new StubbedDataProvider();
    }

    @Bean
    public UserController userController() {
        return new UserController();
    }

}
