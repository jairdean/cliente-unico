/**
 * BusquedaWire.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package localhost;

public class BusquedaWire  implements java.io.Serializable {
    private java.lang.String licenciaEmpresa;

    private java.lang.String contextoWire;

    private java.lang.String separador;

    private java.lang.String idPolitica;

    private java.lang.String userName;

    private java.lang.String perfilUsuario;

    private java.lang.String primaryKey;

    private int codError;

    private java.lang.String descripcionError;

    public BusquedaWire() {
    }

    public BusquedaWire(
           java.lang.String licenciaEmpresa,
           java.lang.String contextoWire,
           java.lang.String separador,
           java.lang.String idPolitica,
           java.lang.String userName,
           java.lang.String perfilUsuario,
           java.lang.String primaryKey,
           int codError,
           java.lang.String descripcionError) {
           this.licenciaEmpresa = licenciaEmpresa;
           this.contextoWire = contextoWire;
           this.separador = separador;
           this.idPolitica = idPolitica;
           this.userName = userName;
           this.perfilUsuario = perfilUsuario;
           this.primaryKey = primaryKey;
           this.codError = codError;
           this.descripcionError = descripcionError;
    }


    /**
     * Gets the licenciaEmpresa value for this BusquedaWire.
     * 
     * @return licenciaEmpresa
     */
    public java.lang.String getLicenciaEmpresa() {
        return licenciaEmpresa;
    }


    /**
     * Sets the licenciaEmpresa value for this BusquedaWire.
     * 
     * @param licenciaEmpresa
     */
    public void setLicenciaEmpresa(java.lang.String licenciaEmpresa) {
        this.licenciaEmpresa = licenciaEmpresa;
    }


    /**
     * Gets the contextoWire value for this BusquedaWire.
     * 
     * @return contextoWire
     */
    public java.lang.String getContextoWire() {
        return contextoWire;
    }


    /**
     * Sets the contextoWire value for this BusquedaWire.
     * 
     * @param contextoWire
     */
    public void setContextoWire(java.lang.String contextoWire) {
        this.contextoWire = contextoWire;
    }


    /**
     * Gets the separador value for this BusquedaWire.
     * 
     * @return separador
     */
    public java.lang.String getSeparador() {
        return separador;
    }


    /**
     * Sets the separador value for this BusquedaWire.
     * 
     * @param separador
     */
    public void setSeparador(java.lang.String separador) {
        this.separador = separador;
    }


    /**
     * Gets the idPolitica value for this BusquedaWire.
     * 
     * @return idPolitica
     */
    public java.lang.String getIdPolitica() {
        return idPolitica;
    }


    /**
     * Sets the idPolitica value for this BusquedaWire.
     * 
     * @param idPolitica
     */
    public void setIdPolitica(java.lang.String idPolitica) {
        this.idPolitica = idPolitica;
    }


    /**
     * Gets the userName value for this BusquedaWire.
     * 
     * @return userName
     */
    public java.lang.String getUserName() {
        return userName;
    }


    /**
     * Sets the userName value for this BusquedaWire.
     * 
     * @param userName
     */
    public void setUserName(java.lang.String userName) {
        this.userName = userName;
    }


    /**
     * Gets the perfilUsuario value for this BusquedaWire.
     * 
     * @return perfilUsuario
     */
    public java.lang.String getPerfilUsuario() {
        return perfilUsuario;
    }


    /**
     * Sets the perfilUsuario value for this BusquedaWire.
     * 
     * @param perfilUsuario
     */
    public void setPerfilUsuario(java.lang.String perfilUsuario) {
        this.perfilUsuario = perfilUsuario;
    }


    /**
     * Gets the primaryKey value for this BusquedaWire.
     * 
     * @return primaryKey
     */
    public java.lang.String getPrimaryKey() {
        return primaryKey;
    }


    /**
     * Sets the primaryKey value for this BusquedaWire.
     * 
     * @param primaryKey
     */
    public void setPrimaryKey(java.lang.String primaryKey) {
        this.primaryKey = primaryKey;
    }


    /**
     * Gets the codError value for this BusquedaWire.
     * 
     * @return codError
     */
    public int getCodError() {
        return codError;
    }


    /**
     * Sets the codError value for this BusquedaWire.
     * 
     * @param codError
     */
    public void setCodError(int codError) {
        this.codError = codError;
    }


    /**
     * Gets the descripcionError value for this BusquedaWire.
     * 
     * @return descripcionError
     */
    public java.lang.String getDescripcionError() {
        return descripcionError;
    }


    /**
     * Sets the descripcionError value for this BusquedaWire.
     * 
     * @param descripcionError
     */
    public void setDescripcionError(java.lang.String descripcionError) {
        this.descripcionError = descripcionError;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BusquedaWire)) return false;
        BusquedaWire other = (BusquedaWire) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.licenciaEmpresa==null && other.getLicenciaEmpresa()==null) || 
             (this.licenciaEmpresa!=null &&
              this.licenciaEmpresa.equals(other.getLicenciaEmpresa()))) &&
            ((this.contextoWire==null && other.getContextoWire()==null) || 
             (this.contextoWire!=null &&
              this.contextoWire.equals(other.getContextoWire()))) &&
            ((this.separador==null && other.getSeparador()==null) || 
             (this.separador!=null &&
              this.separador.equals(other.getSeparador()))) &&
            ((this.idPolitica==null && other.getIdPolitica()==null) || 
             (this.idPolitica!=null &&
              this.idPolitica.equals(other.getIdPolitica()))) &&
            ((this.userName==null && other.getUserName()==null) || 
             (this.userName!=null &&
              this.userName.equals(other.getUserName()))) &&
            ((this.perfilUsuario==null && other.getPerfilUsuario()==null) || 
             (this.perfilUsuario!=null &&
              this.perfilUsuario.equals(other.getPerfilUsuario()))) &&
            ((this.primaryKey==null && other.getPrimaryKey()==null) || 
             (this.primaryKey!=null &&
              this.primaryKey.equals(other.getPrimaryKey()))) &&
            this.codError == other.getCodError() &&
            ((this.descripcionError==null && other.getDescripcionError()==null) || 
             (this.descripcionError!=null &&
              this.descripcionError.equals(other.getDescripcionError())));
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
        if (getLicenciaEmpresa() != null) {
            _hashCode += getLicenciaEmpresa().hashCode();
        }
        if (getContextoWire() != null) {
            _hashCode += getContextoWire().hashCode();
        }
        if (getSeparador() != null) {
            _hashCode += getSeparador().hashCode();
        }
        if (getIdPolitica() != null) {
            _hashCode += getIdPolitica().hashCode();
        }
        if (getUserName() != null) {
            _hashCode += getUserName().hashCode();
        }
        if (getPerfilUsuario() != null) {
            _hashCode += getPerfilUsuario().hashCode();
        }
        if (getPrimaryKey() != null) {
            _hashCode += getPrimaryKey().hashCode();
        }
        _hashCode += getCodError();
        if (getDescripcionError() != null) {
            _hashCode += getDescripcionError().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BusquedaWire.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://localhost/", ">BusquedaWire"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("licenciaEmpresa");
        elemField.setXmlName(new javax.xml.namespace.QName("http://localhost/", "licenciaEmpresa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contextoWire");
        elemField.setXmlName(new javax.xml.namespace.QName("http://localhost/", "contextoWire"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("separador");
        elemField.setXmlName(new javax.xml.namespace.QName("http://localhost/", "separador"));
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
        elemField.setFieldName("userName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://localhost/", "userName"));
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
        elemField.setFieldName("primaryKey");
        elemField.setXmlName(new javax.xml.namespace.QName("http://localhost/", "primaryKey"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codError");
        elemField.setXmlName(new javax.xml.namespace.QName("http://localhost/", "codError"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descripcionError");
        elemField.setXmlName(new javax.xml.namespace.QName("http://localhost/", "descripcionError"));
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
