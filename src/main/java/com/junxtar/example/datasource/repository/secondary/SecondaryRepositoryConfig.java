package com.junxtar.example.datasource.repository.secondary;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.junxtar.example.datasource.repository.secondary",  // Secondary repository 패키지
        entityManagerFactoryRef = "secondaryEntityManagerFactory",  // EntityManagerFactory 설정
        transactionManagerRef = "secondaryTransactionManager"  // TransactionManager 설정
)
@ComponentScan(basePackages = "com.junxtar.example.datasource.repository.secondary")
public class SecondaryRepositoryConfig {
    // Secondary repository 관련 설정
}