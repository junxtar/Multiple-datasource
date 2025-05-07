package com.junxtar.example.datasource.repository.primary;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.junxtar.example.datasource.repository.primary",  // Primary repository 패키지
        entityManagerFactoryRef = "primaryEntityManagerFactory",  // EntityManagerFactory 설정
        transactionManagerRef = "primaryTransactionManager"  // TransactionManager 설정
)
@ComponentScan(basePackages = "com.junxtar.example.datasource.repository.primary")
public class PrimaryRepositoryConfig {
    // Primary repository 관련 설정
}