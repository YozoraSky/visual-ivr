//
// 此檔案是由 JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 所產生 
// 請參閱 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 一旦重新編譯來源綱要, 對此檔案所做的任何修改都將會遺失. 
// 產生時間: 2018.07.13 於 05:21:55 PM GMT+08:00 
//
package tw.com.chinatrust.ns.xsd.ctcb.esb.message.bsmf.csbillresendinqrs._01;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>
 * anonymous complex type 的 Java 類別.
 * 
 * <p>
 * 下列綱要片段會指定此類別中包含的預期內容.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RESHDR">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="RspCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="TrnNum" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="RESBDY" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="BDYREC" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;all>
 *                             &lt;element name="BillType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="SysSeqNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="CycleDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="ResendCnt" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                           &lt;/all>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "reshdr", "resbdy" })
@XmlRootElement(name = "csBillReSendInqRs")
public class CsBillReSendInqRs {
	@XmlElement(name = "RESHDR", required = true)
	protected CsBillReSendInqRs.RESHDR reshdr;
	@XmlElement(name = "RESBDY")
	protected CsBillReSendInqRs.RESBDY resbdy;

	/**
	 * 取得 reshdr 特性的值.
	 * 
	 * @return possible object is {@link CsBillReSendInqRs.RESHDR }
	 * 
	 */
	public CsBillReSendInqRs.RESHDR getRESHDR() {
		return reshdr;
	}

	/**
	 * 設定 reshdr 特性的值.
	 * 
	 * @param value allowed object is {@link CsBillReSendInqRs.RESHDR }
	 * 
	 */
	public void setRESHDR(CsBillReSendInqRs.RESHDR value) {
		this.reshdr = value;
	}

	/**
	 * 取得 resbdy 特性的值.
	 * 
	 * @return possible object is {@link CsBillReSendInqRs.RESBDY }
	 * 
	 */
	public CsBillReSendInqRs.RESBDY getRESBDY() {
		return resbdy;
	}

	/**
	 * 設定 resbdy 特性的值.
	 * 
	 * @param value allowed object is {@link CsBillReSendInqRs.RESBDY }
	 * 
	 */
	public void setRESBDY(CsBillReSendInqRs.RESBDY value) {
		this.resbdy = value;
	}

	/**
	 * <p>
	 * anonymous complex type 的 Java 類別.
	 * 
	 * <p>
	 * 下列綱要片段會指定此類別中包含的預期內容.
	 * 
	 * <pre>
	 * &lt;complexType>
	 *   &lt;complexContent>
	 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *       &lt;sequence>
	 *         &lt;element name="BDYREC" maxOccurs="unbounded" minOccurs="0">
	 *           &lt;complexType>
	 *             &lt;complexContent>
	 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *                 &lt;all>
	 *                   &lt;element name="BillType" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                   &lt;element name="SysSeqNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                   &lt;element name="CycleDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                   &lt;element name="ResendCnt" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                 &lt;/all>
	 *               &lt;/restriction>
	 *             &lt;/complexContent>
	 *           &lt;/complexType>
	 *         &lt;/element>
	 *       &lt;/sequence>
	 *     &lt;/restriction>
	 *   &lt;/complexContent>
	 * &lt;/complexType>
	 * </pre>
	 * 
	 * 
	 */
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "bdyrec" })
	public static class RESBDY {
		@XmlElement(name = "BDYREC")
		protected List<CsBillReSendInqRs.RESBDY.BDYREC> bdyrec;

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
		 * Objects of the following type(s) are allowed in the list {@link CsBillReSendInqRs.RESBDY.BDYREC }
		 * 
		 * 
		 */
		public List<CsBillReSendInqRs.RESBDY.BDYREC> getBDYREC() {
			if (bdyrec == null) {
				bdyrec = new ArrayList<CsBillReSendInqRs.RESBDY.BDYREC>();
			}
			return this.bdyrec;
		}

		/**
		 * <p>
		 * anonymous complex type 的 Java 類別.
		 * 
		 * <p>
		 * 下列綱要片段會指定此類別中包含的預期內容.
		 * 
		 * <pre>
		 * &lt;complexType>
		 *   &lt;complexContent>
		 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
		 *       &lt;all>
		 *         &lt;element name="BillType" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *         &lt;element name="SysSeqNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *         &lt;element name="CycleDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *         &lt;element name="ResendCnt" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *       &lt;/all>
		 *     &lt;/restriction>
		 *   &lt;/complexContent>
		 * &lt;/complexType>
		 * </pre>
		 * 
		 * 
		 */
		@XmlAccessorType(XmlAccessType.FIELD)
		@XmlType(name = "", propOrder = {})
		public static class BDYREC {
			@XmlElement(name = "BillType", required = true)
			protected String billType;
			@XmlElement(name = "SysSeqNo", required = true)
			protected String sysSeqNo;
			@XmlElement(name = "CycleDate", required = true)
			protected String cycleDate;
			@XmlElement(name = "ResendCnt", required = true)
			protected String resendCnt;

			/**
			 * 取得 billType 特性的值.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getBillType() {
				return billType;
			}

			/**
			 * 設定 billType 特性的值.
			 * 
			 * @param value allowed object is {@link String }
			 * 
			 */
			public void setBillType(String value) {
				this.billType = value;
			}

			/**
			 * 取得 sysSeqNo 特性的值.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getSysSeqNo() {
				return sysSeqNo;
			}

			/**
			 * 設定 sysSeqNo 特性的值.
			 * 
			 * @param value allowed object is {@link String }
			 * 
			 */
			public void setSysSeqNo(String value) {
				this.sysSeqNo = value;
			}

			/**
			 * 取得 cycleDate 特性的值.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getCycleDate() {
				return cycleDate;
			}

			/**
			 * 設定 cycleDate 特性的值.
			 * 
			 * @param value allowed object is {@link String }
			 * 
			 */
			public void setCycleDate(String value) {
				this.cycleDate = value;
			}

			/**
			 * 取得 resendCnt 特性的值.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getResendCnt() {
				return resendCnt;
			}

			/**
			 * 設定 resendCnt 特性的值.
			 * 
			 * @param value allowed object is {@link String }
			 * 
			 */
			public void setResendCnt(String value) {
				this.resendCnt = value;
			}
		}
	}
	/**
	 * <p>
	 * anonymous complex type 的 Java 類別.
	 * 
	 * <p>
	 * 下列綱要片段會指定此類別中包含的預期內容.
	 * 
	 * <pre>
	 * &lt;complexType>
	 *   &lt;complexContent>
	 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *       &lt;sequence>
	 *         &lt;element name="RspCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="TrnNum" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *       &lt;/sequence>
	 *     &lt;/restriction>
	 *   &lt;/complexContent>
	 * &lt;/complexType>
	 * </pre>
	 * 
	 * 
	 */
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "rspCode", "trnNum" })
	public static class RESHDR {
		@XmlElement(name = "RspCode", required = true)
		protected String rspCode;
		@XmlElement(name = "TrnNum", required = true)
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
}
