package com.ctbcbank.ivr.repo.gateway.model.in;

public class MPlusIn extends RequestModel{
	private static final long serialVersionUID = -5068327397513119004L;
	private String groupId;
	private String content;
	private String title;
	private IvrCallLogModel ivrCallLog;
	private String nowTime;
	
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public IvrCallLogModel getIvrCallLog() {
		return ivrCallLog;
	}
	public void setIvrCallLog(IvrCallLogModel ivrCallLog) {
		this.ivrCallLog = ivrCallLog;
	}
	public String getNowTime() {
		return nowTime;
	}
	public void setNowTime(String nowTime) {
		this.nowTime = nowTime;
	}
	
	@Override
	public String toString() {
		String result = "groupId=" + getGroupId() + 
						",title=" + getTitle() +
						",content=" + getContent() +
						",ivrCallLog={" + getIvrCallLog().toString() + "}" +
						",nowTime=" + getNowTime();
		return result;
	}
}
