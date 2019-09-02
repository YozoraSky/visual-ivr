package com.ctbcbank.ivr.repo.gateway.model.in;

public class RepoModelToMultipleSQL extends RequestModel {
	private static final long serialVersionUID = 1923207457465052382L;
	private String[] sql;

	public String[] getSql() {
		return sql;
	}

	public void setSql(String[] sql) {
		this.sql = sql;
	}
}
