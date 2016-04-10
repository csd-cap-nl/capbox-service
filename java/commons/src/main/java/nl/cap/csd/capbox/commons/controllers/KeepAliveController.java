package nl.cap.csd.capbox.commons.controllers;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This controller provides a number of book-keeping services to the client. This includes a keepalive and version
 * endpoints.
 */
@RestController
public class KeepAliveController implements ApplicationContextAware, VersionedBean {

    @Value("${application.version}")
    private String applicationVersion;

    @Value("${application.name}")
    private String applicationName;

    private ApplicationContext context;

    @RequestMapping(path = "/keepalive",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String keepalive() {
        return LocalDateTime.now()
                .format(DateTimeFormatter.ISO_DATE_TIME);
    }

    @RequestMapping(path = "/version",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<VersionInformation> getVersions() {
        final Map<String, VersionedBean> versionedBeans = context.getBeansOfType(
                VersionedBean.class);
        final List<VersionInformation> versions = new ArrayList<>();
        for (VersionedBean controller : versionedBeans.values()) {
            versions.add(controller.version());
        }
        return versions;
    }

    @Override
    public VersionInformation version() {
        return new VersionInformation(applicationName, applicationVersion);
    }


    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

}
