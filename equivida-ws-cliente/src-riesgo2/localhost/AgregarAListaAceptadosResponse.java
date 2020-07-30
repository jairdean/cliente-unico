/**
 * AgregarAListaAceptadosResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package localhost;

public class AgregarAListaAceptadosResponse  implements java.io.Serializable {
    private boolean agregarAListaAceptadosResult;

    private int codigoError;

    private java.lang.String descripcionError;

    public AgregarAListaAceptadosResponse() {
    }

    public AgregarAListaAceptadosResponse(
           boolean agregarAListaAceptadosResult,
           int codigoError,
           java.lang.String descripcionError) {
           this.agregarAListaAceptadosResult = agregarAListaAceptadosResult;
           this.codigoError = codigoError;
           this.descripcionError = descripcionError;
    }


    /**
     * Gets the agregarAListaAceptadosResult value for this AgregarAListaAceptadosResponse.
     * 
     * @return agregarAListaAceptadosResult
     */
    public boolean isAgregarAListaAceptadosResult() {
        return agregarAListaAceptadosResult;
    }


    /**
     * Sets the agregarAListaAceptadosResult value for this AgregarAListaAceptadosResponse.
     * 
     * @param agregarAListaAceptadosResult
     */
    public void setAgregarAListaAceptadosResult(boolean agregarAListaAceptadosResult) {
        this.agregarAListaAceptadosResult = agregarAListaAceptadosResult;
    }


    /**
     * Gets the codigoError value for this AgregarAListaAceptadosResponse.
     * 
     * @return codigoError
     */
    public int getCodigoError() {
        return codigoError;
    }


    /**
     * Sets the codigoError value for this AgregarAListaAceptadosResponse.
     * 
     * @param codigoError
     */
    public void setCodigoError(int codigoError) {
        this.codigoError = codigoError;
    }


    /**
     * Gets the descripcionError value for this AgregarAListaAceptadosResponse.
     * 
     * @return descripcionError
     */
    public java.lang.String getDescripcionError() {
        return descripcionError;
    }


    /**
     * Sets the descripcionError value for this AgregarAListaAceptadosResponse.
     * 
     * @param descripcionError
     */
    public void setDescripcionError(java.lang.String descripcionError) {
        this.descripcionError = descripcionError;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AgregarAListaAceptadosResponse)) return false;
        AgregarAListaAceptadosResponse other = (AgregarAListaAceptadosResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.agregarAListaAceptadosResult == other.isAgregarAListaAceptadosResult() &&
            this.codigoError == other.getCodigoError() &&
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
        _hashCode += (isAgregarAListaAceptadosResult() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += getCodigoError();
        if (getDescripcionError() != null) {
            _hashCode += getDescripcionError().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AgregarAListaAceptadosResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://localhost/", ">AgregarAListaAceptadosResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("agregarAListaAceptadosResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://localhost/", "AgregarAListaAceptadosResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoError");
        elemField.setXmlName(new javax.xml.namespace.QName("http://localhost/", "codigoError"));
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
