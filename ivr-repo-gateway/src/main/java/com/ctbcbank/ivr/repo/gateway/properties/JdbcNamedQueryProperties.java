package com.ctbcbank.ivr.repo.gateway.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = { "classpath:jdbc-named-query.properties" })
public class JdbcNamedQueryProperties {
	@Value("${writeIVRHostTxnLog}")
	private String writeIVRHostTxnLog;
	@Value("${countIVRVRFunctionByFunctionId}")
	private String countIVRVRFunctionByFunctionId;

	public String getWriteIVRHostTxnLog() {
		return writeIVRHostTxnLog;
	}

	public void setWriteIVRHostTxnLog(String writeIVRHostTxnLog) {
		this.writeIVRHostTxnLog = writeIVRHostTxnLog;
	}

	public String getCountIVRVRFunctionByFunctionId() {
		return countIVRVRFunctionByFunctionId;
	}

	public void setCountIVRVRFunctionByFunctionId(String countIVRVRFunctionByFunctionId) {
		this.countIVRVRFunctionByFunctionId = countIVRVRFunctionByFunctionId;
	}
}
