package com.ctbcbank.ivr.repo.gateway.monitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DynamicDataSource {
	
//	main>>Taipei, backup>>Taichung
	private static String dataSource = "main";
	
	@Autowired
	@Qualifier("ivrConfigJdbcTemplate")
	private JdbcTemplate configJdbcTemplate;
	@Autowired
	@Qualifier("ivrConfigJdbcTemplate_backup")
	private JdbcTemplate configJdbcTemplate_backup;
	@Autowired
	@Qualifier("ivrConfigNamedParameterJdbcTemplate")
	private NamedParameterJdbcTemplate configNamedParameterJdbcTemplate;
	@Autowired
	@Qualifier("ivrConfigNamedParameterJdbcTemplate_backup")
	private NamedParameterJdbcTemplate configNamedParameterJdbcTemplate_backup;
	
	@Autowired
	@Qualifier("ivrLogJdbcTemplate")
	private JdbcTemplate logJdbcTemplate;
	@Autowired
	@Qualifier("ivrLogJdbcTemplate_backup")
	private JdbcTemplate logJdbcTemplate_backup;
	@Autowired
	@Qualifier("ivrLogNamedParameterJdbcTemplate")
	private NamedParameterJdbcTemplate logNamedParameterJdbcTemplate;
	@Autowired
	@Qualifier("ivrLogNamedParameterJdbcTemplate_backup")
	private NamedParameterJdbcTemplate logNamedParameterJdbcTemplate_backup;
	
	public JdbcTemplate getConfigJdbcTemplate() {
		if(dataSource.equals("main")) {
			System.out.println("Taipei");
			return configJdbcTemplate;
		}
		else {
			System.out.println("Taichung");
			return configJdbcTemplate_backup;
		}
	}
	
	public JdbcTemplate getLogJdbcTemplate() {
		if(dataSource.equals("main")) {
			System.out.println("Taipei");
			return logJdbcTemplate;
		}
		else {
			System.out.println("Taichung");
			return logJdbcTemplate_backup;
		}
	}
	
	public NamedParameterJdbcTemplate getConfigNamedParameterJdbcTemplate() {
		if(dataSource.equals("main")) {
			System.out.println("Taipei");
			return configNamedParameterJdbcTemplate;
		}
		else {
			System.out.println("Taichung");
			return configNamedParameterJdbcTemplate_backup;
		}
	}
	
	public NamedParameterJdbcTemplate getLogNamedParameterJdbcTemplate() {
		if(dataSource.equals("main")) {
			System.out.println("Taipei");
			return logNamedParameterJdbcTemplate;
		}
		else {
			System.out.println("Taichung");
			return logNamedParameterJdbcTemplate_backup;
		}
	}
	
	public static String getDataSource() {
		return dataSource;
	}

	public static void setDataSource(String dataSource) {
		DynamicDataSource.dataSource = dataSource;
	}
}
