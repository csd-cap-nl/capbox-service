package nl.cap.csd.capbox.commons.services.version;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This service provides information about the versions of various beans. This can requested per bean
 * or for all beans implementing the VersionedBean interface.
 */
@Service
public interface VersionService {

    /**
     * Get the information for a single bean, based on its class.
     *
     * @param beanClass class of which the version is requested
     * @return
     *  - If bean implements VersionedBean: result from {@link VersionedBean#version}<br>
     *  - If bean does not implement VersionedBean: VersionInformation with the classname and "Unknown"<br>
     *  - If bean cannot be retrieved: VersionInformation with the "Unknown" and "Error"<br>
     *
     *  @see org.springframework.context.ApplicationContext#getBean
     */
    VersionInformation getVersionForBean(Class<?> beanClass);

    /**
     * Get the information for a single bean, based on its name.
     *
     * @param beanName class of which the version is requested
     * @return
     *  - If bean implements VersionedBean: result from {@link VersionedBean#version}<br>
     *  - If bean does not implement VersionedBean: VersionInformation with the classname and "Unknown"<br>
     *  - If bean cannot be retrieved: VersionInformation with the "Unknown" and "Error"<br>
     *
     *  @see org.springframework.context.ApplicationContext#getBean
     */
    VersionInformation getVersionForBean(String beanName);

    /**
     * Retrieve a list of the versions of all the VersionedBean instances in the default application context
     *
     * @return List of a versioned beans
     *
     * @see VersionedBean#version
     */
    List<VersionInformation> getAllVersions();

}
