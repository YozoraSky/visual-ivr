package com.ctbcbank.visual.ivr.service.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IvCardProfChk implements Serializable {
	private static final long serialVersionUID = 6369292134043513145L;
	@JsonProperty(value = "RESHDR")
	protected IvCardProfChk.RESHDR reshdr;
	@JsonProperty(value = "RESBDY")
	protected IvCardProfChk.RESBDY resbdy;
	@JsonProperty(value = "RESTLR")
	protected IvCardProfChk.RESTLR restlr;

	/**
	 * 取得 reshdr 特性的值.
	 * 
	 * @return possible object is {@link IvCardProfChk.RESHDR }
	 * 
	 */
	public IvCardProfChk.RESHDR getRESHDR() {
		return reshdr;
	}

	/**
	 * 設定 reshdr 特性的值.
	 * 
	 * @param value allowed object is {@link IvCardProfChk.RESHDR }
	 * 
	 */
	public void setRESHDR(IvCardProfChk.RESHDR value) {
		this.reshdr = value;
	}

	/**
	 * 取得 resbdy 特性的值.
	 * 
	 * @return possible object is {@link IvCardProfChk.RESBDY }
	 * 
	 */
	public IvCardProfChk.RESBDY getRESBDY() {
		return resbdy;
	}

	/**
	 * 設定 resbdy 特性的值.
	 * 
	 * @param value allowed object is {@link IvCardProfChk.RESBDY }
	 * 
	 */
	public void setRESBDY(IvCardProfChk.RESBDY value) {
		this.resbdy = value;
	}

	/**
	 * 取得 restlr 特性的值.
	 * 
	 * @return possible object is {@link IvCardProfChk.RESTLR }
	 * 
	 */
	public IvCardProfChk.RESTLR getRESTLR() {
		return restlr;
	}

	/**
	 * 設定 restlr 特性的值.
	 * 
	 * @param value allowed object is {@link IvCardProfChk.RESTLR }
	 * 
	 */
	public void setRESTLR(IvCardProfChk.RESTLR value) {
		this.restlr = value;
	}

	public static class RESBDY {
		@JsonProperty(value = "BDYREC")
		protected List<IvCardProfChk.RESBDY.BDYREC> bdyrec;

		/**
		 * Gets the value of the bdyrec property.
		 * 
		 * <p>
		 * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there
		 * is not a <CODE>set</CODE> method for the bdyrec property.
		 * 
		 * <p>
		 * For example, to add a new item, do as follows:
		 * 
		 * <pre>
		 * getBDYREC().add(newItem);
		 * </pre>
		 * 
		 * 
		 * <p>
		 * Objects of the following type(s) are allowed in the list {@link IvCardProfChk.RESBDY.BDYREC }
		 * 
		 * 
		 */
		public List<IvCardProfChk.RESBDY.BDYREC> getBDYREC() {
			if (bdyrec == null) {
				bdyrec = new ArrayList<IvCardProfChk.RESBDY.BDYREC>();
			}
			return this.bdyrec;
		}

		public static class BDYREC {
			@JsonProperty(value = "FLID")
			protected String flid;
			@JsonProperty(value = "BirthYYYYMMDD")
			protected String birthYYYYMMDD;
			@JsonProperty(value = "PSWD")
			protected String pswd;
			@JsonProperty(value = "NAME")
			protected String name;
			@JsonProperty(value = "MailFiller")
			protected String mailFiller;
			@JsonProperty(value = "PswdFlag")
			protected String pswdFlag;
			@JsonProperty(value = "ChCode")
			protected String chCode;
			@JsonProperty(value = "PriId")
			protected String priId;
			@JsonProperty(value = "AltId")
			protected String altId;
			@JsonProperty(value = "PaymentFlag")
			protected String paymentFlag;
			@JsonProperty(value = "VipFlag")
			protected String vipFlag;
			@JsonProperty(value = "HomePhone")
			protected String homePhone;
			@JsonProperty(value = "OfficePhone")
			protected String officePhone;
			@JsonProperty(value = "MobilePhone")
			protected String mobilePhone;
			@JsonProperty(value = "DdFlag")
			protected String ddFlag;
			@JsonProperty(value = "EMAIL")
			protected String email;
			@JsonProperty(value = "ContactAddr")
			protected String contactAddr;
			@JsonProperty(value = "BillingCycle")
			protected BigDecimal billingCycle;
			@JsonProperty(value = "CorpFlag")
			protected String corpFlag;

			/**
			 * 取得 flid 特性的值.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getFLID() {
				return flid;
			}

			/**
			 * 設定 flid 特性的值.
			 * 
			 * @param value allowed object is {@link String }
			 * 
			 */
			public void setFLID(String value) {
				this.flid = value;
			}

			/**
			 * 取得 birthYYYYMMDD 特性的值.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getBirthYYYYMMDD() {
				return birthYYYYMMDD;
			}

			/**
			 * 設定 birthYYYYMMDD 特性的值.
			 * 
			 * @param value allowed object is {@link String }
			 * 
			 */
			public void setBirthYYYYMMDD(String value) {
				this.birthYYYYMMDD = value;
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

			/**
			 * 取得 name 特性的值.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getNAME() {
				return name;
			}

			/**
			 * 設定 name 特性的值.
			 * 
			 * @param value allowed object is {@link String }
			 * 
			 */
			public void setNAME(String value) {
				this.name = value;
			}

			/**
			 * 取得 mailFiller 特性的值.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getMailFiller() {
				return mailFiller;
			}

			/**
			 * 設定 mailFiller 特性的值.
			 * 
			 * @param value allowed object is {@link String }
			 * 
			 */
			public void setMailFiller(String value) {
				this.mailFiller = value;
			}

			/**
			 * 取得 pswdFlag 特性的值.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getPswdFlag() {
				return pswdFlag;
			}

			/**
			 * 設定 pswdFlag 特性的值.
			 * 
			 * @param value allowed object is {@link String }
			 * 
			 */
			public void setPswdFlag(String value) {
				this.pswdFlag = value;
			}

			/**
			 * 取得 chCode 特性的值.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getChCode() {
				return chCode;
			}

			/**
			 * 設定 chCode 特性的值.
			 * 
			 * @param value allowed object is {@link String }
			 * 
			 */
			public void setChCode(String value) {
				this.chCode = value;
			}

			/**
			 * 取得 priId 特性的值.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getPriId() {
				return priId;
			}

			/**
			 * 設定 priId 特性的值.
			 * 
			 * @param value allowed object is {@link String }
			 * 
			 */
			public void setPriId(String value) {
				this.priId = value;
			}

			/**
			 * 取得 altId 特性的值.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getAltId() {
				return altId;
			}

			/**
			 * 設定 altId 特性的值.
			 * 
			 * @param value allowed object is {@link String }
			 * 
			 */
			public void setAltId(String value) {
				this.altId = value;
			}

			/**
			 * 取得 paymentFlag 特性的值.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getPaymentFlag() {
				return paymentFlag;
			}

			/**
			 * 設定 paymentFlag 特性的值.
			 * 
			 * @param value allowed object is {@link String }
			 * 
			 */
			public void setPaymentFlag(String value) {
				this.paymentFlag = value;
			}

			/**
			 * 取得 vipFlag 特性的值.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getVipFlag() {
				return vipFlag;
			}

			/**
			 * 設定 vipFlag 特性的值.
			 * 
			 * @param value allowed object is {@link String }
			 * 
			 */
			public void setVipFlag(String value) {
				this.vipFlag = value;
			}

			/**
			 * 取得 homePhone 特性的值.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getHomePhone() {
				return homePhone;
			}

			/**
			 * 設定 homePhone 特性的值.
			 * 
			 * @param value allowed object is {@link String }
			 * 
			 */
			public void setHomePhone(String value) {
				this.homePhone = value;
			}

			/**
			 * 取得 officePhone 特性的值.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getOfficePhone() {
				return officePhone;
			}

			/**
			 * 設定 officePhone 特性的值.
			 * 
			 * @param value allowed object is {@link String }
			 * 
			 */
			public void setOfficePhone(String value) {
				this.officePhone = value;
			}

			/**
			 * 取得 mobilePhone 特性的值.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getMobilePhone() {
				return mobilePhone;
			}

			/**
			 * 設定 mobilePhone 特性的值.
			 * 
			 * @param value allowed object is {@link String }
			 * 
			 */
			public void setMobilePhone(String value) {
				this.mobilePhone = value;
			}

			/**
			 * 取得 ddFlag 特性的值.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getDdFlag() {
				return ddFlag;
			}

			/**
			 * 設定 ddFlag 特性的值.
			 * 
			 * @param value allowed object is {@link String }
			 * 
			 */
			public void setDdFlag(String value) {
				this.ddFlag = value;
			}

			/**
			 * 取得 email 特性的值.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getEMAIL() {
				return email;
			}

			/**
			 * 設定 email 特性的值.
			 * 
			 * @param value allowed object is {@link String }
			 * 
			 */
			public void setEMAIL(String value) {
				this.email = value;
			}

			/**
			 * 取得 contactAddr 特性的值.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getContactAddr() {
				return contactAddr;
			}

			/**
			 * 設定 contactAddr 特性的值.
			 * 
			 * @param value allowed object is {@link String }
			 * 
			 */
			public void setContactAddr(String value) {
				this.contactAddr = value;
			}

			/**
			 * 取得 billingCycle 特性的值.
			 * 
			 * @return possible object is {@link BigDecimal }
			 * 
			 */
			public BigDecimal getBillingCycle() {
				return billingCycle;
			}

			/**
			 * 設定 billingCycle 特性的值.
			 * 
			 * @param value allowed object is {@link BigDecimal }
			 * 
			 */
			public void setBillingCycle(BigDecimal value) {
				this.billingCycle = value;
			}

			/**
			 * 取得 corpFlag 特性的值.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getCorpFlag() {
				return corpFlag;
			}

			/**
			 * 設定 corpFlag 特性的值.
			 * 
			 * @param value allowed object is {@link String }
			 * 
			 */
			public void setCorpFlag(String value) {
				this.corpFlag = value;
			}
		}
	}
	public static class RESHDR {
		@JsonProperty(value = "RspCode")
		protected String rspCode;
		@JsonProperty(value = "TrnNum")
		protected String trnNum;

		/**
		 * 取得 rspCode 特性的值.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getRspCode() {
			return rspCode;
		}

		/**
		 * 設定 rspCode 特性的值.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setRspCode(String value) {
			this.rspCode = value;
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
	}
	public static class RESTLR {
	}
}
