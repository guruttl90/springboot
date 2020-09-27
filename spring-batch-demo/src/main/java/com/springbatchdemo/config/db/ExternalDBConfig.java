package com.springbatchdemo.config.db;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "externalDbEntityManager",
					transactionManagerRef = "externalDbTransactionManager")
public class ExternalDBConfig {

	 @Bean(name="secondDataSource")
	 @ConfigurationProperties(prefix="spring.second-datasource")
	 public DataSource secondDataSource() {
	    return DataSourceBuilder.create().build();
	 }
	 
	 
	 @Bean
	 DataSourceTransactionManager secondTransactionManager(@Qualifier ("secondDataSource") DataSource datasource) {
	     DataSourceTransactionManager dataSourceTransactionManager  = new DataSourceTransactionManager(datasource);
	     return dataSourceTransactionManager;
	 }

}
