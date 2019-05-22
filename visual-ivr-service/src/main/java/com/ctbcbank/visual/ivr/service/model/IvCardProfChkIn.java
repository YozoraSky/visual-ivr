package com.ctbcbank.visual.ivr.service.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IvCardProfChkIn implements Serializable {
	private static final long serialVersionUID = -5018063533251023474L;
	@JsonProperty(value = "REQHDR")
	protected IvCardProfChkIn.REQHDR reqhdr;
	@JsonProperty(value = "REQBDY")
	protected IvCardProfChkIn.REQBDY reqbdy;

	/**
	 * 取得 reqhdr 特性的值.
	 * 
	 * @return possible object is {@link IvCardProfChkIn.REQHDR }
	 * 
	 */
	public IvCardProfChkIn.REQHDR getREQHDR() {
		return reqhdr;
	}

	/**
	 * 設定 reqhdr 特性的值.
	 * 
	 * @param value allowed object is {@link IvCardProfChkIn.REQHDR }
	 * 
	 */
	public void setREQHDR(IvCardProfChkIn.REQHDR value) {
		this.reqhdr = value;
	}

	/**
	 * 取得 reqbdy 特性的值.
	 * 
	 * @return possible object is {@link IvCardProfChkIn.REQBDY }
	 * 
	 */
	public IvCardProfChkIn.REQBDY getREQBDY() {
		return reqbdy;
	}

	/**
	 * 設定 reqbdy 特性的值.
	 * 
	 * @param value allowed object is {@link IvCardProfChkIn.REQBDY }
	 * 
	 */
	public void setREQBDY(IvCardProfChkIn.REQBDY value) {
		this.reqbdy = value;
	}

	public static class REQBDY {
		@JsonProperty(value = "FUNCD")
		protected String funcd;
		@JsonProperty(value = "IDNO")
		protected String idno;
		@JsonProperty(value = "PSWD")
		protected String pswd;

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

		/**
		 * 取得 pswd 特性的值.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getPSWD() {
			return pswd;
		}

		/**
		 * 設定 pswd 特性的值.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setPSWD(String value) {
			this.pswd = value;
		}
	}
	public static class REQHDR {
		@JsonProperty(value = "TrnNum")
		protected String trnNum;
		@JsonProperty(value = "SystemId")
		protected String systemId;
		@JsonProperty(value = "TrnCode")
		protected String trnCode;

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
