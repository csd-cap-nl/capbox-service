package nl.cap.csd.capbox.commons.config;

import java.util.Properties;
import javax.sql.DataSource;
import liquibase.integration.spring.SpringLiquibase;
import nl.cap.csd.capbox.commons.model.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
@PropertySource(name = "persistence", value = "classpath:/database.properties")
public class DatabaseConfiguration {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ConfigurableEnvironment environment;

    @Autowired
    private ApplicationContext context;

    @Bean
    public SpringLiquibase liquibase() {
        final SpringLiquibase springLiquibase = new SpringLiquibase();
        springLiquibase.setDataSource(jdbcTemplate.getDataSource());
        springLiquibase.setChangeLog(environment.getProperty("liquibase.changelog"));
        return springLiquibase;
    }

    @Bean
    public EntityPackageName userPackage() {
        return new EntityPackageName(User.class);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        final DataSource dataSource = jdbcTemplate.getDataSource();
        em.setDataSource(dataSource);
        em.setPackagesToScan(getPackagesToScan());

        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        Properties properties = (Properties) environment.getPropertySources().get("persistence").getSource();
        em.setJpaProperties(properties);

        return em;
    }

    private String[] getPackagesToScan() {
        return context.getBeansOfType(EntityPackageName.class)
                .values()
                .stream()
                .map(EntityPackageName::getName)
                .distinct()
                .toArray(String[]::new);
    }

}
