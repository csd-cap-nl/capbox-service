package nl.cap.csd.capbox.commons.controllers;

import nl.cap.csd.capbox.commons.services.version.VersionService;
import nl.cap.csd.capbox.commons.services.version.VersionInformation;
import nl.cap.csd.capbox.commons.services.version.VersionedBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * This controller provides a number of book-keeping services to the client. This includes a keepalive and version
 * endpoints.
 */
@RestController
public class KeepAliveController implements VersionedBean {

    @Value("${application.version}")
    private String applicationVersion;

    @Value("${application.name}")
    private String applicationName;

    private final VersionService versionService;

    @Autowired
    public KeepAliveController(final VersionService versionService) {
        this.versionService = versionService;
    }

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
        return versionService.getAllVersions();
    }

    @Override
    public VersionInformation version() {
        return new VersionInformation(applicationName, applicationVersion);
    }


}
