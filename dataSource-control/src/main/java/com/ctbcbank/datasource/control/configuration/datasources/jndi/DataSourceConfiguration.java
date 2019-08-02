package com.ctbcbank.datasource.control.configuration.datasources.jndi;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

//import com.zaxxer.hikari.HikariConfig;
//import com.zaxxer.hikari.HikariDataSource;


@Configuration
//@Profile(value = { "jndiDs" })
public class DataSourceConfiguration {
	@Bean
	@ConfigurationProperties(prefix = "spring.ivr_config_ds")
	public JndiPropertyHolder primary() {
		return new JndiPropertyHolder();
	}
	
	@Bean(name = "ivrConfigDs")
	@Primary
	public DataSource primaryDataSource() {
		JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
		return dataSourceLookup.getDataSource(primary().getJndiName());
	}
	
	@Bean
	@ConfigurationProperties(prefix = "spring.ivr_config_ds_backup")
	public JndiPropertyHolder primary_backup() {
		return new JndiPropertyHolder();
	}
	
	@Bean(name = "ivrConfigDs_backup")
	public DataSource primaryDataSource_backup() {
		JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
		return dataSourceLookup.getDataSource(primary_backup().getJndiName());
	}
	
//	@Bean(name = "ivrConfigDs")
//	@Primary
//	public DataSource primaryDataSource() {
//		JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
//		DataSource dataSourceTemp = dataSourceLookup.getDataSource(primary().getJndiName());
//		HikariConfig config = new HikariConfig();
//		config.setDataSource(dataSourceTemp);
//		return new HikariDataSource(config);
//	}
	
	@Bean
	@ConfigurationProperties(prefix = "spring.ivr_log_ds")
	public JndiPropertyHolder secondary() {
		return new JndiPropertyHolder();
	}
	
	@Bean(name = "ivrLogDs")
	public DataSource secondaryDataSource() {
		JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
		return dataSourceLookup.getDataSource(secondary().getJndiName());
	}
	
	@Bean
	@ConfigurationProperties(prefix = "spring.ivr_log_ds_backup")
	public JndiPropertyHolder secondary_backup() {
		return new JndiPropertyHolder();
	}
	
	@Bean(name = "ivrLogDs_backup")
	public DataSource secondaryDataSource_backup() {
		JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
		return dataSourceLookup.getDataSource(secondary_backup().getJndiName());
	}
	
	@Bean(name ="ivrConfigJdbcTemplate")
	public JdbcTemplate ivrConfigJdbcTemplate(@Qualifier("ivrConfigDs") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
	@Bean(name ="ivrConfigJdbcTemplate_backup")
	public JdbcTemplate ivrConfigJdbcTemplate_backup(@Qualifier("ivrConfigDs_backup") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
	
	@Bean(name ="ivrLogJdbcTemplate")
	public JdbcTemplate ivrLogJdbcTemplate(@Qualifier("ivrLogDs") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
	@Bean(name ="ivrLogJdbcTemplate_backup")
	public JdbcTemplate ivrLogJdbcTemplate_backup(@Qualifier("ivrLogDs_backup") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
	
	private static class JndiPropertyHolder {
        private String jndiName;

        public String getJndiName() {
            return jndiName;
        }

        public void setJndiName(String jndiName) {
            this.jndiName = jndiName;
        }
    }
}
