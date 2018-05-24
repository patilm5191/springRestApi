package com.neu.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
 */

@Configuration
@EnableTransactionManagement
public class JPAConfig {

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
        ds.setUrl("jdbc:mysql://localhost:3306/jpa-db");
        ds.setUsername("root");
        ds.setPassword("root");
        return ds;
    }

    @Bean
    public PlatformTransactionManager txnMgr(EntityManagerFactory emf){
     return new JpaTransactionManager(emf);
    }

    private Properties jpaProperties(){
        Properties props = new Properties();
        props.setProperty("hibernate.dialect","org.hibernate.dialect.MySQLDialect");
        props.setProperty("hibernate.hbm2ddl.auto","create");
        props.setProperty("hibernate.show_sql","true");
        props.setProperty("hibernate.format_sql","true");
        return props;
    }
}
