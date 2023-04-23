package com.example.demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.example.demo.model.User;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "userEntityManagerFactory", //
		transactionManagerRef = "userTransactionManager") //
public class UserDatasourceConfiguration {
	@Bean
	@ConfigurationProperties(prefix = "user.datasource")
	DataSource userDatasource() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	PlatformTransactionManager userTransactionManager(
			@Qualifier("userEntityManagerFactory") LocalContainerEntityManagerFactoryBean userEntityManagerFactory) {
		return new JpaTransactionManager(userEntityManagerFactory.getObject());
	}

	@Bean
	LocalContainerEntityManagerFactoryBean userEntityManagerFactory(
			@Qualifier("userDatasource") DataSource userDatasource, EntityManagerFactoryBuilder builder) {
		return builder.dataSource(userDatasource).packages(User.class).build();
	}
}