/**
 * RsCumulosDePago.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.equivida.sise.ws.client;

public class RsCumulosDePago  implements java.io.Serializable {
    private java.lang.String estado;

    private java.math.BigDecimal montoMyda;

    private java.math.BigDecimal montoVida;

    private java.lang.String poliza;

    public RsCumulosDePago() {
    }

    public RsCumulosDePago(
           java.lang.String estado,
           java.math.BigDecimal montoMyda,
           java.math.BigDecimal montoVida,
           java.lang.String poliza) {
           this.estado = estado;
           this.montoMyda = montoMyda;
           this.montoVida = montoVida;
           this.poliza = poliza;
    }


    /**
     * Gets the estado value for this RsCumulosDePago.
     * 
     * @return estado
     */
    public java.lang.String getEstado() {
        return estado;
    }


    /**
     * Sets the estado value for this RsCumulosDePago.
     * 
     * @param estado
     */
    public void setEstado(java.lang.String estado) {
        this.estado = estado;
    }


    /**
     * Gets the montoMyda value for this RsCumulosDePago.
     * 
     * @return montoMyda
     */
    public java.math.BigDecimal getMontoMyda() {
        return montoMyda;
    }


    /**
     * Sets the montoMyda value for this RsCumulosDePago.
     * 
     * @param montoMyda
     */
    public void setMontoMyda(java.math.BigDecimal montoMyda) {
        this.montoMyda = montoMyda;
    }


    /**
     * Gets the montoVida value for this RsCumulosDePago.
     * 
     * @return montoVida
     */
    public java.math.BigDecimal getMontoVida() {
        return montoVida;
    }


    /**
     * Sets the montoVida value for this RsCumulosDePago.
     * 
     * @param montoVida
     */
    public void setMontoVida(java.math.BigDecimal montoVida) {
        this.montoVida = montoVida;
    }


    /**
     * Gets the poliza value for this RsCumulosDePago.
     * 
     * @return poliza
     */
    public java.lang.String getPoliza() {
        return poliza;
    }


    /**
     * Sets the poliza value for this RsCumulosDePago.
     * 
     * @param poliza
     */
    public void setPoliza(java.lang.String poliza) {
        this.poliza = poliza;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RsCumulosDePago)) return false;
        RsCumulosDePago other = (RsCumulosDePago) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.estado==null && other.getEstado()==null) || 
             (this.estado!=null &&
              this.estado.equals(other.getEstado()))) &&
            ((this.montoMyda==null && other.getMontoMyda()==null) || 
             (this.montoMyda!=null &&
              this.montoMyda.equals(other.getMontoMyda()))) &&
            ((this.montoVida==null && other.getMontoVida()==null) || 
             (this.montoVida!=null &&
              this.montoVida.equals(other.getMontoVida()))) &&
            ((this.poliza==null && other.getPoliza()==null) || 
             (this.poliza!=null &&
              this.poliza.equals(other.getPoliza())));
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
        if (getEstado() != null) {
            _hashCode += getEstado().hashCode();
        }
        if (getMontoMyda() != null) {
            _hashCode += getMontoMyda().hashCode();
        }
        if (getMontoVida() != null) {
            _hashCode += getMontoVida().hashCode();
        }
        if (getPoliza() != null) {
            _hashCode += getPoliza().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RsCumulosDePago.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.sise.equivida.com/", "rsCumulosDePago"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estado");
        elemField.setXmlName(new javax.xml.namespace.QName("", "estado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("montoMyda");
        elemField.setXmlName(new javax.xml.namespace.QName("", "montoMyda"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("montoVida");
        elemField.setXmlName(new javax.xml.namespace.QName("", "montoVida"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("poliza");
        elemField.setXmlName(new javax.xml.namespace.QName("", "poliza"));
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
