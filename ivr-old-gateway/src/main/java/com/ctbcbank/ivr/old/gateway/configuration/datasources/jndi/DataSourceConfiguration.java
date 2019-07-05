package com.ctbcbank.ivr.old.gateway.configuration.datasources.jndi;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
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
	
//	@Bean
//	@ConfigurationProperties(prefix = "spring.ivr_log_ds")
//	public JndiPropertyHolder secondary() {
//		return new JndiPropertyHolder();
//	}
//	
//	@Bean(name = "ivrLogDs")
//	public DataSource secondaryDataSource() {
//		JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
//		return dataSourceLookup.getDataSource(secondary().getJndiName());
//	}
	
//	@Bean
//	@ConfigurationProperties(prefix = "spring.ivr_club_ds")
//	public JndiPropertyHolder third() {
//		return new JndiPropertyHolder();
//	}
//	
//	@Bean(name = "ivrClubDs")
//	public DataSource thirdDataSource() {
//		JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
//		return dataSourceLookup.getDataSource(third().getJndiName());
//	}
	
//	@Bean
//	@ConfigurationProperties(prefix = "spring.ivr_rcdms_ds")
//	public JndiPropertyHolder fourth() {
//		return new JndiPropertyHolder();
//	}
//	
//	@Bean(name = "ivrRcdmsDs")
//	public DataSource fourthDataSource() {
//		JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
//		return dataSourceLookup.getDataSource(fourth().getJndiName());
//	}
	
//	@Bean
//	@ConfigurationProperties(prefix = "spring.ivr_ctcberp_ds")
//	public JndiPropertyHolder fiveth() {
//		return new JndiPropertyHolder();
//	}
//	
//	@Bean(name = "ivrCtcberpDs")
//	public DataSource fivethDataSource() {
//		JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
//		return dataSourceLookup.getDataSource(fiveth().getJndiName());
//	}
	
	@Bean(name ="ivrConfigJdbcTemplate")
	public JdbcTemplate ivrConfigJdbcTemplate(@Qualifier("ivrConfigDs") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
//	@Bean(name ="ivrLogJdbcTemplate")
//	public JdbcTemplate ivrLogJdbcTemplate(@Qualifier("ivrLogDs") DataSource dataSource) {
//		return new JdbcTemplate(dataSource);
//	}
//	@Bean(name ="ivrClubJdbcTemplate")
//	public JdbcTemplate ivrClubJdbcTemplate(@Qualifier("ivrClubDs") DataSource dataSource) {
//		return new JdbcTemplate(dataSource);
//	}
//	@Bean(name ="ivrRcdmsJdbcTemplate")
//	public JdbcTemplate ivrRcdmsJdbcTemplate(@Qualifier("ivrRcdmsDs") DataSource dataSource) {
//		return new JdbcTemplate(dataSource);
//	}
//	@Bean(name ="ivrCtcberpJdbcTemplate")
//	public JdbcTemplate ivrCtcberpJdbcTemplate(@Qualifier("ivrCtcberpDs") DataSource dataSource) {
//		return new JdbcTemplate(dataSource);
//	}
	
	
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
