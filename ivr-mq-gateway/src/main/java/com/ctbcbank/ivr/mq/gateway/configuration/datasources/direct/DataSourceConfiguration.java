package com.ctbcbank.ivr.mq.gateway.configuration.datasources.direct;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

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
//	@Bean
//	@ConfigurationProperties(prefix = "spring.ivr_log_ds")
//	public JndiPropertyHolder secondary() {
//		return new JndiPropertyHolder();
//	}
//	@Bean(name = "ivrLogDs")
//	public DataSource secondaryDataSource() {
//		JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
//		return dataSourceLookup.getDataSource(secondary().getJndiName());
//	}
	
	@Bean(name ="ivrConfigJdbcTemplate")
	public JdbcTemplate ivrConfigJdbcTemplate(@Qualifier("ivrConfigDs") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
	@Bean(name ="ivrConfigNamedParameterJdbcTemplate")
	public NamedParameterJdbcTemplate ivrConfigNamedParameterJdbcTemplate(@Qualifier("ivrConfigDs") DataSource dataSource) {
		return new NamedParameterJdbcTemplate(dataSource);
	}
//	@Bean(name ="ivrLogJdbcTemplate")
//	public JdbcTemplate ivrLogJdbcTemplate(@Qualifier("ivrLogDs") DataSource dataSource) {
//		return new JdbcTemplate(dataSource);
//	}
	private static class JndiPropertyHolder{
		private String jndiName;
		
		public String getJndiName() {
			return jndiName;
		}
		@SuppressWarnings("unused")
		public void setJndiName(String jndiName) {
			this.jndiName=jndiName;
		}
	}
}
//	@Bean(name = "openJndiProperties")
//	@ConfigurationProperties(prefix = "spring.ds_open")
//	public JndiProperties openJndiProperties() {
//		return new JndiProperties();
//	}
//
//	@Bean(name = "openDs")
//	@Primary
//	public DataSource openDs(@Qualifier("spring.ds_open") String jndiName) {
//		JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
//		return dataSourceLookup.getDataSource(holder.getJndiName());
//	}
//
//	@Bean(name = "as400JndiProperties")
//	@ConfigurationProperties(prefix = "spring.ds_as400")
//	public JndiProperties as400JndiProperties() {
//		return new JndiProperties();
//	}
//
//	@Bean(name = "as400Ds")
//	public DataSource as400Ds(@Qualifier("as400JndiProperties") JndiProperties holder) {
//		JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
//		return dataSourceLookup.getDataSource(holder.getJndiName());
//	}
//
//	@Bean(name = "ivrJndiProperties")
//	@ConfigurationProperties(prefix = "spring.ds_ivr")
//	public JndiProperties ivrJndiProperties() {
//		return new JndiProperties();
//	}
//
//	@Bean(name = "ivrDs")
//	public DataSource ivrDs(@Qualifier("ivrJndiProperties") JndiProperties properties) {
//		JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
//		return dataSourceLookup.getDataSource(properties.getJndiName());
//	}
//
//	@Bean(name = "sasJndiProperties")
//	@ConfigurationProperties(prefix = "spring.ds_sas")
//	public JndiProperties sasJndiProperties() {
//		return new JndiProperties();
//	}
//
//	@Bean(name = "sasDs")
//	public DataSource sasDs(@Qualifier("sasJndiProperties") JndiProperties properties) {
//		JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
//		return dataSourceLookup.getDataSource(properties.getJndiName());
//	}
//
//	@Bean(name = "ncccJndiProperties")
//	@ConfigurationProperties(prefix = "spring.ds_nccc")
//	public JndiProperties ncccJndiProperties() {
//		return new JndiProperties();
//	}
//
//	@Bean(name = "ncccDs")
//	public DataSource ncccDs(@Qualifier("ncccJndiProperties") JndiProperties properties) {
//		JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
//		return dataSourceLookup.getDataSource(properties.getJndiName());
//	}
//
//	@Bean(name = "sssdbJndiProperties")
//	@ConfigurationProperties(prefix = "spring.ds_sssdb")
//	public JndiProperties sssdbJndiProperties() {
//		return new JndiProperties();
//	}
//
//	@Bean(name = "sssdbDs")
//	public DataSource sssdbDs(@Qualifier("sssdbJndiProperties") JndiProperties properties) {
//		JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
//		return dataSourceLookup.getDataSource(properties.getJndiName());
//	}

