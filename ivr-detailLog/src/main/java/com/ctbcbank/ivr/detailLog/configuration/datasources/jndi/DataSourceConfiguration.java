package com.ctbcbank.ivr.detailLog.configuration.datasources.jndi;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
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
	@ConfigurationProperties(prefix = "spring.ivr_log_ds")
	public JndiPropertyHolder secondary() {
		return new JndiPropertyHolder();
	}
	
	@Bean(name = "ivrLogDs")
	public DataSource secondaryDataSource() {
		JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
		return dataSourceLookup.getDataSource(secondary().getJndiName());
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

        public void setJndiName(String jndiName) {
            this.jndiName = jndiName;
        }
    }
}
