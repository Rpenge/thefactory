package com.systemk.ams.Config.MultiDataBase;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import com.zaxxer.hikari.HikariDataSource;


@Configuration
@PropertySource({"classpath:application.properties"})
@EnableJpaRepositories(
	basePackages = "com.systemk.ams.Repository.Main"
)
public class myDataSourceConfig {
	
	@Autowired 
    private Environment env;
     	
	@Bean(name="entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean myEntityManager() {
        LocalContainerEntityManagerFactoryBean em
          = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(myDataSource());
        em.setPackagesToScan(new String[] {"com.systemk.ams.Entity.Main"});
        HibernateJpaVendorAdapter vendorAdapter= new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto",env.getProperty("my.datasources.hibernate.ddl-auto"));
        properties.put("hibernate.physical_naming_strategy", SpringPhysicalNamingStrategy.class.getName());
        properties.put("hibernate.implicit_naming_strategy", SpringImplicitNamingStrategy.class.getName());
        properties.put("hibernate.enable_lazy_load_no_trans", true);
        em.setJpaPropertyMap(properties);
        return em;
    }
 
    @Bean
    public DataSource myDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(env.getProperty("my.datasources.driverClassName"));
        dataSource.setJdbcUrl(env.getProperty("my.datasources.url"));
        dataSource.setUsername(env.getProperty("my.datasources.username"));
        dataSource.setPassword(env.getProperty("my.datasources.password"));
        dataSource.setMinimumIdle(20);
        dataSource.setMaximumPoolSize(100);
        dataSource.setIdleTimeout(25000);
        dataSource.setMaxLifetime(29000);
        dataSource.setValidationTimeout(10000);
        return new HikariDataSource(dataSource);
    }
 
    @Bean(name="transactionManager")
    public PlatformTransactionManager mytransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(myEntityManager().getObject());
        return transactionManager;
    }
    
    
}