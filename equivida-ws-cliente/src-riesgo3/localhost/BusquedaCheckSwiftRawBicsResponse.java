/**
 * BusquedaCheckSwiftRawBicsResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package localhost;

public class BusquedaCheckSwiftRawBicsResponse  implements java.io.Serializable {
    private localhost.BusquedaCheckSwiftRawBicsResponseBusquedaCheckSwiftRawBicsResult busquedaCheckSwiftRawBicsResult;

    private java.lang.String primaryKey;

    private int codError;

    private java.lang.String descripcionError;

    public BusquedaCheckSwiftRawBicsResponse() {
    }

    public BusquedaCheckSwiftRawBicsResponse(
           localhost.BusquedaCheckSwiftRawBicsResponseBusquedaCheckSwiftRawBicsResult busquedaCheckSwiftRawBicsResult,
           java.lang.String primaryKey,
           int codError,
           java.lang.String descripcionError) {
           this.busquedaCheckSwiftRawBicsResult = busquedaCheckSwiftRawBicsResult;
           this.primaryKey = primaryKey;
           this.codError = codError;
           this.descripcionError = descripcionError;
    }


    /**
     * Gets the busquedaCheckSwiftRawBicsResult value for this BusquedaCheckSwiftRawBicsResponse.
     * 
     * @return busquedaCheckSwiftRawBicsResult
     */
    public localhost.BusquedaCheckSwiftRawBicsResponseBusquedaCheckSwiftRawBicsResult getBusquedaCheckSwiftRawBicsResult() {
        return busquedaCheckSwiftRawBicsResult;
    }


    /**
     * Sets the busquedaCheckSwiftRawBicsResult value for this BusquedaCheckSwiftRawBicsResponse.
     * 
     * @param busquedaCheckSwiftRawBicsResult
     */
    public void setBusquedaCheckSwiftRawBicsResult(localhost.BusquedaCheckSwiftRawBicsResponseBusquedaCheckSwiftRawBicsResult busquedaCheckSwiftRawBicsResult) {
        this.busquedaCheckSwiftRawBicsResult = busquedaCheckSwiftRawBicsResult;
    }


    /**
     * Gets the primaryKey value for this BusquedaCheckSwiftRawBicsResponse.
     * 
     * @return primaryKey
     */
    public java.lang.String getPrimaryKey() {
        return primaryKey;
    }


    /**
     * Sets the primaryKey value for this BusquedaCheckSwiftRawBicsResponse.
     * 
     * @param primaryKey
     */
    public void setPrimaryKey(java.lang.String primaryKey) {
        this.primaryKey = primaryKey;
    }


    /**
     * Gets the codError value for this BusquedaCheckSwiftRawBicsResponse.
     * 
     * @return codError
     */
    public int getCodError() {
        return codError;
    }


    /**
     * Sets the codError value for this BusquedaCheckSwiftRawBicsResponse.
     * 
     * @param codError
     */
    public void setCodError(int codError) {
        this.codError = codError;
    }


    /**
     * Gets the descripcionError value for this BusquedaCheckSwiftRawBicsResponse.
     * 
     * @return descripcionError
     */
    public java.lang.String getDescripcionError() {
        return descripcionError;
    }


    /**
     * Sets the descripcionError value for this BusquedaCheckSwiftRawBicsResponse.
     * 
     * @param descripcionError
     */
    public void setDescripcionError(java.lang.String descripcionError) {
        this.descripcionError = descripcionError;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BusquedaCheckSwiftRawBicsResponse)) return false;
        BusquedaCheckSwiftRawBicsResponse other = (BusquedaCheckSwiftRawBicsResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.busquedaCheckSwiftRawBicsResult==null && other.getBusquedaCheckSwiftRawBicsResult()==null) || 
             (this.busquedaCheckSwiftRawBicsResult!=null &&
              this.busquedaCheckSwiftRawBicsResult.equals(other.getBusquedaCheckSwiftRawBicsResult()))) &&
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
        if (getBusquedaCheckSwiftRawBicsResult() != null) {
            _hashCode += getBusquedaCheckSwiftRawBicsResult().hashCode();
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
        new org.apache.axis.description.TypeDesc(BusquedaCheckSwiftRawBicsResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://localhost/", ">BusquedaCheckSwiftRawBicsResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("busquedaCheckSwiftRawBicsResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://localhost/", "BusquedaCheckSwiftRawBicsResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://localhost/", ">>BusquedaCheckSwiftRawBicsResponse>BusquedaCheckSwiftRawBicsResult"));
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
