/**
 * BusquedaCheckSwiftRawBicsResulResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package localhost;

public class BusquedaCheckSwiftRawBicsResulResponse  implements java.io.Serializable {
    private boolean busquedaCheckSwiftRawBicsResulResult;

    private java.lang.String primaryKey;

    private int numResult;

    private int codError;

    private java.lang.String descripcionError;

    public BusquedaCheckSwiftRawBicsResulResponse() {
    }

    public BusquedaCheckSwiftRawBicsResulResponse(
           boolean busquedaCheckSwiftRawBicsResulResult,
           java.lang.String primaryKey,
           int numResult,
           int codError,
           java.lang.String descripcionError) {
           this.busquedaCheckSwiftRawBicsResulResult = busquedaCheckSwiftRawBicsResulResult;
           this.primaryKey = primaryKey;
           this.numResult = numResult;
           this.codError = codError;
           this.descripcionError = descripcionError;
    }


    /**
     * Gets the busquedaCheckSwiftRawBicsResulResult value for this BusquedaCheckSwiftRawBicsResulResponse.
     * 
     * @return busquedaCheckSwiftRawBicsResulResult
     */
    public boolean isBusquedaCheckSwiftRawBicsResulResult() {
        return busquedaCheckSwiftRawBicsResulResult;
    }


    /**
     * Sets the busquedaCheckSwiftRawBicsResulResult value for this BusquedaCheckSwiftRawBicsResulResponse.
     * 
     * @param busquedaCheckSwiftRawBicsResulResult
     */
    public void setBusquedaCheckSwiftRawBicsResulResult(boolean busquedaCheckSwiftRawBicsResulResult) {
        this.busquedaCheckSwiftRawBicsResulResult = busquedaCheckSwiftRawBicsResulResult;
    }


    /**
     * Gets the primaryKey value for this BusquedaCheckSwiftRawBicsResulResponse.
     * 
     * @return primaryKey
     */
    public java.lang.String getPrimaryKey() {
        return primaryKey;
    }


    /**
     * Sets the primaryKey value for this BusquedaCheckSwiftRawBicsResulResponse.
     * 
     * @param primaryKey
     */
    public void setPrimaryKey(java.lang.String primaryKey) {
        this.primaryKey = primaryKey;
    }


    /**
     * Gets the numResult value for this BusquedaCheckSwiftRawBicsResulResponse.
     * 
     * @return numResult
     */
    public int getNumResult() {
        return numResult;
    }


    /**
     * Sets the numResult value for this BusquedaCheckSwiftRawBicsResulResponse.
     * 
     * @param numResult
     */
    public void setNumResult(int numResult) {
        this.numResult = numResult;
    }


    /**
     * Gets the codError value for this BusquedaCheckSwiftRawBicsResulResponse.
     * 
     * @return codError
     */
    public int getCodError() {
        return codError;
    }


    /**
     * Sets the codError value for this BusquedaCheckSwiftRawBicsResulResponse.
     * 
     * @param codError
     */
    public void setCodError(int codError) {
        this.codError = codError;
    }


    /**
     * Gets the descripcionError value for this BusquedaCheckSwiftRawBicsResulResponse.
     * 
     * @return descripcionError
     */
    public java.lang.String getDescripcionError() {
        return descripcionError;
    }


    /**
     * Sets the descripcionError value for this BusquedaCheckSwiftRawBicsResulResponse.
     * 
     * @param descripcionError
     */
    public void setDescripcionError(java.lang.String descripcionError) {
        this.descripcionError = descripcionError;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BusquedaCheckSwiftRawBicsResulResponse)) return false;
        BusquedaCheckSwiftRawBicsResulResponse other = (BusquedaCheckSwiftRawBicsResulResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.busquedaCheckSwiftRawBicsResulResult == other.isBusquedaCheckSwiftRawBicsResulResult() &&
            ((this.primaryKey==null && other.getPrimaryKey()==null) || 
             (this.primaryKey!=null &&
              this.primaryKey.equals(other.getPrimaryKey()))) &&
            this.numResult == other.getNumResult() &&
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
        _hashCode += (isBusquedaCheckSwiftRawBicsResulResult() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getPrimaryKey() != null) {
            _hashCode += getPrimaryKey().hashCode();
        }
        _hashCode += getNumResult();
        _hashCode += getCodError();
        if (getDescripcionError() != null) {
            _hashCode += getDescripcionError().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BusquedaCheckSwiftRawBicsResulResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://localhost/", ">BusquedaCheckSwiftRawBicsResulResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("busquedaCheckSwiftRawBicsResulResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://localhost/", "BusquedaCheckSwiftRawBicsResulResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
        elemField.setFieldName("numResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://localhost/", "numResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
