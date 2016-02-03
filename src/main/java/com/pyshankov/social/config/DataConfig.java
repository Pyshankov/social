package com.pyshankov.social.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by pyshankov on 28.01.2016.
 */
@Configuration
@EnableTransactionManagement
@PropertySource("classpath:db.properties")
public class DataConfig {

    @Resource
    private Environment env;

    //configure data source
    @Bean
    BasicDataSource dataSource(){
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(env.getRequiredProperty("db.driver"));
        ds.setUrl(env.getRequiredProperty("db.url"));
        ds.setUsername(env.getRequiredProperty("db.user"));
        ds.setPassword(env.getRequiredProperty("db.password"));
        ds.setInitialSize(Integer.parseInt(env.getRequiredProperty("db.initial.size.pool")));
        ds.setMaxActive(Integer.parseInt(env.getRequiredProperty("db.max.size.pool")));
        return ds;
    }
    //configure session factory
    @Bean(name = "sessionFactory")
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean sfb = new LocalSessionFactoryBean();
        sfb.setDataSource(dataSource);
        sfb.setPackagesToScan("com.pyshankov.social.domain.entity" );
        Properties props = new Properties();
        props.setProperty("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
        props.setProperty("hibernate.hbm2ddl.auto", env.getRequiredProperty("hibernate.hbm2ddl.auto"));
        sfb.setHibernateProperties(props);
        return sfb;
    }
    //configure transaction manager bean
    @Bean(name="txGreeting")
    public HibernateTransactionManager txName(SessionFactory sessionFactory, DataSource dataSource) throws IOException {
        HibernateTransactionManager txName= new HibernateTransactionManager();
        txName.setSessionFactory(sessionFactory);
        txName.setDataSource(dataSource);
        return txName;
    }
    /*PersistenceExceptionTranslationPostProcessor is a bean post-processor that adds
   an adviser to any bean that’s annotated with @Repository so that any platform-specific
   exceptions are caught and then rethrown as one of Spring’s unchecked data-access
   exceptions.*/
    @Bean
    public BeanPostProcessor persistenceTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

}
