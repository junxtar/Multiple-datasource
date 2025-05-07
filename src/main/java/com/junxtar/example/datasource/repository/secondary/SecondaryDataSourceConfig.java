package com.junxtar.example.datasource.repository.secondary;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class SecondaryDataSourceConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.secondary")
    public DataSourceProperties secondaryDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource secondaryDataSource() {
        DataSource dataSource = secondaryDataSourceProperties().initializeDataSourceBuilder().build();
        return dataSource;
    }


    @Bean(name = "secondaryTransactionManager")
    PlatformTransactionManager usersTransactionManager(@Qualifier("secondaryEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }


    @Bean("secondaryNamedParameterJdbcTemplate")
    public NamedParameterJdbcTemplate secondaryNamedParameterJdbcTemplate(@Qualifier("secondaryDataSource") DataSource amisDataSource) {
        return new NamedParameterJdbcTemplate(amisDataSource);
    }

    @Bean(name = "secondaryEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean secondaryEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(secondaryDataSource());
        em.setPackagesToScan("com.junxtar.example.datasource.domain.secondary");
        em.setPersistenceUnitName("secondaryPersistenceUnit");
        em.setPersistenceProviderClass(org.hibernate.jpa.HibernatePersistenceProvider.class);
        Properties props = new Properties();
        props.put("hibernate.show_sql", "true");
        props.put("hibernate.jdbc.batch_size", 50);
        props.put("hibernate.physical_naming_strategy", "org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy");
        em.setJpaProperties(props);
        return em;
    }

}
