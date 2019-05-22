package com.ctbcbank.visual.ivr.service.model;

import java.io.Serializable;

public class ReturnModel implements Serializable{
	private static final long serialVersionUID = 745619145476977875L;
	/** The process result.流程處理結果 */
	private ProcessResult processResult = new ProcessResult();

	public ProcessResult getProcessResult() {
		return processResult;
	}

	public void setProcessResult(ProcessResult processResult) {
		this.processResult = processResult;
	}
}
