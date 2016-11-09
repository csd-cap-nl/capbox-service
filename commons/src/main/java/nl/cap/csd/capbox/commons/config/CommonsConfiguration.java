package nl.cap.csd.capbox.commons.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.cap.csd.capbox.commons.controllers.KeepAliveController;
import nl.cap.csd.capbox.commons.helpers.RandomProvider;
import nl.cap.csd.capbox.commons.helpers.impl.SecureRandomProvider;
import nl.cap.csd.capbox.commons.services.version.VersionService;
import nl.cap.csd.capbox.commons.services.version.impl.VersionServiceImpl;
import nl.cap.csd.capbox.commons.transformers.Java8TimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
    public RandomProvider randomProvider() {
        return new SecureRandomProvider();
    }

    @Bean
    public MappingJackson2HttpMessageConverter customJackson2HttpMessageConverter() {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new Java8TimeModule());

        final MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        jsonConverter.setObjectMapper(objectMapper);
        jsonConverter.setJsonPrefix(")]}',\n"); // XSSI protection. This stops the malformed JSON with instructions to be injected in to Javascript code.

        return jsonConverter;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.addDefaultHttpMessageConverters(converters);
        // Replace default Jackson mapping converter with custom converter
        for (Iterator<HttpMessageConverter<?>> i = converters.iterator(); i.hasNext();) {
            final HttpMessageConverter<?> converter = i.next();
            if (converter instanceof MappingJackson2HttpMessageConverter) {
                i.remove();
            }
        }
        converters.add(customJackson2HttpMessageConverter());
    }

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        final Map<String, HandlerInterceptor> interceptors = getApplicationContext().getBeansOfType(HandlerInterceptor.class);
        for (HandlerInterceptor interceptor : interceptors.values()) {
            registry.addInterceptor(interceptor);
        }
        super.addInterceptors(registry);
    }

}
