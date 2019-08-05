/**
 * IaIvrSetInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.Ctcb_IAcquirer_Eai;

public class IaIvrSetInfo  implements java.io.Serializable {
    private org.datacontract.schemas._2004._07.Ctcb_IAcquirer_Eai.IvrSetErrorInfo error;

    private java.lang.Boolean isSuccess;

    private java.lang.String transactionId;

    public IaIvrSetInfo() {
    }

    public IaIvrSetInfo(
           org.datacontract.schemas._2004._07.Ctcb_IAcquirer_Eai.IvrSetErrorInfo error,
           java.lang.Boolean isSuccess,
           java.lang.String transactionId) {
           this.error = error;
           this.isSuccess = isSuccess;
           this.transactionId = transactionId;
    }


    /**
     * Gets the error value for this IaIvrSetInfo.
     * 
     * @return error
     */
    public org.datacontract.schemas._2004._07.Ctcb_IAcquirer_Eai.IvrSetErrorInfo getError() {
        return error;
    }


    /**
     * Sets the error value for this IaIvrSetInfo.
     * 
     * @param error
     */
    public void setError(org.datacontract.schemas._2004._07.Ctcb_IAcquirer_Eai.IvrSetErrorInfo error) {
        this.error = error;
    }


    /**
     * Gets the isSuccess value for this IaIvrSetInfo.
     * 
     * @return isSuccess
     */
    public java.lang.Boolean getIsSuccess() {
        return isSuccess;
    }


    /**
     * Sets the isSuccess value for this IaIvrSetInfo.
     * 
     * @param isSuccess
     */
    public void setIsSuccess(java.lang.Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }


    /**
     * Gets the transactionId value for this IaIvrSetInfo.
     * 
     * @return transactionId
     */
    public java.lang.String getTransactionId() {
        return transactionId;
    }


    /**
     * Sets the transactionId value for this IaIvrSetInfo.
     * 
     * @param transactionId
     */
    public void setTransactionId(java.lang.String transactionId) {
        this.transactionId = transactionId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof IaIvrSetInfo)) return false;
        IaIvrSetInfo other = (IaIvrSetInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.error==null && other.getError()==null) || 
             (this.error!=null &&
              this.error.equals(other.getError()))) &&
            ((this.isSuccess==null && other.getIsSuccess()==null) || 
             (this.isSuccess!=null &&
              this.isSuccess.equals(other.getIsSuccess()))) &&
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
        if (getError() != null) {
            _hashCode += getError().hashCode();
        }
        if (getIsSuccess() != null) {
            _hashCode += getIsSuccess().hashCode();
        }
        if (getTransactionId() != null) {
            _hashCode += getTransactionId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(IaIvrSetInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/Ctcb.IAcquirer.Eai", "IaIvrSetInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("error");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/Ctcb.IAcquirer.Eai", "Error"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/Ctcb.IAcquirer.Eai", "IvrSetErrorInfo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isSuccess");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/Ctcb.IAcquirer.Eai", "IsSuccess"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
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
