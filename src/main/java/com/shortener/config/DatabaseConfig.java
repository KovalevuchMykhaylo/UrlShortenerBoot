package com.shortener.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.shortener.repository")
@ComponentScan(basePackages = {"com.shortener.repository", "com.shortener.service"})
@PropertySource("classpath:application.properties")
public class DatabaseConfig {

    @Autowired
    private Environment env;

    @Bean
    public DataSource getDataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(env.getProperty("spring.datasource.driver"));
        dataSource.setJdbcUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUser(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));

        dataSource.setMaxPoolSize(Integer.parseInt(env.getRequiredProperty("spring.datasource.c3p0.maximum-pool-size")));
        dataSource.setMinPoolSize(Integer.parseInt(env.getRequiredProperty("spring.datasource.c3p0.minimum-pool-size")));
        dataSource.setMaxIdleTime(Integer.parseInt(env.getRequiredProperty("spring.datasource.c3p0.connection-timeout")));
        return dataSource;

    }

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean getEntityManagerFactory() throws PropertyVetoException {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(getDataSource());
        emf.setPackagesToScan(new String[]{"com.shortener.entity"}); // Entity package
        emf.setPersistenceUnitName("spring-jpa-unit");
        emf.setJpaVendorAdapter(getHibernateAdapter());
        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect"));
        jpaProperties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
        jpaProperties.put("hibernate.show_sql", env.getProperty("spring.jpa.show-sql"));
        jpaProperties.put("hibernate.format_sql", env.getProperty("spring.jpa.format_sql"));
        jpaProperties.put("hibernate.enable_lazy_load_no_trans", "true"); // Fix - no session
        emf.setJpaProperties(jpaProperties);
        return emf;
    }

    @Bean(name = "transactionManager")
    @Autowired
    public PlatformTransactionManager getTransactionManager(EntityManagerFactory emf) {
        JpaTransactionManager jpaTransaction = new JpaTransactionManager();
        jpaTransaction.setEntityManagerFactory(emf);
        return jpaTransaction;
    }

    @Bean
    public JpaVendorAdapter getHibernateAdapter() {
        return new HibernateJpaVendorAdapter();
    }

}
