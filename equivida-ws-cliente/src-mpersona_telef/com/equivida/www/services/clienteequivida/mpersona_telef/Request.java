/**
 * Request.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.equivida.www.services.clienteequivida.mpersona_telef;

public class Request  implements java.io.Serializable {
    private int id_persona;

    private int cod_tipo_telef;

    private java.lang.String txt_telefono;

    public Request() {
    }

    public Request(
           int id_persona,
           int cod_tipo_telef,
           java.lang.String txt_telefono) {
           this.id_persona = id_persona;
           this.cod_tipo_telef = cod_tipo_telef;
           this.txt_telefono = txt_telefono;
    }


    /**
     * Gets the id_persona value for this Request.
     * 
     * @return id_persona
     */
    public int getId_persona() {
        return id_persona;
    }


    /**
     * Sets the id_persona value for this Request.
     * 
     * @param id_persona
     */
    public void setId_persona(int id_persona) {
        this.id_persona = id_persona;
    }


    /**
     * Gets the cod_tipo_telef value for this Request.
     * 
     * @return cod_tipo_telef
     */
    public int getCod_tipo_telef() {
        return cod_tipo_telef;
    }


    /**
     * Sets the cod_tipo_telef value for this Request.
     * 
     * @param cod_tipo_telef
     */
    public void setCod_tipo_telef(int cod_tipo_telef) {
        this.cod_tipo_telef = cod_tipo_telef;
    }


    /**
     * Gets the txt_telefono value for this Request.
     * 
     * @return txt_telefono
     */
    public java.lang.String getTxt_telefono() {
        return txt_telefono;
    }


    /**
     * Sets the txt_telefono value for this Request.
     * 
     * @param txt_telefono
     */
    public void setTxt_telefono(java.lang.String txt_telefono) {
        this.txt_telefono = txt_telefono;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Request)) return false;
        Request other = (Request) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.id_persona == other.getId_persona() &&
            this.cod_tipo_telef == other.getCod_tipo_telef() &&
            ((this.txt_telefono==null && other.getTxt_telefono()==null) || 
             (this.txt_telefono!=null &&
              this.txt_telefono.equals(other.getTxt_telefono())));
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
        _hashCode += getId_persona();
        _hashCode += getCod_tipo_telef();
        if (getTxt_telefono() != null) {
            _hashCode += getTxt_telefono().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Request.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.equivida.com/services/clienteequivida/mpersona_telef", ">request"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id_persona");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.equivida.com/services/clienteequivida/mpersona_telef", "id_persona"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cod_tipo_telef");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.equivida.com/services/clienteequivida/mpersona_telef", "cod_tipo_telef"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("txt_telefono");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.equivida.com/services/clienteequivida/mpersona_telef", "txt_telefono"));
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
