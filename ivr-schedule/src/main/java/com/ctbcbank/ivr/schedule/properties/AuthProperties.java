package com.ctbcbank.ivr.schedule.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = { "classpath:auth.properties" })
public class AuthProperties {
	@Value("${auth.deCompressKey}")
	private String deCompressKey;

	@Value("${auth.host}")
	private String host;
	
	@Value("${auth.username}")
	private String username;
	
	@Value("${auth.password}")
	private String password;
	
	@Value("${auth.remotePath}")
	private String remotePath;

	@Value("${auth.authow05.localPath}")
	private String authow05LocalPath;
	
	@Value("${auth.authow05.checkSql}")
	private String authow05CheckSql;
	
	@Value("${auth.authow05.updateSql}")
	private String authow05UpdateSql;
	
	@Value("${auth.authow05.insertSql}")
	private String authow05InsertSql;
	
	@Value("${auth.authow05.batchSql}")
	private String authow05BatchSql;
	
	@Value("${auth.authpq25.localPath}")
	private String authpq25LocalPath;
	
	@Value("${auth.authpq25.insertSql}")
	private String authpq25InsertSql;
	
	@Value("${auth.authpq25.batchSql}")
	private String authpq25BatchSql;
	
	@Value("${auth.authpq26.localPath}")
	private String authpq26LocalPath;
	
	@Value("${auth.authpq26.insertSql}")
	private String authpq26InsertSql;
	
	@Value("${auth.authpq26.batchSql}")
	private String authpq26BatchSql;
	
	@Value("${auth.authpq27.localPath}")
	private String authpq27LocalPath;
	
	@Value("${auth.authpq27.insertSql}")
	private String authpq27InsertSql;
	
	@Value("${auth.authpq27.batchSql}")
	private String authpq27BatchSql;
	
	@Value("${auth.authpq28.localPath}")
	private String authpq28LocalPath;
	
	@Value("${auth.authpq28.insertSql}")
	private String authpq28InsertSql;
	
	@Value("${auth.authpq28.batchSql}")
	private String authpq28BatchSql;
	
	public String getAuthow05BatchSql() {
		return authow05BatchSql;
	}

	public void setAuthow05BatchSql(String authow05BatchSql) {
		this.authow05BatchSql = authow05BatchSql;
	}

	public String getAuthpq25BatchSql() {
		return authpq25BatchSql;
	}

	public void setAuthpq25BatchSql(String authpq25BatchSql) {
		this.authpq25BatchSql = authpq25BatchSql;
	}

	public String getAuthpq26BatchSql() {
		return authpq26BatchSql;
	}

	public void setAuthpq26BatchSql(String authpq26BatchSql) {
		this.authpq26BatchSql = authpq26BatchSql;
	}

	public String getAuthpq27BatchSql() {
		return authpq27BatchSql;
	}

	public void setAuthpq27BatchSql(String authpq27BatchSql) {
		this.authpq27BatchSql = authpq27BatchSql;
	}

	public String getAuthpq28BatchSql() {
		return authpq28BatchSql;
	}

	public void setAuthpq28BatchSql(String authpq28BatchSql) {
		this.authpq28BatchSql = authpq28BatchSql;
	}

	public String getDeCompressKey() {
		return deCompressKey;
	}

	public void setDeCompressKey(String deCompressKey) {
		this.deCompressKey = deCompressKey;
	}
	
	public String getAuthpq25InsertSql() {
		return authpq25InsertSql;
	}
	
	public void setAuthpq25InsertSql(String authpq25InsertSql) {
		this.authpq25InsertSql = authpq25InsertSql;
	}
	
	public String getAuthpq26InsertSql() {
		return authpq26InsertSql;
	}
	
	public void setAuthpq26InsertSql(String authpq26InsertSql) {
		this.authpq26InsertSql = authpq26InsertSql;
	}
	
	public String getAuthpq27InsertSql() {
		return authpq27InsertSql;
	}
	
	public void setAuthpq27InsertSql(String authpq27InsertSql) {
		this.authpq27InsertSql = authpq27InsertSql;
	}
	
	public String getAuthpq28InsertSql() {
		return authpq28InsertSql;
	}
	
	public void setAuthpq28InsertSql(String authpq28InsertSql) {
		this.authpq28InsertSql = authpq28InsertSql;
	}
	
	public String getAuthow05CheckSql() {
		return authow05CheckSql;
	}
	
	public void setAuthow05CheckSql(String authow05CheckSql) {
		this.authow05CheckSql = authow05CheckSql;
	}
	
	public String getAuthow05UpdateSql() {
		return authow05UpdateSql;
	}
	
	public void setAuthow05UpdateSql(String authow05UpdateSql) {
		this.authow05UpdateSql = authow05UpdateSql;
	}
	
	public String getAuthow05InsertSql() {
		return authow05InsertSql;
	}
	
	public void setAuthow05InsertSql(String authow05InsertSql) {
		this.authow05InsertSql = authow05InsertSql;
	}
	
	public String getHost() {
		return host;
	}
	
	public void setHost(String host) {
		this.host = host;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getAuthpq25LocalPath() {
		return authpq25LocalPath;
	}

	public void setAuthpq25LocalPath(String authpq25LocalPath) {
		this.authpq25LocalPath = authpq25LocalPath;
	}

	public String getAuthpq26LocalPath() {
		return authpq26LocalPath;
	}

	public void setAuthpq26LocalPath(String authpq26LocalPath) {
		this.authpq26LocalPath = authpq26LocalPath;
	}

	public String getAuthpq27LocalPath() {
		return authpq27LocalPath;
	}

	public void setAuthpq27LocalPath(String authpq27LocalPath) {
		this.authpq27LocalPath = authpq27LocalPath;
	}

	public String getAuthpq28LocalPath() {
		return authpq28LocalPath;
	}

	public void setAuthpq28LocalPath(String authpq28LocalPath) {
		this.authpq28LocalPath = authpq28LocalPath;
	}
	
	public String getAuthow05LocalPath() {
		return authow05LocalPath;
	}
	
	public void setAuthow05LocalPath(String authow05LocalPath) {
		this.authow05LocalPath = authow05LocalPath;
	}
	
	public String getRemotePath() {
		return remotePath;
	}

	public void setRemotePath(String remotePath) {
		this.remotePath = remotePath;
	}
}
