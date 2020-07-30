/**
 * RsContratante.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.equivida.sise.ws.client;

public class RsContratante  implements java.io.Serializable {
    private int cod_error;

    private java.math.BigDecimal id_persona_wkf;

    private java.math.BigDecimal id_proceso_wkf;

    private java.lang.String message_wkf;

    private java.lang.String txt_id_persona_wkf;

    public RsContratante() {
    }

    public RsContratante(
           int cod_error,
           java.math.BigDecimal id_persona_wkf,
           java.math.BigDecimal id_proceso_wkf,
           java.lang.String message_wkf,
           java.lang.String txt_id_persona_wkf) {
           this.cod_error = cod_error;
           this.id_persona_wkf = id_persona_wkf;
           this.id_proceso_wkf = id_proceso_wkf;
           this.message_wkf = message_wkf;
           this.txt_id_persona_wkf = txt_id_persona_wkf;
    }


    /**
     * Gets the cod_error value for this RsContratante.
     * 
     * @return cod_error
     */
    public int getCod_error() {
        return cod_error;
    }


    /**
     * Sets the cod_error value for this RsContratante.
     * 
     * @param cod_error
     */
    public void setCod_error(int cod_error) {
        this.cod_error = cod_error;
    }


    /**
     * Gets the id_persona_wkf value for this RsContratante.
     * 
     * @return id_persona_wkf
     */
    public java.math.BigDecimal getId_persona_wkf() {
        return id_persona_wkf;
    }


    /**
     * Sets the id_persona_wkf value for this RsContratante.
     * 
     * @param id_persona_wkf
     */
    public void setId_persona_wkf(java.math.BigDecimal id_persona_wkf) {
        this.id_persona_wkf = id_persona_wkf;
    }


    /**
     * Gets the id_proceso_wkf value for this RsContratante.
     * 
     * @return id_proceso_wkf
     */
    public java.math.BigDecimal getId_proceso_wkf() {
        return id_proceso_wkf;
    }


    /**
     * Sets the id_proceso_wkf value for this RsContratante.
     * 
     * @param id_proceso_wkf
     */
    public void setId_proceso_wkf(java.math.BigDecimal id_proceso_wkf) {
        this.id_proceso_wkf = id_proceso_wkf;
    }


    /**
     * Gets the message_wkf value for this RsContratante.
     * 
     * @return message_wkf
     */
    public java.lang.String getMessage_wkf() {
        return message_wkf;
    }


    /**
     * Sets the message_wkf value for this RsContratante.
     * 
     * @param message_wkf
     */
    public void setMessage_wkf(java.lang.String message_wkf) {
        this.message_wkf = message_wkf;
    }


    /**
     * Gets the txt_id_persona_wkf value for this RsContratante.
     * 
     * @return txt_id_persona_wkf
     */
    public java.lang.String getTxt_id_persona_wkf() {
        return txt_id_persona_wkf;
    }


    /**
     * Sets the txt_id_persona_wkf value for this RsContratante.
     * 
     * @param txt_id_persona_wkf
     */
    public void setTxt_id_persona_wkf(java.lang.String txt_id_persona_wkf) {
        this.txt_id_persona_wkf = txt_id_persona_wkf;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RsContratante)) return false;
        RsContratante other = (RsContratante) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.cod_error == other.getCod_error() &&
            ((this.id_persona_wkf==null && other.getId_persona_wkf()==null) || 
             (this.id_persona_wkf!=null &&
              this.id_persona_wkf.equals(other.getId_persona_wkf()))) &&
            ((this.id_proceso_wkf==null && other.getId_proceso_wkf()==null) || 
             (this.id_proceso_wkf!=null &&
              this.id_proceso_wkf.equals(other.getId_proceso_wkf()))) &&
            ((this.message_wkf==null && other.getMessage_wkf()==null) || 
             (this.message_wkf!=null &&
              this.message_wkf.equals(other.getMessage_wkf()))) &&
            ((this.txt_id_persona_wkf==null && other.getTxt_id_persona_wkf()==null) || 
             (this.txt_id_persona_wkf!=null &&
              this.txt_id_persona_wkf.equals(other.getTxt_id_persona_wkf())));
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
        _hashCode += getCod_error();
        if (getId_persona_wkf() != null) {
            _hashCode += getId_persona_wkf().hashCode();
        }
        if (getId_proceso_wkf() != null) {
            _hashCode += getId_proceso_wkf().hashCode();
        }
        if (getMessage_wkf() != null) {
            _hashCode += getMessage_wkf().hashCode();
        }
        if (getTxt_id_persona_wkf() != null) {
            _hashCode += getTxt_id_persona_wkf().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RsContratante.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.sise.equivida.com/", "rsContratante"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cod_error");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cod_error"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id_persona_wkf");
        elemField.setXmlName(new javax.xml.namespace.QName("", "id_persona_wkf"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id_proceso_wkf");
        elemField.setXmlName(new javax.xml.namespace.QName("", "id_proceso_wkf"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("message_wkf");
        elemField.setXmlName(new javax.xml.namespace.QName("", "message_wkf"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("txt_id_persona_wkf");
        elemField.setXmlName(new javax.xml.namespace.QName("", "txt_id_persona_wkf"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
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
