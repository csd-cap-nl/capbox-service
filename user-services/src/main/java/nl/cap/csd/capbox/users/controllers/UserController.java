package nl.cap.csd.capbox.users.controllers;

import nl.cap.csd.capbox.commons.services.version.VersionInformation;
import nl.cap.csd.capbox.commons.services.version.VersionedBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements VersionedBean {

    @Value("${application.version}")
    private String applicationVersion;

    @Value("${application.name}")
    private String applicationName;

    @Override
    public VersionInformation version() {
        return new VersionInformation(applicationName, applicationVersion);
    }

}
