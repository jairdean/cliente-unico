/**
 * Request.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.equivida.www.services.clienteequivida.maseg_header;

public class Request  implements java.io.Serializable {
    private int cod_aseg;

    private int id_persona;

    private int cod_figura_aseg;

    private int cod_tipo_aseg;

    private int cod_imp_aseg;

    private int cod_tipo_agente;

    private int cod_agente;

    private java.util.Date fec_alta;

    private java.util.Date fec_baja;

    private int cod_ocupacion;

    private java.util.Date fec_ult_modif;

    private java.lang.String cod_usuario;

    private java.lang.String txt_nom_factura;

    private int cod_moneda;

    private java.math.BigDecimal imp_sueldo;

    public Request() {
    }

    public Request(
           int cod_aseg,
           int id_persona,
           int cod_figura_aseg,
           int cod_tipo_aseg,
           int cod_imp_aseg,
           int cod_tipo_agente,
           int cod_agente,
           java.util.Date fec_alta,
           java.util.Date fec_baja,
           int cod_ocupacion,
           java.util.Date fec_ult_modif,
           java.lang.String cod_usuario,
           java.lang.String txt_nom_factura,
           int cod_moneda,
           java.math.BigDecimal imp_sueldo) {
           this.cod_aseg = cod_aseg;
           this.id_persona = id_persona;
           this.cod_figura_aseg = cod_figura_aseg;
           this.cod_tipo_aseg = cod_tipo_aseg;
           this.cod_imp_aseg = cod_imp_aseg;
           this.cod_tipo_agente = cod_tipo_agente;
           this.cod_agente = cod_agente;
           this.fec_alta = fec_alta;
           this.fec_baja = fec_baja;
           this.cod_ocupacion = cod_ocupacion;
           this.fec_ult_modif = fec_ult_modif;
           this.cod_usuario = cod_usuario;
           this.txt_nom_factura = txt_nom_factura;
           this.cod_moneda = cod_moneda;
           this.imp_sueldo = imp_sueldo;
    }


    /**
     * Gets the cod_aseg value for this Request.
     * 
     * @return cod_aseg
     */
    public int getCod_aseg() {
        return cod_aseg;
    }


    /**
     * Sets the cod_aseg value for this Request.
     * 
     * @param cod_aseg
     */
    public void setCod_aseg(int cod_aseg) {
        this.cod_aseg = cod_aseg;
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
     * Gets the cod_figura_aseg value for this Request.
     * 
     * @return cod_figura_aseg
     */
    public int getCod_figura_aseg() {
        return cod_figura_aseg;
    }


    /**
     * Sets the cod_figura_aseg value for this Request.
     * 
     * @param cod_figura_aseg
     */
    public void setCod_figura_aseg(int cod_figura_aseg) {
        this.cod_figura_aseg = cod_figura_aseg;
    }


    /**
     * Gets the cod_tipo_aseg value for this Request.
     * 
     * @return cod_tipo_aseg
     */
    public int getCod_tipo_aseg() {
        return cod_tipo_aseg;
    }


    /**
     * Sets the cod_tipo_aseg value for this Request.
     * 
     * @param cod_tipo_aseg
     */
    public void setCod_tipo_aseg(int cod_tipo_aseg) {
        this.cod_tipo_aseg = cod_tipo_aseg;
    }


    /**
     * Gets the cod_imp_aseg value for this Request.
     * 
     * @return cod_imp_aseg
     */
    public int getCod_imp_aseg() {
        return cod_imp_aseg;
    }


    /**
     * Sets the cod_imp_aseg value for this Request.
     * 
     * @param cod_imp_aseg
     */
    public void setCod_imp_aseg(int cod_imp_aseg) {
        this.cod_imp_aseg = cod_imp_aseg;
    }


    /**
     * Gets the cod_tipo_agente value for this Request.
     * 
     * @return cod_tipo_agente
     */
    public int getCod_tipo_agente() {
        return cod_tipo_agente;
    }


    /**
     * Sets the cod_tipo_agente value for this Request.
     * 
     * @param cod_tipo_agente
     */
    public void setCod_tipo_agente(int cod_tipo_agente) {
        this.cod_tipo_agente = cod_tipo_agente;
    }


    /**
     * Gets the cod_agente value for this Request.
     * 
     * @return cod_agente
     */
    public int getCod_agente() {
        return cod_agente;
    }


    /**
     * Sets the cod_agente value for this Request.
     * 
     * @param cod_agente
     */
    public void setCod_agente(int cod_agente) {
        this.cod_agente = cod_agente;
    }


    /**
     * Gets the fec_alta value for this Request.
     * 
     * @return fec_alta
     */
    public java.util.Date getFec_alta() {
        return fec_alta;
    }


    /**
     * Sets the fec_alta value for this Request.
     * 
     * @param fec_alta
     */
    public void setFec_alta(java.util.Date fec_alta) {
        this.fec_alta = fec_alta;
    }


    /**
     * Gets the fec_baja value for this Request.
     * 
     * @return fec_baja
     */
    public java.util.Date getFec_baja() {
        return fec_baja;
    }


    /**
     * Sets the fec_baja value for this Request.
     * 
     * @param fec_baja
     */
    public void setFec_baja(java.util.Date fec_baja) {
        this.fec_baja = fec_baja;
    }


    /**
     * Gets the cod_ocupacion value for this Request.
     * 
     * @return cod_ocupacion
     */
    public int getCod_ocupacion() {
        return cod_ocupacion;
    }


    /**
     * Sets the cod_ocupacion value for this Request.
     * 
     * @param cod_ocupacion
     */
    public void setCod_ocupacion(int cod_ocupacion) {
        this.cod_ocupacion = cod_ocupacion;
    }


    /**
     * Gets the fec_ult_modif value for this Request.
     * 
     * @return fec_ult_modif
     */
    public java.util.Date getFec_ult_modif() {
        return fec_ult_modif;
    }


    /**
     * Sets the fec_ult_modif value for this Request.
     * 
     * @param fec_ult_modif
     */
    public void setFec_ult_modif(java.util.Date fec_ult_modif) {
        this.fec_ult_modif = fec_ult_modif;
    }


    /**
     * Gets the cod_usuario value for this Request.
     * 
     * @return cod_usuario
     */
    public java.lang.String getCod_usuario() {
        return cod_usuario;
    }


    /**
     * Sets the cod_usuario value for this Request.
     * 
     * @param cod_usuario
     */
    public void setCod_usuario(java.lang.String cod_usuario) {
        this.cod_usuario = cod_usuario;
    }


    /**
     * Gets the txt_nom_factura value for this Request.
     * 
     * @return txt_nom_factura
     */
    public java.lang.String getTxt_nom_factura() {
        return txt_nom_factura;
    }


    /**
     * Sets the txt_nom_factura value for this Request.
     * 
     * @param txt_nom_factura
     */
    public void setTxt_nom_factura(java.lang.String txt_nom_factura) {
        this.txt_nom_factura = txt_nom_factura;
    }


    /**
     * Gets the cod_moneda value for this Request.
     * 
     * @return cod_moneda
     */
    public int getCod_moneda() {
        return cod_moneda;
    }


    /**
     * Sets the cod_moneda value for this Request.
     * 
     * @param cod_moneda
     */
    public void setCod_moneda(int cod_moneda) {
        this.cod_moneda = cod_moneda;
    }


    /**
     * Gets the imp_sueldo value for this Request.
     * 
     * @return imp_sueldo
     */
    public java.math.BigDecimal getImp_sueldo() {
        return imp_sueldo;
    }


    /**
     * Sets the imp_sueldo value for this Request.
     * 
     * @param imp_sueldo
     */
    public void setImp_sueldo(java.math.BigDecimal imp_sueldo) {
        this.imp_sueldo = imp_sueldo;
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
            this.cod_aseg == other.getCod_aseg() &&
            this.id_persona == other.getId_persona() &&
            this.cod_figura_aseg == other.getCod_figura_aseg() &&
            this.cod_tipo_aseg == other.getCod_tipo_aseg() &&
            this.cod_imp_aseg == other.getCod_imp_aseg() &&
            this.cod_tipo_agente == other.getCod_tipo_agente() &&
            this.cod_agente == other.getCod_agente() &&
            ((this.fec_alta==null && other.getFec_alta()==null) || 
             (this.fec_alta!=null &&
              this.fec_alta.equals(other.getFec_alta()))) &&
            ((this.fec_baja==null && other.getFec_baja()==null) || 
             (this.fec_baja!=null &&
              this.fec_baja.equals(other.getFec_baja()))) &&
            this.cod_ocupacion == other.getCod_ocupacion() &&
            ((this.fec_ult_modif==null && other.getFec_ult_modif()==null) || 
             (this.fec_ult_modif!=null &&
              this.fec_ult_modif.equals(other.getFec_ult_modif()))) &&
            ((this.cod_usuario==null && other.getCod_usuario()==null) || 
             (this.cod_usuario!=null &&
              this.cod_usuario.equals(other.getCod_usuario()))) &&
            ((this.txt_nom_factura==null && other.getTxt_nom_factura()==null) || 
             (this.txt_nom_factura!=null &&
              this.txt_nom_factura.equals(other.getTxt_nom_factura()))) &&
            this.cod_moneda == other.getCod_moneda() &&
            ((this.imp_sueldo==null && other.getImp_sueldo()==null) || 
             (this.imp_sueldo!=null &&
              this.imp_sueldo.equals(other.getImp_sueldo())));
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
        _hashCode += getCod_aseg();
        _hashCode += getId_persona();
        _hashCode += getCod_figura_aseg();
        _hashCode += getCod_tipo_aseg();
        _hashCode += getCod_imp_aseg();
        _hashCode += getCod_tipo_agente();
        _hashCode += getCod_agente();
        if (getFec_alta() != null) {
            _hashCode += getFec_alta().hashCode();
        }
        if (getFec_baja() != null) {
            _hashCode += getFec_baja().hashCode();
        }
        _hashCode += getCod_ocupacion();
        if (getFec_ult_modif() != null) {
            _hashCode += getFec_ult_modif().hashCode();
        }
        if (getCod_usuario() != null) {
            _hashCode += getCod_usuario().hashCode();
        }
        if (getTxt_nom_factura() != null) {
            _hashCode += getTxt_nom_factura().hashCode();
        }
        _hashCode += getCod_moneda();
        if (getImp_sueldo() != null) {
            _hashCode += getImp_sueldo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Request.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.equivida.com/services/clienteequivida/maseg_header", ">request"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cod_aseg");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.equivida.com/services/clienteequivida/maseg_header", "cod_aseg"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id_persona");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.equivida.com/services/clienteequivida/maseg_header", "id_persona"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cod_figura_aseg");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.equivida.com/services/clienteequivida/maseg_header", "cod_figura_aseg"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cod_tipo_aseg");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.equivida.com/services/clienteequivida/maseg_header", "cod_tipo_aseg"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cod_imp_aseg");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.equivida.com/services/clienteequivida/maseg_header", "cod_imp_aseg"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cod_tipo_agente");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.equivida.com/services/clienteequivida/maseg_header", "cod_tipo_agente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cod_agente");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.equivida.com/services/clienteequivida/maseg_header", "cod_agente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fec_alta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.equivida.com/services/clienteequivida/maseg_header", "fec_alta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fec_baja");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.equivida.com/services/clienteequivida/maseg_header", "fec_baja"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cod_ocupacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.equivida.com/services/clienteequivida/maseg_header", "cod_ocupacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fec_ult_modif");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.equivida.com/services/clienteequivida/maseg_header", "fec_ult_modif"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cod_usuario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.equivida.com/services/clienteequivida/maseg_header", "cod_usuario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("txt_nom_factura");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.equivida.com/services/clienteequivida/maseg_header", "txt_nom_factura"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cod_moneda");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.equivida.com/services/clienteequivida/maseg_header", "cod_moneda"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("imp_sueldo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.equivida.com/services/clienteequivida/maseg_header", "imp_sueldo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
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
