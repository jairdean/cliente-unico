/**
 * InsertDireccionContratante.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.equivida.sise.ws.client;

public class InsertDireccionContratante  implements java.io.Serializable {
    private java.lang.String cod_error;

    private java.math.BigDecimal id_mpersona_dir_out;

    private java.lang.String message_wkf;

    public InsertDireccionContratante() {
    }

    public InsertDireccionContratante(
           java.lang.String cod_error,
           java.math.BigDecimal id_mpersona_dir_out,
           java.lang.String message_wkf) {
           this.cod_error = cod_error;
           this.id_mpersona_dir_out = id_mpersona_dir_out;
           this.message_wkf = message_wkf;
    }


    /**
     * Gets the cod_error value for this InsertDireccionContratante.
     * 
     * @return cod_error
     */
    public java.lang.String getCod_error() {
        return cod_error;
    }


    /**
     * Sets the cod_error value for this InsertDireccionContratante.
     * 
     * @param cod_error
     */
    public void setCod_error(java.lang.String cod_error) {
        this.cod_error = cod_error;
    }


    /**
     * Gets the id_mpersona_dir_out value for this InsertDireccionContratante.
     * 
     * @return id_mpersona_dir_out
     */
    public java.math.BigDecimal getId_mpersona_dir_out() {
        return id_mpersona_dir_out;
    }


    /**
     * Sets the id_mpersona_dir_out value for this InsertDireccionContratante.
     * 
     * @param id_mpersona_dir_out
     */
    public void setId_mpersona_dir_out(java.math.BigDecimal id_mpersona_dir_out) {
        this.id_mpersona_dir_out = id_mpersona_dir_out;
    }


    /**
     * Gets the message_wkf value for this InsertDireccionContratante.
     * 
     * @return message_wkf
     */
    public java.lang.String getMessage_wkf() {
        return message_wkf;
    }


    /**
     * Sets the message_wkf value for this InsertDireccionContratante.
     * 
     * @param message_wkf
     */
    public void setMessage_wkf(java.lang.String message_wkf) {
        this.message_wkf = message_wkf;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof InsertDireccionContratante)) return false;
        InsertDireccionContratante other = (InsertDireccionContratante) obj;
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
            ((this.id_mpersona_dir_out==null && other.getId_mpersona_dir_out()==null) || 
             (this.id_mpersona_dir_out!=null &&
              this.id_mpersona_dir_out.equals(other.getId_mpersona_dir_out()))) &&
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
        if (getId_mpersona_dir_out() != null) {
            _hashCode += getId_mpersona_dir_out().hashCode();
        }
        if (getMessage_wkf() != null) {
            _hashCode += getMessage_wkf().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(InsertDireccionContratante.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.sise.equivida.com/", "insertDireccionContratante"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cod_error");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cod_error"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id_mpersona_dir_out");
        elemField.setXmlName(new javax.xml.namespace.QName("", "id_mpersona_dir_out"));
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
