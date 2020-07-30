/**
 * InsertTelefonoContratante.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.equivida.sise.ws.client;

public class InsertTelefonoContratante  implements java.io.Serializable {
    private java.lang.String cod_error;

    private java.math.BigDecimal id_mper_telef_cod_out;

    private java.lang.String message_wkf;

    public InsertTelefonoContratante() {
    }

    public InsertTelefonoContratante(
           java.lang.String cod_error,
           java.math.BigDecimal id_mper_telef_cod_out,
           java.lang.String message_wkf) {
           this.cod_error = cod_error;
           this.id_mper_telef_cod_out = id_mper_telef_cod_out;
           this.message_wkf = message_wkf;
    }


    /**
     * Gets the cod_error value for this InsertTelefonoContratante.
     * 
     * @return cod_error
     */
    public java.lang.String getCod_error() {
        return cod_error;
    }


    /**
     * Sets the cod_error value for this InsertTelefonoContratante.
     * 
     * @param cod_error
     */
    public void setCod_error(java.lang.String cod_error) {
        this.cod_error = cod_error;
    }


    /**
     * Gets the id_mper_telef_cod_out value for this InsertTelefonoContratante.
     * 
     * @return id_mper_telef_cod_out
     */
    public java.math.BigDecimal getId_mper_telef_cod_out() {
        return id_mper_telef_cod_out;
    }


    /**
     * Sets the id_mper_telef_cod_out value for this InsertTelefonoContratante.
     * 
     * @param id_mper_telef_cod_out
     */
    public void setId_mper_telef_cod_out(java.math.BigDecimal id_mper_telef_cod_out) {
        this.id_mper_telef_cod_out = id_mper_telef_cod_out;
    }


    /**
     * Gets the message_wkf value for this InsertTelefonoContratante.
     * 
     * @return message_wkf
     */
    public java.lang.String getMessage_wkf() {
        return message_wkf;
    }


    /**
     * Sets the message_wkf value for this InsertTelefonoContratante.
     * 
     * @param message_wkf
     */
    public void setMessage_wkf(java.lang.String message_wkf) {
        this.message_wkf = message_wkf;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof InsertTelefonoContratante)) return false;
        InsertTelefonoContratante other = (InsertTelefonoContratante) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.cod_error==null && other.getCod_error()==null) || 
             (this.cod_error!=null &&
              this.cod_error.equals(other.getCod_error()))) &&
            ((this.id_mper_telef_cod_out==null && other.getId_mper_telef_cod_out()==null) || 
             (this.id_mper_telef_cod_out!=null &&
              this.id_mper_telef_cod_out.equals(other.getId_mper_telef_cod_out()))) &&
            ((this.message_wkf==null && other.getMessage_wkf()==null) || 
             (this.message_wkf!=null &&
              this.message_wkf.equals(other.getMessage_wkf())));
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
        if (getCod_error() != null) {
            _hashCode += getCod_error().hashCode();
        }
        if (getId_mper_telef_cod_out() != null) {
            _hashCode += getId_mper_telef_cod_out().hashCode();
        }
        if (getMessage_wkf() != null) {
            _hashCode += getMessage_wkf().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(InsertTelefonoContratante.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.sise.equivida.com/", "insertTelefonoContratante"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cod_error");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cod_error"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id_mper_telef_cod_out");
        elemField.setXmlName(new javax.xml.namespace.QName("", "id_mper_telef_cod_out"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("message_wkf");
        elemField.setXmlName(new javax.xml.namespace.QName("", "message_wkf"));
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
