/**
 * Request.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.equivida.www.services.clienteequivida.mpersona_dir;

public class Request  implements java.io.Serializable {
    private int id_persona;

    private int cod_tipo_dir;

    private java.lang.String txt_direccion;

    private int cod_municipio;

    private int cod_dpto;

    private int cod_pais;

    public Request() {
    }

    public Request(
           int id_persona,
           int cod_tipo_dir,
           java.lang.String txt_direccion,
           int cod_municipio,
           int cod_dpto,
           int cod_pais) {
           this.id_persona = id_persona;
           this.cod_tipo_dir = cod_tipo_dir;
           this.txt_direccion = txt_direccion;
           this.cod_municipio = cod_municipio;
           this.cod_dpto = cod_dpto;
           this.cod_pais = cod_pais;
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
     * Gets the cod_tipo_dir value for this Request.
     * 
     * @return cod_tipo_dir
     */
    public int getCod_tipo_dir() {
        return cod_tipo_dir;
    }


    /**
     * Sets the cod_tipo_dir value for this Request.
     * 
     * @param cod_tipo_dir
     */
    public void setCod_tipo_dir(int cod_tipo_dir) {
        this.cod_tipo_dir = cod_tipo_dir;
    }


    /**
     * Gets the txt_direccion value for this Request.
     * 
     * @return txt_direccion
     */
    public java.lang.String getTxt_direccion() {
        return txt_direccion;
    }


    /**
     * Sets the txt_direccion value for this Request.
     * 
     * @param txt_direccion
     */
    public void setTxt_direccion(java.lang.String txt_direccion) {
        this.txt_direccion = txt_direccion;
    }


    /**
     * Gets the cod_municipio value for this Request.
     * 
     * @return cod_municipio
     */
    public int getCod_municipio() {
        return cod_municipio;
    }


    /**
     * Sets the cod_municipio value for this Request.
     * 
     * @param cod_municipio
     */
    public void setCod_municipio(int cod_municipio) {
        this.cod_municipio = cod_municipio;
    }


    /**
     * Gets the cod_dpto value for this Request.
     * 
     * @return cod_dpto
     */
    public int getCod_dpto() {
        return cod_dpto;
    }


    /**
     * Sets the cod_dpto value for this Request.
     * 
     * @param cod_dpto
     */
    public void setCod_dpto(int cod_dpto) {
        this.cod_dpto = cod_dpto;
    }


    /**
     * Gets the cod_pais value for this Request.
     * 
     * @return cod_pais
     */
    public int getCod_pais() {
        return cod_pais;
    }


    /**
     * Sets the cod_pais value for this Request.
     * 
     * @param cod_pais
     */
    public void setCod_pais(int cod_pais) {
        this.cod_pais = cod_pais;
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
            this.cod_tipo_dir == other.getCod_tipo_dir() &&
            ((this.txt_direccion==null && other.getTxt_direccion()==null) || 
             (this.txt_direccion!=null &&
              this.txt_direccion.equals(other.getTxt_direccion()))) &&
            this.cod_municipio == other.getCod_municipio() &&
            this.cod_dpto == other.getCod_dpto() &&
            this.cod_pais == other.getCod_pais();
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
        _hashCode += getCod_tipo_dir();
        if (getTxt_direccion() != null) {
            _hashCode += getTxt_direccion().hashCode();
        }
        _hashCode += getCod_municipio();
        _hashCode += getCod_dpto();
        _hashCode += getCod_pais();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Request.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.equivida.com/services/clienteequivida/mpersona_dir", ">request"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id_persona");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.equivida.com/services/clienteequivida/mpersona_dir", "id_persona"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cod_tipo_dir");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.equivida.com/services/clienteequivida/mpersona_dir", "cod_tipo_dir"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("txt_direccion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.equivida.com/services/clienteequivida/mpersona_dir", "txt_direccion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cod_municipio");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.equivida.com/services/clienteequivida/mpersona_dir", "cod_municipio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cod_dpto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.equivida.com/services/clienteequivida/mpersona_dir", "cod_dpto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cod_pais");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.equivida.com/services/clienteequivida/mpersona_dir", "cod_pais"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
