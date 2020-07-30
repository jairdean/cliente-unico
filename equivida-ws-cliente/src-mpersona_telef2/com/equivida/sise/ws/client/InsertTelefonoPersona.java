/**
 * InsertTelefonoPersona.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.equivida.sise.ws.client;

public class InsertTelefonoPersona  implements java.io.Serializable {
    private java.lang.String campoOut1;

    private java.lang.String campoOut2;

    private java.lang.String campoOut3;

    private java.lang.String codError;

    private java.math.BigDecimal idTelefono;

    private java.lang.String msgError;

    private java.math.BigDecimal numFilas;

    public InsertTelefonoPersona() {
    }

    public InsertTelefonoPersona(
           java.lang.String campoOut1,
           java.lang.String campoOut2,
           java.lang.String campoOut3,
           java.lang.String codError,
           java.math.BigDecimal idTelefono,
           java.lang.String msgError,
           java.math.BigDecimal numFilas) {
           this.campoOut1 = campoOut1;
           this.campoOut2 = campoOut2;
           this.campoOut3 = campoOut3;
           this.codError = codError;
           this.idTelefono = idTelefono;
           this.msgError = msgError;
           this.numFilas = numFilas;
    }


    /**
     * Gets the campoOut1 value for this InsertTelefonoPersona.
     * 
     * @return campoOut1
     */
    public java.lang.String getCampoOut1() {
        return campoOut1;
    }


    /**
     * Sets the campoOut1 value for this InsertTelefonoPersona.
     * 
     * @param campoOut1
     */
    public void setCampoOut1(java.lang.String campoOut1) {
        this.campoOut1 = campoOut1;
    }


    /**
     * Gets the campoOut2 value for this InsertTelefonoPersona.
     * 
     * @return campoOut2
     */
    public java.lang.String getCampoOut2() {
        return campoOut2;
    }


    /**
     * Sets the campoOut2 value for this InsertTelefonoPersona.
     * 
     * @param campoOut2
     */
    public void setCampoOut2(java.lang.String campoOut2) {
        this.campoOut2 = campoOut2;
    }


    /**
     * Gets the campoOut3 value for this InsertTelefonoPersona.
     * 
     * @return campoOut3
     */
    public java.lang.String getCampoOut3() {
        return campoOut3;
    }


    /**
     * Sets the campoOut3 value for this InsertTelefonoPersona.
     * 
     * @param campoOut3
     */
    public void setCampoOut3(java.lang.String campoOut3) {
        this.campoOut3 = campoOut3;
    }


    /**
     * Gets the codError value for this InsertTelefonoPersona.
     * 
     * @return codError
     */
    public java.lang.String getCodError() {
        return codError;
    }


    /**
     * Sets the codError value for this InsertTelefonoPersona.
     * 
     * @param codError
     */
    public void setCodError(java.lang.String codError) {
        this.codError = codError;
    }


    /**
     * Gets the idTelefono value for this InsertTelefonoPersona.
     * 
     * @return idTelefono
     */
    public java.math.BigDecimal getIdTelefono() {
        return idTelefono;
    }


    /**
     * Sets the idTelefono value for this InsertTelefonoPersona.
     * 
     * @param idTelefono
     */
    public void setIdTelefono(java.math.BigDecimal idTelefono) {
        this.idTelefono = idTelefono;
    }


    /**
     * Gets the msgError value for this InsertTelefonoPersona.
     * 
     * @return msgError
     */
    public java.lang.String getMsgError() {
        return msgError;
    }


    /**
     * Sets the msgError value for this InsertTelefonoPersona.
     * 
     * @param msgError
     */
    public void setMsgError(java.lang.String msgError) {
        this.msgError = msgError;
    }


    /**
     * Gets the numFilas value for this InsertTelefonoPersona.
     * 
     * @return numFilas
     */
    public java.math.BigDecimal getNumFilas() {
        return numFilas;
    }


    /**
     * Sets the numFilas value for this InsertTelefonoPersona.
     * 
     * @param numFilas
     */
    public void setNumFilas(java.math.BigDecimal numFilas) {
        this.numFilas = numFilas;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof InsertTelefonoPersona)) return false;
        InsertTelefonoPersona other = (InsertTelefonoPersona) obj;
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
            ((this.codError==null && other.getCodError()==null) || 
             (this.codError!=null &&
              this.codError.equals(other.getCodError()))) &&
            ((this.idTelefono==null && other.getIdTelefono()==null) || 
             (this.idTelefono!=null &&
              this.idTelefono.equals(other.getIdTelefono()))) &&
            ((this.msgError==null && other.getMsgError()==null) || 
             (this.msgError!=null &&
              this.msgError.equals(other.getMsgError()))) &&
            ((this.numFilas==null && other.getNumFilas()==null) || 
             (this.numFilas!=null &&
              this.numFilas.equals(other.getNumFilas())));
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
        if (getCodError() != null) {
            _hashCode += getCodError().hashCode();
        }
        if (getIdTelefono() != null) {
            _hashCode += getIdTelefono().hashCode();
        }
        if (getMsgError() != null) {
            _hashCode += getMsgError().hashCode();
        }
        if (getNumFilas() != null) {
            _hashCode += getNumFilas().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(InsertTelefonoPersona.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.sise.equivida.com/", "insertTelefonoPersona"));
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
        elemField.setFieldName("codError");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codError"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idTelefono");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idTelefono"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("msgError");
        elemField.setXmlName(new javax.xml.namespace.QName("", "msgError"));
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
