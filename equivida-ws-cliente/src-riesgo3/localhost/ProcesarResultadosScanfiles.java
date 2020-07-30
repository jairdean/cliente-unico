/**
 * ProcesarResultadosScanfiles.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package localhost;

public class ProcesarResultadosScanfiles  implements java.io.Serializable {
    private java.lang.String idBusquedaSF;

    private java.lang.String licencia;

    private java.lang.String idPolitica;

    private java.lang.String usuario;

    private java.lang.String perfilUsuario;

    private int codigoError;

    private java.lang.String descError;

    public ProcesarResultadosScanfiles() {
    }

    public ProcesarResultadosScanfiles(
           java.lang.String idBusquedaSF,
           java.lang.String licencia,
           java.lang.String idPolitica,
           java.lang.String usuario,
           java.lang.String perfilUsuario,
           int codigoError,
           java.lang.String descError) {
           this.idBusquedaSF = idBusquedaSF;
           this.licencia = licencia;
           this.idPolitica = idPolitica;
           this.usuario = usuario;
           this.perfilUsuario = perfilUsuario;
           this.codigoError = codigoError;
           this.descError = descError;
    }


    /**
     * Gets the idBusquedaSF value for this ProcesarResultadosScanfiles.
     * 
     * @return idBusquedaSF
     */
    public java.lang.String getIdBusquedaSF() {
        return idBusquedaSF;
    }


    /**
     * Sets the idBusquedaSF value for this ProcesarResultadosScanfiles.
     * 
     * @param idBusquedaSF
     */
    public void setIdBusquedaSF(java.lang.String idBusquedaSF) {
        this.idBusquedaSF = idBusquedaSF;
    }


    /**
     * Gets the licencia value for this ProcesarResultadosScanfiles.
     * 
     * @return licencia
     */
    public java.lang.String getLicencia() {
        return licencia;
    }


    /**
     * Sets the licencia value for this ProcesarResultadosScanfiles.
     * 
     * @param licencia
     */
    public void setLicencia(java.lang.String licencia) {
        this.licencia = licencia;
    }


    /**
     * Gets the idPolitica value for this ProcesarResultadosScanfiles.
     * 
     * @return idPolitica
     */
    public java.lang.String getIdPolitica() {
        return idPolitica;
    }


    /**
     * Sets the idPolitica value for this ProcesarResultadosScanfiles.
     * 
     * @param idPolitica
     */
    public void setIdPolitica(java.lang.String idPolitica) {
        this.idPolitica = idPolitica;
    }


    /**
     * Gets the usuario value for this ProcesarResultadosScanfiles.
     * 
     * @return usuario
     */
    public java.lang.String getUsuario() {
        return usuario;
    }


    /**
     * Sets the usuario value for this ProcesarResultadosScanfiles.
     * 
     * @param usuario
     */
    public void setUsuario(java.lang.String usuario) {
        this.usuario = usuario;
    }


    /**
     * Gets the perfilUsuario value for this ProcesarResultadosScanfiles.
     * 
     * @return perfilUsuario
     */
    public java.lang.String getPerfilUsuario() {
        return perfilUsuario;
    }


    /**
     * Sets the perfilUsuario value for this ProcesarResultadosScanfiles.
     * 
     * @param perfilUsuario
     */
    public void setPerfilUsuario(java.lang.String perfilUsuario) {
        this.perfilUsuario = perfilUsuario;
    }


    /**
     * Gets the codigoError value for this ProcesarResultadosScanfiles.
     * 
     * @return codigoError
     */
    public int getCodigoError() {
        return codigoError;
    }


    /**
     * Sets the codigoError value for this ProcesarResultadosScanfiles.
     * 
     * @param codigoError
     */
    public void setCodigoError(int codigoError) {
        this.codigoError = codigoError;
    }


    /**
     * Gets the descError value for this ProcesarResultadosScanfiles.
     * 
     * @return descError
     */
    public java.lang.String getDescError() {
        return descError;
    }


    /**
     * Sets the descError value for this ProcesarResultadosScanfiles.
     * 
     * @param descError
     */
    public void setDescError(java.lang.String descError) {
        this.descError = descError;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ProcesarResultadosScanfiles)) return false;
        ProcesarResultadosScanfiles other = (ProcesarResultadosScanfiles) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.idBusquedaSF==null && other.getIdBusquedaSF()==null) || 
             (this.idBusquedaSF!=null &&
              this.idBusquedaSF.equals(other.getIdBusquedaSF()))) &&
            ((this.licencia==null && other.getLicencia()==null) || 
             (this.licencia!=null &&
              this.licencia.equals(other.getLicencia()))) &&
            ((this.idPolitica==null && other.getIdPolitica()==null) || 
             (this.idPolitica!=null &&
              this.idPolitica.equals(other.getIdPolitica()))) &&
            ((this.usuario==null && other.getUsuario()==null) || 
             (this.usuario!=null &&
              this.usuario.equals(other.getUsuario()))) &&
            ((this.perfilUsuario==null && other.getPerfilUsuario()==null) || 
             (this.perfilUsuario!=null &&
              this.perfilUsuario.equals(other.getPerfilUsuario()))) &&
            this.codigoError == other.getCodigoError() &&
            ((this.descError==null && other.getDescError()==null) || 
             (this.descError!=null &&
              this.descError.equals(other.getDescError())));
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
        if (getIdBusquedaSF() != null) {
            _hashCode += getIdBusquedaSF().hashCode();
        }
        if (getLicencia() != null) {
            _hashCode += getLicencia().hashCode();
        }
        if (getIdPolitica() != null) {
            _hashCode += getIdPolitica().hashCode();
        }
        if (getUsuario() != null) {
            _hashCode += getUsuario().hashCode();
        }
        if (getPerfilUsuario() != null) {
            _hashCode += getPerfilUsuario().hashCode();
        }
        _hashCode += getCodigoError();
        if (getDescError() != null) {
            _hashCode += getDescError().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ProcesarResultadosScanfiles.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://localhost/", ">ProcesarResultadosScanfiles"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idBusquedaSF");
        elemField.setXmlName(new javax.xml.namespace.QName("http://localhost/", "idBusquedaSF"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("licencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://localhost/", "licencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idPolitica");
        elemField.setXmlName(new javax.xml.namespace.QName("http://localhost/", "idPolitica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("usuario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://localhost/", "usuario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("perfilUsuario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://localhost/", "perfilUsuario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoError");
        elemField.setXmlName(new javax.xml.namespace.QName("http://localhost/", "codigoError"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descError");
        elemField.setXmlName(new javax.xml.namespace.QName("http://localhost/", "descError"));
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
