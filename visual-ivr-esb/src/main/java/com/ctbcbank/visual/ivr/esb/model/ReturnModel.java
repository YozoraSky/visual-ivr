package com.ctbcbank.visual.ivr.esb.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class ReturnModel implements Serializable{
	private static final long serialVersionUID = 745619145476977875L;
	/** The process result.流程處理結果 */
	@ApiModelProperty(value = "電文細部狀態資訊", required = true)
	private ProcessResult processResult = new ProcessResult();

	public ProcessResult getProcessResult() {
		return processResult;
	}

	public void setProcessResult(ProcessResult processResult) {
		this.processResult = processResult;
	}
}
