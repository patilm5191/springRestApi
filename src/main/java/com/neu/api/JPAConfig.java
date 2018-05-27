package com.neu.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 *  We can configure persistence.xml directly if we have any inside project.
 *  JPAConfig file contains information about DataSource, JDBC Connection and Hibernate properties.
 *  This file is same as persistence.xml
 *
 *  @PropertySource: tells spring to find properties file at given path
 */

@Configuration
@EnableTransactionManagement
@PropertySource(value = "classpath:application.properties")
public class JPAConfig {

    @Autowired
    private Environment env;

    @Bean
    public LocalContainerEntityManagerFactoryBean emf(){
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter()); // same as setting up jpa provider.
        emf.setDataSource(getDataSource());
        emf.setPackagesToScan("com.neu.api.entity");
        emf.setJpaProperties(jpaProperties());
        return emf;
    }

    @Bean
    public DataSource getDataSource(){
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl(env.getProperty("db.url"));
        ds.setUsername(env.getProperty("db.username"));
        ds.setPassword(env.getProperty("db.password"));
        return ds;
    }

    @Bean
    public PlatformTransactionManager txnMgr(EntityManagerFactory emf){
     return new JpaTransactionManager(emf);
    }

    private Properties jpaProperties(){
        Properties props = new Properties();
        props.setProperty("hibernate.dialect","org.hibernate.dialect.MySQLDialect");
        props.setProperty("hibernate.hbm2ddl.auto",env.getProperty("hibernate.hbm2ddl.auto"));
        props.setProperty("hibernate.show_sql",env.getProperty("hibernate.show_sql"));
        props.setProperty("hibernate.format_sql",env.getProperty("hibernate.format_sql"));
        return props;
    }
}
