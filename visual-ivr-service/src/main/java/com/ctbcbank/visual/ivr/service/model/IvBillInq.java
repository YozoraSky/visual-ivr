package com.ctbcbank.visual.ivr.service.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IvBillInq implements Serializable {
	private static final long serialVersionUID = -6535236409364227721L;
	@JsonProperty(value = "RESHDR")
	protected IvBillInq.RESHDR reshdr;
	@JsonProperty(value = "RESBDY")
	protected IvBillInq.RESBDY resbdy;
	@JsonProperty(value = "RESTLR")
	protected IvBillInq.RESTLR restlr;

	/**
	 * 取得 reshdr 特性的值.
	 * 
	 * @return possible object is {@link IvBillInq.RESHDR }
	 * 
	 */
	public IvBillInq.RESHDR getRESHDR() {
		return reshdr;
	}

	/**
	 * 設定 reshdr 特性的值.
	 * 
	 * @param value allowed object is {@link IvBillInq.RESHDR }
	 * 
	 */
	public void setRESHDR(IvBillInq.RESHDR value) {
		this.reshdr = value;
	}

	/**
	 * 取得 resbdy 特性的值.
	 * 
	 * @return possible object is {@link IvBillInq.RESBDY }
	 * 
	 */
	public IvBillInq.RESBDY getRESBDY() {
		return resbdy;
	}

	/**
	 * 設定 resbdy 特性的值.
	 * 
	 * @param value allowed object is {@link IvBillInq.RESBDY }
	 * 
	 */
	public void setRESBDY(IvBillInq.RESBDY value) {
		this.resbdy = value;
	}

	/**
	 * 取得 restlr 特性的值.
	 * 
	 * @return possible object is {@link IvBillInq.RESTLR }
	 * 
	 */
	public IvBillInq.RESTLR getRESTLR() {
		return restlr;
	}

	/**
	 * 設定 restlr 特性的值.
	 * 
	 * @param value allowed object is {@link IvBillInq.RESTLR }
	 * 
	 */
	public void setRESTLR(IvBillInq.RESTLR value) {
		this.restlr = value;
	}

	public static class RESBDY {
		@JsonProperty(value = "BDYREC")
		protected List<IvBillInq.RESBDY.BDYREC> bdyrec;

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
		 * Objects of the following type(s) are allowed in the list {@link IvBillInq.RESBDY.BDYREC }
		 * 
		 * 
		 */
		public List<IvBillInq.RESBDY.BDYREC> getBDYREC() {
			if (bdyrec == null) {
				bdyrec = new ArrayList<IvBillInq.RESBDY.BDYREC>();
			}
			return this.bdyrec;
		}

		public static class BDYREC {
			@JsonProperty(value = "FLID")
			protected String flid;
			@JsonProperty(value = "BillDate")
			protected String billDate;
			@JsonProperty(value = "CurrBalance")
			protected BigDecimal currBalance;
			@JsonProperty(value = "DueAmt")
			protected BigDecimal dueAmt;
			@JsonProperty(value = "PymtDueDate")
			protected String pymtDueDate;
			@JsonProperty(value = "StmtYYYYMM")
			protected String stmtYYYYMM;
			@JsonProperty(value = "CurrBalSign")
			protected String currBalSign;
			@JsonProperty(value = "UnpaidStmtBal")
			protected BigDecimal unpaidStmtBal;
			@JsonProperty(value = "TotAmtDueSign")
			protected String totAmtDueSign;
			@JsonProperty(value = "TotAmtDue")
			protected BigDecimal totAmtDue;
			@JsonProperty(value = "PymtFlag")
			protected String pymtFlag;
			@JsonProperty(value = "TotTxAmtSign")
			protected String totTxAmtSign;
			@JsonProperty(value = "TotTxAmt")
			protected BigDecimal totTxAmt;

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
			 * 取得 billDate 特性的值.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getBillDate() {
				return billDate;
			}

			/**
			 * 設定 billDate 特性的值.
			 * 
			 * @param value allowed object is {@link String }
			 * 
			 */
			public void setBillDate(String value) {
				this.billDate = value;
			}

			/**
			 * 取得 currBalance 特性的值.
			 * 
			 * @return possible object is {@link BigDecimal }
			 * 
			 */
			public BigDecimal getCurrBalance() {
				return currBalance;
			}

			/**
			 * 設定 currBalance 特性的值.
			 * 
			 * @param value allowed object is {@link BigDecimal }
			 * 
			 */
			public void setCurrBalance(BigDecimal value) {
				this.currBalance = value;
			}

			/**
			 * 取得 dueAmt 特性的值.
			 * 
			 * @return possible object is {@link BigDecimal }
			 * 
			 */
			public BigDecimal getDueAmt() {
				return dueAmt;
			}

			/**
			 * 設定 dueAmt 特性的值.
			 * 
			 * @param value allowed object is {@link BigDecimal }
			 * 
			 */
			public void setDueAmt(BigDecimal value) {
				this.dueAmt = value;
			}

			/**
			 * 取得 pymtDueDate 特性的值.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getPymtDueDate() {
				return pymtDueDate;
			}

			/**
			 * 設定 pymtDueDate 特性的值.
			 * 
			 * @param value allowed object is {@link String }
			 * 
			 */
			public void setPymtDueDate(String value) {
				this.pymtDueDate = value;
			}

			/**
			 * 取得 stmtYYYYMM 特性的值.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getStmtYYYYMM() {
				return stmtYYYYMM;
			}

			/**
			 * 設定 stmtYYYYMM 特性的值.
			 * 
			 * @param value allowed object is {@link String }
			 * 
			 */
			public void setStmtYYYYMM(String value) {
				this.stmtYYYYMM = value;
			}

			/**
			 * 取得 currBalSign 特性的值.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getCurrBalSign() {
				return currBalSign;
			}

			/**
			 * 設定 currBalSign 特性的值.
			 * 
			 * @param value allowed object is {@link String }
			 * 
			 */
			public void setCurrBalSign(String value) {
				this.currBalSign = value;
			}

			/**
			 * 取得 unpaidStmtBal 特性的值.
			 * 
			 * @return possible object is {@link BigDecimal }
			 * 
			 */
			public BigDecimal getUnpaidStmtBal() {
				return unpaidStmtBal;
			}

			/**
			 * 設定 unpaidStmtBal 特性的值.
			 * 
			 * @param value allowed object is {@link BigDecimal }
			 * 
			 */
			public void setUnpaidStmtBal(BigDecimal value) {
				this.unpaidStmtBal = value;
			}

			/**
			 * 取得 totAmtDueSign 特性的值.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getTotAmtDueSign() {
				return totAmtDueSign;
			}

			/**
			 * 設定 totAmtDueSign 特性的值.
			 * 
			 * @param value allowed object is {@link String }
			 * 
			 */
			public void setTotAmtDueSign(String value) {
				this.totAmtDueSign = value;
			}

			/**
			 * 取得 totAmtDue 特性的值.
			 * 
			 * @return possible object is {@link BigDecimal }
			 * 
			 */
			public BigDecimal getTotAmtDue() {
				return totAmtDue;
			}

			/**
			 * 設定 totAmtDue 特性的值.
			 * 
			 * @param value allowed object is {@link BigDecimal }
			 * 
			 */
			public void setTotAmtDue(BigDecimal value) {
				this.totAmtDue = value;
			}

			/**
			 * 取得 pymtFlag 特性的值.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getPymtFlag() {
				return pymtFlag;
			}

			/**
			 * 設定 pymtFlag 特性的值.
			 * 
			 * @param value allowed object is {@link String }
			 * 
			 */
			public void setPymtFlag(String value) {
				this.pymtFlag = value;
			}

			/**
			 * 取得 totTxAmtSign 特性的值.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getTotTxAmtSign() {
				return totTxAmtSign;
			}

			/**
			 * 設定 totTxAmtSign 特性的值.
			 * 
			 * @param value allowed object is {@link String }
			 * 
			 */
			public void setTotTxAmtSign(String value) {
				this.totTxAmtSign = value;
			}

			/**
			 * 取得 totTxAmt 特性的值.
			 * 
			 * @return possible object is {@link BigDecimal }
			 * 
			 */
			public BigDecimal getTotTxAmt() {
				return totTxAmt;
			}

			/**
			 * 設定 totTxAmt 特性的值.
			 * 
			 * @param value allowed object is {@link BigDecimal }
			 * 
			 */
			public void setTotTxAmt(BigDecimal value) {
				this.totTxAmt = value;
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
