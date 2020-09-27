package com.springbatchdemo.config.db;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "entityManager",
			transactionManagerRef = "inMemtransactionManager",
			basePackages = "com.springbatchdemo.repository")
public class InMemoryDbConfig {
	
	
	@Bean(name="dataSource")
	@ConfigurationProperties(prefix="spring.datasource")
	public DataSource dataSource() {
	   return DataSourceBuilder.create().build();
	}
	
	
	@Bean
	public DataSourceTransactionManager inMemtransactionManager(@Qualifier ("dataSource") DataSource datasource) {
	    DataSourceTransactionManager dataSourceTransactionManager  = new DataSourceTransactionManager(datasource);
	    return dataSourceTransactionManager;
	}	
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManager(@Qualifier ("dataSource") DataSource datasource) {
		//EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.springbatchdemo.model");
		LocalContainerEntityManagerFactoryBean em= new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(datasource);
		em.setPackagesToScan(new String[] { "com.springbatchdemo.model" });
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);		
		return em;
	}
}
