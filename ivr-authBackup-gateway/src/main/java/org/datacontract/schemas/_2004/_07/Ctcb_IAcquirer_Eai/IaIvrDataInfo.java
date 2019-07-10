/**
 * IaIvrDataInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.Ctcb_IAcquirer_Eai;

public class IaIvrDataInfo  implements java.io.Serializable {
    private java.lang.String authApprvCode;

    private org.datacontract.schemas._2004._07.Ctcb_IAcquirer_Eai.IvrDataErrorInfo error;

    private java.lang.String transactionId;

    public IaIvrDataInfo() {
    }

    public IaIvrDataInfo(
           java.lang.String authApprvCode,
           org.datacontract.schemas._2004._07.Ctcb_IAcquirer_Eai.IvrDataErrorInfo error,
           java.lang.String transactionId) {
           this.authApprvCode = authApprvCode;
           this.error = error;
           this.transactionId = transactionId;
    }


    /**
     * Gets the authApprvCode value for this IaIvrDataInfo.
     * 
     * @return authApprvCode
     */
    public java.lang.String getAuthApprvCode() {
        return authApprvCode;
    }


    /**
     * Sets the authApprvCode value for this IaIvrDataInfo.
     * 
     * @param authApprvCode
     */
    public void setAuthApprvCode(java.lang.String authApprvCode) {
        this.authApprvCode = authApprvCode;
    }


    /**
     * Gets the error value for this IaIvrDataInfo.
     * 
     * @return error
     */
    public org.datacontract.schemas._2004._07.Ctcb_IAcquirer_Eai.IvrDataErrorInfo getError() {
        return error;
    }


    /**
     * Sets the error value for this IaIvrDataInfo.
     * 
     * @param error
     */
    public void setError(org.datacontract.schemas._2004._07.Ctcb_IAcquirer_Eai.IvrDataErrorInfo error) {
        this.error = error;
    }


    /**
     * Gets the transactionId value for this IaIvrDataInfo.
     * 
     * @return transactionId
     */
    public java.lang.String getTransactionId() {
        return transactionId;
    }


    /**
     * Sets the transactionId value for this IaIvrDataInfo.
     * 
     * @param transactionId
     */
    public void setTransactionId(java.lang.String transactionId) {
        this.transactionId = transactionId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof IaIvrDataInfo)) return false;
        IaIvrDataInfo other = (IaIvrDataInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.authApprvCode==null && other.getAuthApprvCode()==null) || 
             (this.authApprvCode!=null &&
              this.authApprvCode.equals(other.getAuthApprvCode()))) &&
            ((this.error==null && other.getError()==null) || 
             (this.error!=null &&
              this.error.equals(other.getError()))) &&
            ((this.transactionId==null && other.getTransactionId()==null) || 
             (this.transactionId!=null &&
              this.transactionId.equals(other.getTransactionId())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getAuthApprvCode() != null) {
            _hashCode += getAuthApprvCode().hashCode();
        }
        if (getError() != null) {
            _hashCode += getError().hashCode();
        }
        if (getTransactionId() != null) {
            _hashCode += getTransactionId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(IaIvrDataInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/Ctcb.IAcquirer.Eai", "IaIvrDataInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("authApprvCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/Ctcb.IAcquirer.Eai", "AuthApprvCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("error");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/Ctcb.IAcquirer.Eai", "Error"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/Ctcb.IAcquirer.Eai", "IvrDataErrorInfo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transactionId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/Ctcb.IAcquirer.Eai", "TransactionId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
