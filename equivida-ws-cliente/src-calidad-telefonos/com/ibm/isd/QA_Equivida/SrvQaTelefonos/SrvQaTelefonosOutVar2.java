/**
 * SrvQaTelefonosOutVar2.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ibm.isd.QA_Equivida.SrvQaTelefonos;

public class SrvQaTelefonosOutVar2  implements java.io.Serializable {
    private java.lang.String areacode_telefonos;

    private java.lang.String numberphone_telefonos;

    private java.lang.String mensaje_telefonos;

    private int status;

    private java.lang.String message;

    private java.lang.String unhandledpattern_telefonos;

    public SrvQaTelefonosOutVar2() {
    }

    public SrvQaTelefonosOutVar2(
           java.lang.String areacode_telefonos,
           java.lang.String numberphone_telefonos,
           java.lang.String mensaje_telefonos,
           int status,
           java.lang.String message,
           java.lang.String unhandledpattern_telefonos) {
           this.areacode_telefonos = areacode_telefonos;
           this.numberphone_telefonos = numberphone_telefonos;
           this.mensaje_telefonos = mensaje_telefonos;
           this.status = status;
           this.message = message;
           this.unhandledpattern_telefonos = unhandledpattern_telefonos;
    }


    /**
     * Gets the areacode_telefonos value for this SrvQaTelefonosOutVar2.
     * 
     * @return areacode_telefonos
     */
    public java.lang.String getAreacode_telefonos() {
        return areacode_telefonos;
    }


    /**
     * Sets the areacode_telefonos value for this SrvQaTelefonosOutVar2.
     * 
     * @param areacode_telefonos
     */
    public void setAreacode_telefonos(java.lang.String areacode_telefonos) {
        this.areacode_telefonos = areacode_telefonos;
    }


    /**
     * Gets the numberphone_telefonos value for this SrvQaTelefonosOutVar2.
     * 
     * @return numberphone_telefonos
     */
    public java.lang.String getNumberphone_telefonos() {
        return numberphone_telefonos;
    }


    /**
     * Sets the numberphone_telefonos value for this SrvQaTelefonosOutVar2.
     * 
     * @param numberphone_telefonos
     */
    public void setNumberphone_telefonos(java.lang.String numberphone_telefonos) {
        this.numberphone_telefonos = numberphone_telefonos;
    }


    /**
     * Gets the mensaje_telefonos value for this SrvQaTelefonosOutVar2.
     * 
     * @return mensaje_telefonos
     */
    public java.lang.String getMensaje_telefonos() {
        return mensaje_telefonos;
    }


    /**
     * Sets the mensaje_telefonos value for this SrvQaTelefonosOutVar2.
     * 
     * @param mensaje_telefonos
     */
    public void setMensaje_telefonos(java.lang.String mensaje_telefonos) {
        this.mensaje_telefonos = mensaje_telefonos;
    }


    /**
     * Gets the status value for this SrvQaTelefonosOutVar2.
     * 
     * @return status
     */
    public int getStatus() {
        return status;
    }


    /**
     * Sets the status value for this SrvQaTelefonosOutVar2.
     * 
     * @param status
     */
    public void setStatus(int status) {
        this.status = status;
    }


    /**
     * Gets the message value for this SrvQaTelefonosOutVar2.
     * 
     * @return message
     */
    public java.lang.String getMessage() {
        return message;
    }


    /**
     * Sets the message value for this SrvQaTelefonosOutVar2.
     * 
     * @param message
     */
    public void setMessage(java.lang.String message) {
        this.message = message;
    }


    /**
     * Gets the unhandledpattern_telefonos value for this SrvQaTelefonosOutVar2.
     * 
     * @return unhandledpattern_telefonos
     */
    public java.lang.String getUnhandledpattern_telefonos() {
        return unhandledpattern_telefonos;
    }


    /**
     * Sets the unhandledpattern_telefonos value for this SrvQaTelefonosOutVar2.
     * 
     * @param unhandledpattern_telefonos
     */
    public void setUnhandledpattern_telefonos(java.lang.String unhandledpattern_telefonos) {
        this.unhandledpattern_telefonos = unhandledpattern_telefonos;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SrvQaTelefonosOutVar2)) return false;
        SrvQaTelefonosOutVar2 other = (SrvQaTelefonosOutVar2) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.areacode_telefonos==null && other.getAreacode_telefonos()==null) || 
             (this.areacode_telefonos!=null &&
              this.areacode_telefonos.equals(other.getAreacode_telefonos()))) &&
            ((this.numberphone_telefonos==null && other.getNumberphone_telefonos()==null) || 
             (this.numberphone_telefonos!=null &&
              this.numberphone_telefonos.equals(other.getNumberphone_telefonos()))) &&
            ((this.mensaje_telefonos==null && other.getMensaje_telefonos()==null) || 
             (this.mensaje_telefonos!=null &&
              this.mensaje_telefonos.equals(other.getMensaje_telefonos()))) &&
            this.status == other.getStatus() &&
            ((this.message==null && other.getMessage()==null) || 
             (this.message!=null &&
              this.message.equals(other.getMessage()))) &&
            ((this.unhandledpattern_telefonos==null && other.getUnhandledpattern_telefonos()==null) || 
             (this.unhandledpattern_telefonos!=null &&
              this.unhandledpattern_telefonos.equals(other.getUnhandledpattern_telefonos())));
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
        if (getAreacode_telefonos() != null) {
            _hashCode += getAreacode_telefonos().hashCode();
        }
        if (getNumberphone_telefonos() != null) {
            _hashCode += getNumberphone_telefonos().hashCode();
        }
        if (getMensaje_telefonos() != null) {
            _hashCode += getMensaje_telefonos().hashCode();
        }
        _hashCode += getStatus();
        if (getMessage() != null) {
            _hashCode += getMessage().hashCode();
        }
        if (getUnhandledpattern_telefonos() != null) {
            _hashCode += getUnhandledpattern_telefonos().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SrvQaTelefonosOutVar2.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://SrvQaTelefonos.QA_Equivida.isd.ibm.com", "SrvQaTelefonosOutVar2"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("areacode_telefonos");
        elemField.setXmlName(new javax.xml.namespace.QName("", "areacode_telefonos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numberphone_telefonos");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numberphone_telefonos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mensaje_telefonos");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mensaje_telefonos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("", "status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("message");
        elemField.setXmlName(new javax.xml.namespace.QName("", "message"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("unhandledpattern_telefonos");
        elemField.setXmlName(new javax.xml.namespace.QName("", "unhandledpattern_telefonos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
