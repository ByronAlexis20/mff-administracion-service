package mff.administracion.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import mff.administracion.config.entity.ConfigAdmin;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "adminEntityManagerFactory",
        transactionManagerRef = "adminTransactionManager",
        basePackages = {"mff.administracion.dao"}
)
public class AdminConfig {
	
	@Autowired
	private ConfigAdmin admin;
	
	@Primary
	@Bean(name = "adminDataSource")
	public DataSource dataSource() {
		System.out.println("url: " + admin.getUrl().trim());
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl(admin.getUrl().trim());
        dataSource.setUsername(admin.getUsername().trim());
        dataSource.setPassword(admin.getPassword().trim());
        return dataSource;
    }

	@Primary
    @Bean(name = "adminEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean postgresEntityManagerFactory(@Qualifier("adminDataSource") DataSource dataSource) {
    	LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("mff.administracion.entity");
        em.setPersistenceUnitName("adminPersistence");
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());
        return em;
    }

	@Primary
    @Bean(name = "adminTransactionManager")
    public PlatformTransactionManager integradorTransactionManager(@Qualifier("adminEntityManagerFactory") EntityManagerFactory userEntityManagerFactory) {
        return new JpaTransactionManager(userEntityManagerFactory);
    }
    
	Properties additionalProperties() {
	    Properties properties = new Properties();
	    properties.setProperty("hibernate.show_sql", "true");
	    properties.setProperty("spring.jpa.properties.hibernate.format_sql", "true");
    	properties.setProperty("logging.level.org.hibernate.SQL", "debug");
    	properties.setProperty("spring.jpa.properties.hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
    	properties.setProperty("spring.jpa.properties.hibernate.hbm2ddl.auto", "none");
	    return properties;
	}
	
}