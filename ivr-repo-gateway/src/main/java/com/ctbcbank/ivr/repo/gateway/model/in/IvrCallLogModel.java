/**
 * IvrCallLog.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ctbcbank.ivr.repo.gateway.model.in;

public class IvrCallLogModel  implements java.io.Serializable {
	private static final long serialVersionUID = -7188818563495056751L;
	
	private String groupId;
    private String customerId;
    private String startTime;
    private String endTime;
    private String customerName;
    private String groupName;
    private String agentName;
    private String agentExt;
    private String callFrom;
    
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	public String getAgentExt() {
		return agentExt;
	}
	public void setAgentExt(String agentExt) {
		this.agentExt = agentExt;
	}
	public String getCallFrom() {
		return callFrom;
	}
	public void setCallFrom(String callFrom) {
		this.callFrom = callFrom;
	}
	
	@Override
	public String toString() {
		String result = "groupId=" + getGroupId() +
				 		",customerId=" + getCustomerId() +
				 		",startTime=" + getStartTime() +
				 		",endTime=" + getEndTime() +
				 		",customerName=" + getCustomerName() +
				 		",groupName=" + getGroupName() +
				 		",agentName=" + getAgentName() +
				 		",agentExt=" + getAgentExt() +
				 		",callFrom=" + getCallFrom(); 
		return result;
	}
}
