package com.ctbcbank.ivr.schedule.gateway.configuration.datasources.jndi;

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
	
	@Bean(name ="ivrConfigJdbcTemplate")
	public JdbcTemplate ivrConfigJdbcTemplate(@Qualifier("ivrConfigDs") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
	@Bean(name ="NamedParameterJdbcTemplate")
	public NamedParameterJdbcTemplate ivrConfigNamedParameterJdbcTemplate(@Qualifier("ivrConfigDs") DataSource dataSource) {
		return new NamedParameterJdbcTemplate(dataSource);
	}
	@Bean(name ="ivrLogJdbcTemplate")
	public JdbcTemplate ivrLogJdbcTemplate(@Qualifier("ivrLogDs") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
	@Bean(name ="ivrLogNamedParameterJdbcTemplate")
	public NamedParameterJdbcTemplate ivrLogNamedParameterJdbcTemplate(@Qualifier("ivrLogDs") DataSource dataSource) {
		return new NamedParameterJdbcTemplate(dataSource);
	}
	
	private static class JndiPropertyHolder {
        private String jndiName;

        public String getJndiName() {
            return jndiName;
        }

        @SuppressWarnings("unused")
		public void setJndiName(String jndiName) {
            this.jndiName = jndiName;
        }
    }
}
