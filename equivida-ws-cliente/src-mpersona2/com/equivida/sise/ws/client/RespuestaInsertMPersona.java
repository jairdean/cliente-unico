/**
 * RespuestaInsertMPersona.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.equivida.sise.ws.client;

public class RespuestaInsertMPersona  implements java.io.Serializable {
    private java.lang.String campoOut1;

    private java.lang.String campoOut2;

    private java.lang.String campoOut3;

    private java.math.BigDecimal idPersona;

    private java.lang.String idProceso;

    private java.math.BigDecimal numFilas;

    private java.lang.String txtError;

    public RespuestaInsertMPersona() {
    }

    public RespuestaInsertMPersona(
           java.lang.String campoOut1,
           java.lang.String campoOut2,
           java.lang.String campoOut3,
           java.math.BigDecimal idPersona,
           java.lang.String idProceso,
           java.math.BigDecimal numFilas,
           java.lang.String txtError) {
           this.campoOut1 = campoOut1;
           this.campoOut2 = campoOut2;
           this.campoOut3 = campoOut3;
           this.idPersona = idPersona;
           this.idProceso = idProceso;
           this.numFilas = numFilas;
           this.txtError = txtError;
    }


    /**
     * Gets the campoOut1 value for this RespuestaInsertMPersona.
     * 
     * @return campoOut1
     */
    public java.lang.String getCampoOut1() {
        return campoOut1;
    }


    /**
     * Sets the campoOut1 value for this RespuestaInsertMPersona.
     * 
     * @param campoOut1
     */
    public void setCampoOut1(java.lang.String campoOut1) {
        this.campoOut1 = campoOut1;
    }


    /**
     * Gets the campoOut2 value for this RespuestaInsertMPersona.
     * 
     * @return campoOut2
     */
    public java.lang.String getCampoOut2() {
        return campoOut2;
    }


    /**
     * Sets the campoOut2 value for this RespuestaInsertMPersona.
     * 
     * @param campoOut2
     */
    public void setCampoOut2(java.lang.String campoOut2) {
        this.campoOut2 = campoOut2;
    }


    /**
     * Gets the campoOut3 value for this RespuestaInsertMPersona.
     * 
     * @return campoOut3
     */
    public java.lang.String getCampoOut3() {
        return campoOut3;
    }


    /**
     * Sets the campoOut3 value for this RespuestaInsertMPersona.
     * 
     * @param campoOut3
     */
    public void setCampoOut3(java.lang.String campoOut3) {
        this.campoOut3 = campoOut3;
    }


    /**
     * Gets the idPersona value for this RespuestaInsertMPersona.
     * 
     * @return idPersona
     */
    public java.math.BigDecimal getIdPersona() {
        return idPersona;
    }


    /**
     * Sets the idPersona value for this RespuestaInsertMPersona.
     * 
     * @param idPersona
     */
    public void setIdPersona(java.math.BigDecimal idPersona) {
        this.idPersona = idPersona;
    }


    /**
     * Gets the idProceso value for this RespuestaInsertMPersona.
     * 
     * @return idProceso
     */
    public java.lang.String getIdProceso() {
        return idProceso;
    }


    /**
     * Sets the idProceso value for this RespuestaInsertMPersona.
     * 
     * @param idProceso
     */
    public void setIdProceso(java.lang.String idProceso) {
        this.idProceso = idProceso;
    }


    /**
     * Gets the numFilas value for this RespuestaInsertMPersona.
     * 
     * @return numFilas
     */
    public java.math.BigDecimal getNumFilas() {
        return numFilas;
    }


    /**
     * Sets the numFilas value for this RespuestaInsertMPersona.
     * 
     * @param numFilas
     */
    public void setNumFilas(java.math.BigDecimal numFilas) {
        this.numFilas = numFilas;
    }


    /**
     * Gets the txtError value for this RespuestaInsertMPersona.
     * 
     * @return txtError
     */
    public java.lang.String getTxtError() {
        return txtError;
    }


    /**
     * Sets the txtError value for this RespuestaInsertMPersona.
     * 
     * @param txtError
     */
    public void setTxtError(java.lang.String txtError) {
        this.txtError = txtError;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RespuestaInsertMPersona)) return false;
        RespuestaInsertMPersona other = (RespuestaInsertMPersona) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.campoOut1==null && other.getCampoOut1()==null) || 
             (this.campoOut1!=null &&
              this.campoOut1.equals(other.getCampoOut1()))) &&
            ((this.campoOut2==null && other.getCampoOut2()==null) || 
             (this.campoOut2!=null &&
              this.campoOut2.equals(other.getCampoOut2()))) &&
            ((this.campoOut3==null && other.getCampoOut3()==null) || 
             (this.campoOut3!=null &&
              this.campoOut3.equals(other.getCampoOut3()))) &&
            ((this.idPersona==null && other.getIdPersona()==null) || 
             (this.idPersona!=null &&
              this.idPersona.equals(other.getIdPersona()))) &&
            ((this.idProceso==null && other.getIdProceso()==null) || 
             (this.idProceso!=null &&
              this.idProceso.equals(other.getIdProceso()))) &&
            ((this.numFilas==null && other.getNumFilas()==null) || 
             (this.numFilas!=null &&
              this.numFilas.equals(other.getNumFilas()))) &&
            ((this.txtError==null && other.getTxtError()==null) || 
             (this.txtError!=null &&
              this.txtError.equals(other.getTxtError())));
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
        if (getCampoOut1() != null) {
            _hashCode += getCampoOut1().hashCode();
        }
        if (getCampoOut2() != null) {
            _hashCode += getCampoOut2().hashCode();
        }
        if (getCampoOut3() != null) {
            _hashCode += getCampoOut3().hashCode();
        }
        if (getIdPersona() != null) {
            _hashCode += getIdPersona().hashCode();
        }
        if (getIdProceso() != null) {
            _hashCode += getIdProceso().hashCode();
        }
        if (getNumFilas() != null) {
            _hashCode += getNumFilas().hashCode();
        }
        if (getTxtError() != null) {
            _hashCode += getTxtError().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RespuestaInsertMPersona.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.sise.equivida.com/", "respuestaInsertMPersona"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("campoOut1");
        elemField.setXmlName(new javax.xml.namespace.QName("", "campoOut1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("campoOut2");
        elemField.setXmlName(new javax.xml.namespace.QName("", "campoOut2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("campoOut3");
        elemField.setXmlName(new javax.xml.namespace.QName("", "campoOut3"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idPersona");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idPersona"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idProceso");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idProceso"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numFilas");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numFilas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("txtError");
        elemField.setXmlName(new javax.xml.namespace.QName("", "txtError"));
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
