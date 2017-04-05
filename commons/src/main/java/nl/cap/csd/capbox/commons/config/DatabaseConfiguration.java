package nl.cap.csd.capbox.commons.config;

import java.sql.SQLException;
import java.util.Properties;
import javax.sql.DataSource;
import liquibase.integration.spring.SpringLiquibase;
import nl.cap.csd.capbox.commons.model.users.User;
import org.h2.tools.Server;
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

    // This bean starts a second webserver in the application listening to port 8082.
    // This webserver allows direct connection to the h2 database console on the root path: localhost:8082/
    @Bean
    public Server h2WebServer() throws SQLException {
        return Server.createWebServer("-web","-webAllowOthers","-webPort","8082").start();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        final DataSource dataSource = jdbcTemplate.getDataSource();
        em.setDataSource(dataSource);
        em.setPackagesToScan(
                User.class.getPackage().getName()
        );

        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        Properties properties = (Properties) environment.getPropertySources().get("persistence").getSource();
        em.setJpaProperties(properties);

        return em;
    }

}
