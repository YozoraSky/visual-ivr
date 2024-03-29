package com.ctbcbank.ivr.old.gateway.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.ctbcbank.ivr.old.gateway.encrypt.Log;
import com.ctbcbank.ivr.old.gateway.model.in.RepoModel;

@Component
public class AsyncTask {
	@Autowired
	private Log log;
	@Async("myAsync")
	public void jdbcExecute(JdbcTemplate jdbcTemplate, RepoModel repoModel) {
		try {
			jdbcTemplate.execute(repoModel.getSql());
		}
		catch(Exception e) {
			log.writeError(repoModel, e);
		}
	}
}
