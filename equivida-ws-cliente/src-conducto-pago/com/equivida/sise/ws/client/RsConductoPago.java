/**
 * RsConductoPago.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.equivida.sise.ws.client;

public class RsConductoPago  implements java.io.Serializable {
    private java.lang.String anio;

    private java.lang.Integer campo;

    private java.lang.String conductoPago;

    private java.lang.String estado;

    private java.math.BigDecimal implimiteTarjeta;

    private java.lang.String mes;

    private java.lang.String numerCuentaTarjeta;

    private java.math.BigDecimal numeroSeguridad;

    private java.lang.String tipoCuenta;

    public RsConductoPago() {
    }

    public RsConductoPago(
           java.lang.String anio,
           java.lang.Integer campo,
           java.lang.String conductoPago,
           java.lang.String estado,
           java.math.BigDecimal implimiteTarjeta,
           java.lang.String mes,
           java.lang.String numerCuentaTarjeta,
           java.math.BigDecimal numeroSeguridad,
           java.lang.String tipoCuenta) {
           this.anio = anio;
           this.campo = campo;
           this.conductoPago = conductoPago;
           this.estado = estado;
           this.implimiteTarjeta = implimiteTarjeta;
           this.mes = mes;
           this.numerCuentaTarjeta = numerCuentaTarjeta;
           this.numeroSeguridad = numeroSeguridad;
           this.tipoCuenta = tipoCuenta;
    }


    /**
     * Gets the anio value for this RsConductoPago.
     * 
     * @return anio
     */
    public java.lang.String getAnio() {
        return anio;
    }


    /**
     * Sets the anio value for this RsConductoPago.
     * 
     * @param anio
     */
    public void setAnio(java.lang.String anio) {
        this.anio = anio;
    }


    /**
     * Gets the campo value for this RsConductoPago.
     * 
     * @return campo
     */
    public java.lang.Integer getCampo() {
        return campo;
    }


    /**
     * Sets the campo value for this RsConductoPago.
     * 
     * @param campo
     */
    public void setCampo(java.lang.Integer campo) {
        this.campo = campo;
    }


    /**
     * Gets the conductoPago value for this RsConductoPago.
     * 
     * @return conductoPago
     */
    public java.lang.String getConductoPago() {
        return conductoPago;
    }


    /**
     * Sets the conductoPago value for this RsConductoPago.
     * 
     * @param conductoPago
     */
    public void setConductoPago(java.lang.String conductoPago) {
        this.conductoPago = conductoPago;
    }


    /**
     * Gets the estado value for this RsConductoPago.
     * 
     * @return estado
     */
    public java.lang.String getEstado() {
        return estado;
    }


    /**
     * Sets the estado value for this RsConductoPago.
     * 
     * @param estado
     */
    public void setEstado(java.lang.String estado) {
        this.estado = estado;
    }


    /**
     * Gets the implimiteTarjeta value for this RsConductoPago.
     * 
     * @return implimiteTarjeta
     */
    public java.math.BigDecimal getImplimiteTarjeta() {
        return implimiteTarjeta;
    }


    /**
     * Sets the implimiteTarjeta value for this RsConductoPago.
     * 
     * @param implimiteTarjeta
     */
    public void setImplimiteTarjeta(java.math.BigDecimal implimiteTarjeta) {
        this.implimiteTarjeta = implimiteTarjeta;
    }


    /**
     * Gets the mes value for this RsConductoPago.
     * 
     * @return mes
     */
    public java.lang.String getMes() {
        return mes;
    }


    /**
     * Sets the mes value for this RsConductoPago.
     * 
     * @param mes
     */
    public void setMes(java.lang.String mes) {
        this.mes = mes;
    }


    /**
     * Gets the numerCuentaTarjeta value for this RsConductoPago.
     * 
     * @return numerCuentaTarjeta
     */
    public java.lang.String getNumerCuentaTarjeta() {
        return numerCuentaTarjeta;
    }


    /**
     * Sets the numerCuentaTarjeta value for this RsConductoPago.
     * 
     * @param numerCuentaTarjeta
     */
    public void setNumerCuentaTarjeta(java.lang.String numerCuentaTarjeta) {
        this.numerCuentaTarjeta = numerCuentaTarjeta;
    }


    /**
     * Gets the numeroSeguridad value for this RsConductoPago.
     * 
     * @return numeroSeguridad
     */
    public java.math.BigDecimal getNumeroSeguridad() {
        return numeroSeguridad;
    }


    /**
     * Sets the numeroSeguridad value for this RsConductoPago.
     * 
     * @param numeroSeguridad
     */
    public void setNumeroSeguridad(java.math.BigDecimal numeroSeguridad) {
        this.numeroSeguridad = numeroSeguridad;
    }


    /**
     * Gets the tipoCuenta value for this RsConductoPago.
     * 
     * @return tipoCuenta
     */
    public java.lang.String getTipoCuenta() {
        return tipoCuenta;
    }


    /**
     * Sets the tipoCuenta value for this RsConductoPago.
     * 
     * @param tipoCuenta
     */
    public void setTipoCuenta(java.lang.String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RsConductoPago)) return false;
        RsConductoPago other = (RsConductoPago) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.anio==null && other.getAnio()==null) || 
             (this.anio!=null &&
              this.anio.equals(other.getAnio()))) &&
            ((this.campo==null && other.getCampo()==null) || 
             (this.campo!=null &&
              this.campo.equals(other.getCampo()))) &&
            ((this.conductoPago==null && other.getConductoPago()==null) || 
             (this.conductoPago!=null &&
              this.conductoPago.equals(other.getConductoPago()))) &&
            ((this.estado==null && other.getEstado()==null) || 
             (this.estado!=null &&
              this.estado.equals(other.getEstado()))) &&
            ((this.implimiteTarjeta==null && other.getImplimiteTarjeta()==null) || 
             (this.implimiteTarjeta!=null &&
              this.implimiteTarjeta.equals(other.getImplimiteTarjeta()))) &&
            ((this.mes==null && other.getMes()==null) || 
             (this.mes!=null &&
              this.mes.equals(other.getMes()))) &&
            ((this.numerCuentaTarjeta==null && other.getNumerCuentaTarjeta()==null) || 
             (this.numerCuentaTarjeta!=null &&
              this.numerCuentaTarjeta.equals(other.getNumerCuentaTarjeta()))) &&
            ((this.numeroSeguridad==null && other.getNumeroSeguridad()==null) || 
             (this.numeroSeguridad!=null &&
              this.numeroSeguridad.equals(other.getNumeroSeguridad()))) &&
            ((this.tipoCuenta==null && other.getTipoCuenta()==null) || 
             (this.tipoCuenta!=null &&
              this.tipoCuenta.equals(other.getTipoCuenta())));
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
        if (getAnio() != null) {
            _hashCode += getAnio().hashCode();
        }
        if (getCampo() != null) {
            _hashCode += getCampo().hashCode();
        }
        if (getConductoPago() != null) {
            _hashCode += getConductoPago().hashCode();
        }
        if (getEstado() != null) {
            _hashCode += getEstado().hashCode();
        }
        if (getImplimiteTarjeta() != null) {
            _hashCode += getImplimiteTarjeta().hashCode();
        }
        if (getMes() != null) {
            _hashCode += getMes().hashCode();
        }
        if (getNumerCuentaTarjeta() != null) {
            _hashCode += getNumerCuentaTarjeta().hashCode();
        }
        if (getNumeroSeguridad() != null) {
            _hashCode += getNumeroSeguridad().hashCode();
        }
        if (getTipoCuenta() != null) {
            _hashCode += getTipoCuenta().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RsConductoPago.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.sise.equivida.com/", "rsConductoPago"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("anio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "anio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("campo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "campo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("conductoPago");
        elemField.setXmlName(new javax.xml.namespace.QName("", "conductoPago"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estado");
        elemField.setXmlName(new javax.xml.namespace.QName("", "estado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("implimiteTarjeta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "implimiteTarjeta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mes");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numerCuentaTarjeta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numerCuentaTarjeta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroSeguridad");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroSeguridad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoCuenta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoCuenta"));
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
