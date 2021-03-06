/**
 * Response.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.equivida.www.services.clienteequivida.mpersona_telef;

public class Response  implements java.io.Serializable {
    private int num_filas;

    private java.lang.String cod_error;

    private java.lang.String msg_error;

    public Response() {
    }

    public Response(
           int num_filas,
           java.lang.String cod_error,
           java.lang.String msg_error) {
           this.num_filas = num_filas;
           this.cod_error = cod_error;
           this.msg_error = msg_error;
    }


    /**
     * Gets the num_filas value for this Response.
     * 
     * @return num_filas
     */
    public int getNum_filas() {
        return num_filas;
    }


    /**
     * Sets the num_filas value for this Response.
     * 
     * @param num_filas
     */
    public void setNum_filas(int num_filas) {
        this.num_filas = num_filas;
    }


    /**
     * Gets the cod_error value for this Response.
     * 
     * @return cod_error
     */
    public java.lang.String getCod_error() {
        return cod_error;
    }


    /**
     * Sets the cod_error value for this Response.
     * 
     * @param cod_error
     */
    public void setCod_error(java.lang.String cod_error) {
        this.cod_error = cod_error;
    }


    /**
     * Gets the msg_error value for this Response.
     * 
     * @return msg_error
     */
    public java.lang.String getMsg_error() {
        return msg_error;
    }


    /**
     * Sets the msg_error value for this Response.
     * 
     * @param msg_error
     */
    public void setMsg_error(java.lang.String msg_error) {
        this.msg_error = msg_error;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Response)) return false;
        Response other = (Response) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.num_filas == other.getNum_filas() &&
            ((this.cod_error==null && other.getCod_error()==null) || 
             (this.cod_error!=null &&
              this.cod_error.equals(other.getCod_error()))) &&
            ((this.msg_error==null && other.getMsg_error()==null) || 
             (this.msg_error!=null &&
              this.msg_error.equals(other.getMsg_error())));
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
        _hashCode += getNum_filas();
        if (getCod_error() != null) {
            _hashCode += getCod_error().hashCode();
        }
        if (getMsg_error() != null) {
            _hashCode += getMsg_error().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Response.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.equivida.com/services/clienteequivida/mpersona_telef", ">response"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("num_filas");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.equivida.com/services/clienteequivida/mpersona_telef", "num_filas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cod_error");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.equivida.com/services/clienteequivida/mpersona_telef", "cod_error"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("msg_error");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.equivida.com/services/clienteequivida/mpersona_telef", "msg_error"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
