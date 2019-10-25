package com.ctbcbank.visual.ivr.service;

import com.ctbcbank.visual.ivr.esb.model.EsbCommandOut;
import com.ctbcbank.visual.ivr.esb.model.EsbIn;

public interface EsbCommandService {
	public EsbCommandOut excute(final EsbIn esbIn, String UUID, long ivrInTime) throws Exception;
}
