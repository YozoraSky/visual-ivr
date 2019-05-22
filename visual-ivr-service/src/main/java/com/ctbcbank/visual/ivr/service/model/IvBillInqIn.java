package com.ctbcbank.visual.ivr.service.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IvBillInqIn implements Serializable {
	private static final long serialVersionUID = 1412602088329368668L;
	@JsonProperty(value = "REQHDR")
	private IvBillInqIn.REQHDR reqhdr;
	@JsonProperty(value = "REQBDY", required = true)
	private IvBillInqIn.REQBDY reqbdy;

	/**
	 * 取得 reqhdr 特性的值.
	 * 
	 * @return possible object is {@link IvBillInqIn.REQHDR }
	 * 
	 */
	public IvBillInqIn.REQHDR getREQHDR() {
		return reqhdr;
	}

	/**
	 * 設定 reqhdr 特性的值.
	 * 
	 * @param value allowed object is {@link IvBillInqIn.REQHDR }
	 * 
	 */
	public void setREQHDR(IvBillInqIn.REQHDR value) {
		this.reqhdr = value;
	}

	/**
	 * 取得 reqbdy 特性的值.
	 * 
	 * @return possible object is {@link IvBillInqIn.REQBDY }
	 * 
	 */
	public IvBillInqIn.REQBDY getREQBDY() {
		return reqbdy;
	}

	/**
	 * 設定 reqbdy 特性的值.
	 * 
	 * @param value allowed object is {@link IvBillInqIn.REQBDY }
	 * 
	 */
	public void setREQBDY(IvBillInqIn.REQBDY value) {
		this.reqbdy = value;
	}

	public static class REQBDY {
		@JsonProperty(value = "FUNCD")
		private String funcd;
		@JsonProperty(value = "IDNO")
		private String idno;

		/**
		 * 取得 funcd 特性的值.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getFUNCD() {
			return funcd;
		}

		/**
		 * 設定 funcd 特性的值.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setFUNCD(String value) {
			this.funcd = value;
		}

		/**
		 * 取得 idno 特性的值.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getIDNO() {
			return idno;
		}

		/**
		 * 設定 idno 特性的值.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setIDNO(String value) {
			this.idno = value;
		}
	}
	public static class REQHDR {
		@JsonProperty(value = "SystemId")
		private String systemId;
		@JsonProperty(value = "TrnNum")
		private String trnNum;
		@JsonProperty(value = "TrnCode")
		private String trnCode;

		/**
		 * 取得 systemId 特性的值.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getSystemId() {
			return systemId;
		}

		/**
		 * 設定 systemId 特性的值.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setSystemId(String value) {
			this.systemId = value;
		}

		/**
		 * 取得 trnNum 特性的值.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getTrnNum() {
			return trnNum;
		}

		/**
		 * 設定 trnNum 特性的值.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setTrnNum(String value) {
			this.trnNum = value;
		}

		/**
		 * 取得 trnCode 特性的值.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getTrnCode() {
			return trnCode;
		}

		/**
		 * 設定 trnCode 特性的值.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setTrnCode(String value) {
			this.trnCode = value;
		}
	}
}
