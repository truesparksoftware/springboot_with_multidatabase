package com.example.demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.example.demo.model.Bank;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "bankEntityManagerFactory", transactionManagerRef = "bankTransactionManager")
public class BankDatasourceConfiguration {
	
	@Bean
	@Primary
	@ConfigurationProperties(prefix = "bank.datasource")
	public DataSource bankDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	PlatformTransactionManager bankTransactionManager(
			@Qualifier("bankEntityManagerFactory") LocalContainerEntityManagerFactoryBean bankEntityManagerFactory) {
		return new JpaTransactionManager(bankEntityManagerFactory.getObject());
	}

	@Bean
	LocalContainerEntityManagerFactoryBean bankEntityManagerFactory(
			@Qualifier("bankDataSource") DataSource bankDatasource, EntityManagerFactoryBuilder builder) {
		return builder.dataSource(bankDatasource).packages(Bank.class).build();
	}
}