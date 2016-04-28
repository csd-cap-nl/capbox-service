package nl.cap.csd.capbox.commons.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.cap.csd.capbox.commons.controllers.KeepAliveController;
import nl.cap.csd.capbox.commons.services.version.VersionService;
import nl.cap.csd.capbox.commons.services.version.impl.VersionServiceImpl;
import nl.cap.csd.capbox.commons.transformers.Java8TimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

@Configuration
@PropertySource("classpath:/application.properties")
public class CommonsConfiguration extends WebMvcConfigurationSupport {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigIn() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public VersionService versionService() {
        return new VersionServiceImpl();
    }

    @Bean
    public KeepAliveController keepAliveController(final VersionService versionService) {
        return new KeepAliveController(versionService);
    }

    @Bean
    public MappingJackson2HttpMessageConverter customJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new Java8TimeModule());
        jsonConverter.setObjectMapper(objectMapper);
        jsonConverter.setJsonPrefix(")]}',\n");
        return jsonConverter;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(customJackson2HttpMessageConverter());
        super.addDefaultHttpMessageConverters(converters);
    }

}
