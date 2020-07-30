/**
 * Request.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.equivida.www.services.clienteequivida.mpersona;

public class Request  implements java.io.Serializable {
    private java.lang.String txt_apellido1;

    private java.lang.String txt_apellido2;

    private java.lang.String txt_nombre;

    private int cod_tipo_doc;

    private java.lang.String nro_doc;

    private int cod_tipo_iva;

    private java.lang.String nro_nit;

    private java.util.Date fec_nac;

    private java.lang.String txt_lugar_nac;

    private java.lang.String txt_sexo;

    private int cod_est_civil;

    private java.lang.String cod_tipo_persona;

    private java.lang.String txt_origen;

    public Request() {
    }

    public Request(
           java.lang.String txt_apellido1,
           java.lang.String txt_apellido2,
           java.lang.String txt_nombre,
           int cod_tipo_doc,
           java.lang.String nro_doc,
           int cod_tipo_iva,
           java.lang.String nro_nit,
           java.util.Date fec_nac,
           java.lang.String txt_lugar_nac,
           java.lang.String txt_sexo,
           int cod_est_civil,
           java.lang.String cod_tipo_persona,
           java.lang.String txt_origen) {
           this.txt_apellido1 = txt_apellido1;
           this.txt_apellido2 = txt_apellido2;
           this.txt_nombre = txt_nombre;
           this.cod_tipo_doc = cod_tipo_doc;
           this.nro_doc = nro_doc;
           this.cod_tipo_iva = cod_tipo_iva;
           this.nro_nit = nro_nit;
           this.fec_nac = fec_nac;
           this.txt_lugar_nac = txt_lugar_nac;
           this.txt_sexo = txt_sexo;
           this.cod_est_civil = cod_est_civil;
           this.cod_tipo_persona = cod_tipo_persona;
           this.txt_origen = txt_origen;
    }


    /**
     * Gets the txt_apellido1 value for this Request.
     * 
     * @return txt_apellido1
     */
    public java.lang.String getTxt_apellido1() {
        return txt_apellido1;
    }


    /**
     * Sets the txt_apellido1 value for this Request.
     * 
     * @param txt_apellido1
     */
    public void setTxt_apellido1(java.lang.String txt_apellido1) {
        this.txt_apellido1 = txt_apellido1;
    }


    /**
     * Gets the txt_apellido2 value for this Request.
     * 
     * @return txt_apellido2
     */
    public java.lang.String getTxt_apellido2() {
        return txt_apellido2;
    }


    /**
     * Sets the txt_apellido2 value for this Request.
     * 
     * @param txt_apellido2
     */
    public void setTxt_apellido2(java.lang.String txt_apellido2) {
        this.txt_apellido2 = txt_apellido2;
    }


    /**
     * Gets the txt_nombre value for this Request.
     * 
     * @return txt_nombre
     */
    public java.lang.String getTxt_nombre() {
        return txt_nombre;
    }


    /**
     * Sets the txt_nombre value for this Request.
     * 
     * @param txt_nombre
     */
    public void setTxt_nombre(java.lang.String txt_nombre) {
        this.txt_nombre = txt_nombre;
    }


    /**
     * Gets the cod_tipo_doc value for this Request.
     * 
     * @return cod_tipo_doc
     */
    public int getCod_tipo_doc() {
        return cod_tipo_doc;
    }


    /**
     * Sets the cod_tipo_doc value for this Request.
     * 
     * @param cod_tipo_doc
     */
    public void setCod_tipo_doc(int cod_tipo_doc) {
        this.cod_tipo_doc = cod_tipo_doc;
    }


    /**
     * Gets the nro_doc value for this Request.
     * 
     * @return nro_doc
     */
    public java.lang.String getNro_doc() {
        return nro_doc;
    }


    /**
     * Sets the nro_doc value for this Request.
     * 
     * @param nro_doc
     */
    public void setNro_doc(java.lang.String nro_doc) {
        this.nro_doc = nro_doc;
    }


    /**
     * Gets the cod_tipo_iva value for this Request.
     * 
     * @return cod_tipo_iva
     */
    public int getCod_tipo_iva() {
        return cod_tipo_iva;
    }


    /**
     * Sets the cod_tipo_iva value for this Request.
     * 
     * @param cod_tipo_iva
     */
    public void setCod_tipo_iva(int cod_tipo_iva) {
        this.cod_tipo_iva = cod_tipo_iva;
    }


    /**
     * Gets the nro_nit value for this Request.
     * 
     * @return nro_nit
     */
    public java.lang.String getNro_nit() {
        return nro_nit;
    }


    /**
     * Sets the nro_nit value for this Request.
     * 
     * @param nro_nit
     */
    public void setNro_nit(java.lang.String nro_nit) {
        this.nro_nit = nro_nit;
    }


    /**
     * Gets the fec_nac value for this Request.
     * 
     * @return fec_nac
     */
    public java.util.Date getFec_nac() {
        return fec_nac;
    }


    /**
     * Sets the fec_nac value for this Request.
     * 
     * @param fec_nac
     */
    public void setFec_nac(java.util.Date fec_nac) {
        this.fec_nac = fec_nac;
    }


    /**
     * Gets the txt_lugar_nac value for this Request.
     * 
     * @return txt_lugar_nac
     */
    public java.lang.String getTxt_lugar_nac() {
        return txt_lugar_nac;
    }


    /**
     * Sets the txt_lugar_nac value for this Request.
     * 
     * @param txt_lugar_nac
     */
    public void setTxt_lugar_nac(java.lang.String txt_lugar_nac) {
        this.txt_lugar_nac = txt_lugar_nac;
    }


    /**
     * Gets the txt_sexo value for this Request.
     * 
     * @return txt_sexo
     */
    public java.lang.String getTxt_sexo() {
        return txt_sexo;
    }


    /**
     * Sets the txt_sexo value for this Request.
     * 
     * @param txt_sexo
     */
    public void setTxt_sexo(java.lang.String txt_sexo) {
        this.txt_sexo = txt_sexo;
    }


    /**
     * Gets the cod_est_civil value for this Request.
     * 
     * @return cod_est_civil
     */
    public int getCod_est_civil() {
        return cod_est_civil;
    }


    /**
     * Sets the cod_est_civil value for this Request.
     * 
     * @param cod_est_civil
     */
    public void setCod_est_civil(int cod_est_civil) {
        this.cod_est_civil = cod_est_civil;
    }


    /**
     * Gets the cod_tipo_persona value for this Request.
     * 
     * @return cod_tipo_persona
     */
    public java.lang.String getCod_tipo_persona() {
        return cod_tipo_persona;
    }


    /**
     * Sets the cod_tipo_persona value for this Request.
     * 
     * @param cod_tipo_persona
     */
    public void setCod_tipo_persona(java.lang.String cod_tipo_persona) {
        this.cod_tipo_persona = cod_tipo_persona;
    }


    /**
     * Gets the txt_origen value for this Request.
     * 
     * @return txt_origen
     */
    public java.lang.String getTxt_origen() {
        return txt_origen;
    }


    /**
     * Sets the txt_origen value for this Request.
     * 
     * @param txt_origen
     */
    public void setTxt_origen(java.lang.String txt_origen) {
        this.txt_origen = txt_origen;
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
            ((this.txt_apellido1==null && other.getTxt_apellido1()==null) || 
             (this.txt_apellido1!=null &&
              this.txt_apellido1.equals(other.getTxt_apellido1()))) &&
            ((this.txt_apellido2==null && other.getTxt_apellido2()==null) || 
             (this.txt_apellido2!=null &&
              this.txt_apellido2.equals(other.getTxt_apellido2()))) &&
            ((this.txt_nombre==null && other.getTxt_nombre()==null) || 
             (this.txt_nombre!=null &&
              this.txt_nombre.equals(other.getTxt_nombre()))) &&
            this.cod_tipo_doc == other.getCod_tipo_doc() &&
            ((this.nro_doc==null && other.getNro_doc()==null) || 
             (this.nro_doc!=null &&
              this.nro_doc.equals(other.getNro_doc()))) &&
            this.cod_tipo_iva == other.getCod_tipo_iva() &&
            ((this.nro_nit==null && other.getNro_nit()==null) || 
             (this.nro_nit!=null &&
              this.nro_nit.equals(other.getNro_nit()))) &&
            ((this.fec_nac==null && other.getFec_nac()==null) || 
             (this.fec_nac!=null &&
              this.fec_nac.equals(other.getFec_nac()))) &&
            ((this.txt_lugar_nac==null && other.getTxt_lugar_nac()==null) || 
             (this.txt_lugar_nac!=null &&
              this.txt_lugar_nac.equals(other.getTxt_lugar_nac()))) &&
            ((this.txt_sexo==null && other.getTxt_sexo()==null) || 
             (this.txt_sexo!=null &&
              this.txt_sexo.equals(other.getTxt_sexo()))) &&
            this.cod_est_civil == other.getCod_est_civil() &&
            ((this.cod_tipo_persona==null && other.getCod_tipo_persona()==null) || 
             (this.cod_tipo_persona!=null &&
              this.cod_tipo_persona.equals(other.getCod_tipo_persona()))) &&
            ((this.txt_origen==null && other.getTxt_origen()==null) || 
             (this.txt_origen!=null &&
              this.txt_origen.equals(other.getTxt_origen())));
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
        if (getTxt_apellido1() != null) {
            _hashCode += getTxt_apellido1().hashCode();
        }
        if (getTxt_apellido2() != null) {
            _hashCode += getTxt_apellido2().hashCode();
        }
        if (getTxt_nombre() != null) {
            _hashCode += getTxt_nombre().hashCode();
        }
        _hashCode += getCod_tipo_doc();
        if (getNro_doc() != null) {
            _hashCode += getNro_doc().hashCode();
        }
        _hashCode += getCod_tipo_iva();
        if (getNro_nit() != null) {
            _hashCode += getNro_nit().hashCode();
        }
        if (getFec_nac() != null) {
            _hashCode += getFec_nac().hashCode();
        }
        if (getTxt_lugar_nac() != null) {
            _hashCode += getTxt_lugar_nac().hashCode();
        }
        if (getTxt_sexo() != null) {
            _hashCode += getTxt_sexo().hashCode();
        }
        _hashCode += getCod_est_civil();
        if (getCod_tipo_persona() != null) {
            _hashCode += getCod_tipo_persona().hashCode();
        }
        if (getTxt_origen() != null) {
            _hashCode += getTxt_origen().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Request.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.equivida.com/services/clienteequivida/mpersona", ">request"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("txt_apellido1");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.equivida.com/services/clienteequivida/mpersona", "txt_apellido1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("txt_apellido2");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.equivida.com/services/clienteequivida/mpersona", "txt_apellido2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("txt_nombre");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.equivida.com/services/clienteequivida/mpersona", "txt_nombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cod_tipo_doc");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.equivida.com/services/clienteequivida/mpersona", "cod_tipo_doc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nro_doc");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.equivida.com/services/clienteequivida/mpersona", "nro_doc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cod_tipo_iva");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.equivida.com/services/clienteequivida/mpersona", "cod_tipo_iva"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nro_nit");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.equivida.com/services/clienteequivida/mpersona", "nro_nit"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fec_nac");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.equivida.com/services/clienteequivida/mpersona", "fec_nac"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("txt_lugar_nac");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.equivida.com/services/clienteequivida/mpersona", "txt_lugar_nac"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("txt_sexo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.equivida.com/services/clienteequivida/mpersona", "txt_sexo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cod_est_civil");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.equivida.com/services/clienteequivida/mpersona", "cod_est_civil"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cod_tipo_persona");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.equivida.com/services/clienteequivida/mpersona", "cod_tipo_persona"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("txt_origen");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.equivida.com/services/clienteequivida/mpersona", "txt_origen"));
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
