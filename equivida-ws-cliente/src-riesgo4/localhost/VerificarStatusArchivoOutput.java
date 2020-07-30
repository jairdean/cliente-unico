/**
 * VerificarStatusArchivoOutput.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package localhost;

public class VerificarStatusArchivoOutput  implements java.io.Serializable {
    private java.lang.String idBusquedaSF;

    private int codError;

    private java.lang.String descError;

    public VerificarStatusArchivoOutput() {
    }

    public VerificarStatusArchivoOutput(
           java.lang.String idBusquedaSF,
           int codError,
           java.lang.String descError) {
           this.idBusquedaSF = idBusquedaSF;
           this.codError = codError;
           this.descError = descError;
    }


    /**
     * Gets the idBusquedaSF value for this VerificarStatusArchivoOutput.
     * 
     * @return idBusquedaSF
     */
    public java.lang.String getIdBusquedaSF() {
        return idBusquedaSF;
    }


    /**
     * Sets the idBusquedaSF value for this VerificarStatusArchivoOutput.
     * 
     * @param idBusquedaSF
     */
    public void setIdBusquedaSF(java.lang.String idBusquedaSF) {
        this.idBusquedaSF = idBusquedaSF;
    }


    /**
     * Gets the codError value for this VerificarStatusArchivoOutput.
     * 
     * @return codError
     */
    public int getCodError() {
        return codError;
    }


    /**
     * Sets the codError value for this VerificarStatusArchivoOutput.
     * 
     * @param codError
     */
    public void setCodError(int codError) {
        this.codError = codError;
    }


    /**
     * Gets the descError value for this VerificarStatusArchivoOutput.
     * 
     * @return descError
     */
    public java.lang.String getDescError() {
        return descError;
    }


    /**
     * Sets the descError value for this VerificarStatusArchivoOutput.
     * 
     * @param descError
     */
    public void setDescError(java.lang.String descError) {
        this.descError = descError;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof VerificarStatusArchivoOutput)) return false;
        VerificarStatusArchivoOutput other = (VerificarStatusArchivoOutput) obj;
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
            this.codError == other.getCodError() &&
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
        _hashCode += getCodError();
        if (getDescError() != null) {
            _hashCode += getDescError().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(VerificarStatusArchivoOutput.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://localhost/", ">VerificarStatusArchivoOutput"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idBusquedaSF");
        elemField.setXmlName(new javax.xml.namespace.QName("http://localhost/", "idBusquedaSF"));
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
