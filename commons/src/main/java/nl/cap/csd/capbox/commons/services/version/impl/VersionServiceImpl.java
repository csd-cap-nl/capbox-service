package nl.cap.csd.capbox.commons.services.version.impl;

import nl.cap.csd.capbox.commons.services.version.VersionInformation;
import nl.cap.csd.capbox.commons.services.version.VersionService;
import nl.cap.csd.capbox.commons.services.version.VersionedBean;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class VersionServiceImpl implements ApplicationContextAware, VersionService {

    private ApplicationContext context;

    @Override
    public VersionInformation getVersionForBean(final Class<?> beanClass) {
        try {
            return getVersionFromBean(context.getBean(beanClass));
        } catch (BeansException e) {
            return new VersionInformation("Unknown", "Error");
        }
    }

    @Override
    public VersionInformation getVersionForBean(final String beanName) {
        try {
            return getVersionFromBean(context.getBean(beanName));
        } catch (BeansException e) {
            return new VersionInformation("Unknown", "Error");
        }
    }

    @Override
    public List<VersionInformation> getAllVersions() {
        final Map<String, VersionedBean> versionedBeans = context.getBeansOfType(
                VersionedBean.class);
        final List<VersionInformation> versions = new ArrayList<>();
        for (VersionedBean controller : versionedBeans.values()) {
            versions.add(controller.version());
        }
        return Collections.unmodifiableList(versions);
    }

    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    private VersionInformation getVersionFromBean(final Object bean) {
        if (bean instanceof VersionedBean) {
            return ((VersionedBean) bean).version();
        }
        return new VersionInformation(bean.getClass()
                .getSimpleName(), "Unknown");
    }

}
