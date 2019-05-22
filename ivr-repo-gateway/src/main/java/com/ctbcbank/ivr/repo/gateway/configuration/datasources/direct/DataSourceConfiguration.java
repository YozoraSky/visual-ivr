//package com.ctbcbank.ivr.repo.gateway.configuration.datasources.direct;
//
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//
//
//
//@Configuration
//// @Profile(value = { "directDs" })
//public class DataSourceConfiguration {
//	@Primary
//	@Bean(name = "ivrConfigDs")
//	@ConfigurationProperties(prefix = "spring.ivr_config_ds")
//	public DataSource ivrConfigDs() {
//		return DataSourceBuilder.create().build();
//	}
//	
//	@Bean(name = "ivrLogDs")
//	@ConfigurationProperties(prefix = "spring.ivr_log_ds")
//	public DataSource ivrLogDs() {
//		return DataSourceBuilder.create().build();
//	}
//
//	@Bean(name ="ivrConfigJdbcTemplate")
//	public JdbcTemplate ivrConfigJdbcTemplate(@Qualifier("ivrConfigDs") DataSource dataSource) {
//		return new JdbcTemplate(dataSource);
//	}
//	@Bean(name ="ivrConfigNamedParameterJdbcTemplate")
//	public NamedParameterJdbcTemplate ivrConfigNamedParameterJdbcTemplate(@Qualifier("ivrConfigDs") DataSource dataSource) {
//		return new NamedParameterJdbcTemplate(dataSource);
//	}
//	@Bean(name ="ivrLogJdbcTemplate")
//	public JdbcTemplate ivrLogJdbcTemplate(@Qualifier("ivrLogDs") DataSource dataSource) {
//		return new JdbcTemplate(dataSource);
//	}
//}
