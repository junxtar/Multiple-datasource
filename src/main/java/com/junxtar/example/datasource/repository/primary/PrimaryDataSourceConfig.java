package com.junxtar.example.datasource.repository.primary;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class PrimaryDataSourceConfig {

    @Bean
    public DataSource primaryDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setUsername("root");
        dataSource.setPassword("1234");
        return dataSource;
    }


    @Primary
    @Bean(name = "primaryTransactionManager")
    PlatformTransactionManager usersTransactionManager(@Qualifier("primaryEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    @Primary
    @Bean("primaryNamedParameterJdbcTemplate")
    public NamedParameterJdbcTemplate primaryNamedParameterJdbcTemplate(@Qualifier("primaryDataSource") DataSource amisDataSource) {
        return new NamedParameterJdbcTemplate(amisDataSource);
    }

    @Bean(name = {"entityManagerFactory", "primaryEntityManagerFactory"})
    @Primary
    public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(primaryDataSource());
        em.setPackagesToScan("com.junxtar.example.datasource.domain.primary");
        em.setPersistenceUnitName("primaryPersistenceUnit");
        em.setPersistenceProviderClass(org.hibernate.jpa.HibernatePersistenceProvider.class);
        Properties props = new Properties();
        props.put("hibernate.show_sql", "true");
        props.put("hibernate.jdbc.batch_size", 50);
        props.put("hibernate.physical_naming_strategy", "org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy");
        em.setJpaProperties(props);
        return em;
    }
}
