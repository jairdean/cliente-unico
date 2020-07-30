/**
 * BusquedaAlternateSearchResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package localhost;

public class BusquedaAlternateSearchResponse  implements java.io.Serializable {
    private localhost.BusquedaAlternateSearchResponseBusquedaAlternateSearchResult busquedaAlternateSearchResult;

    private java.lang.String primaryKey;

    private int codError;

    private java.lang.String descripcionError;

    public BusquedaAlternateSearchResponse() {
    }

    public BusquedaAlternateSearchResponse(
           localhost.BusquedaAlternateSearchResponseBusquedaAlternateSearchResult busquedaAlternateSearchResult,
           java.lang.String primaryKey,
           int codError,
           java.lang.String descripcionError) {
           this.busquedaAlternateSearchResult = busquedaAlternateSearchResult;
           this.primaryKey = primaryKey;
           this.codError = codError;
           this.descripcionError = descripcionError;
    }


    /**
     * Gets the busquedaAlternateSearchResult value for this BusquedaAlternateSearchResponse.
     * 
     * @return busquedaAlternateSearchResult
     */
    public localhost.BusquedaAlternateSearchResponseBusquedaAlternateSearchResult getBusquedaAlternateSearchResult() {
        return busquedaAlternateSearchResult;
    }


    /**
     * Sets the busquedaAlternateSearchResult value for this BusquedaAlternateSearchResponse.
     * 
     * @param busquedaAlternateSearchResult
     */
    public void setBusquedaAlternateSearchResult(localhost.BusquedaAlternateSearchResponseBusquedaAlternateSearchResult busquedaAlternateSearchResult) {
        this.busquedaAlternateSearchResult = busquedaAlternateSearchResult;
    }


    /**
     * Gets the primaryKey value for this BusquedaAlternateSearchResponse.
     * 
     * @return primaryKey
     */
    public java.lang.String getPrimaryKey() {
        return primaryKey;
    }


    /**
     * Sets the primaryKey value for this BusquedaAlternateSearchResponse.
     * 
     * @param primaryKey
     */
    public void setPrimaryKey(java.lang.String primaryKey) {
        this.primaryKey = primaryKey;
    }


    /**
     * Gets the codError value for this BusquedaAlternateSearchResponse.
     * 
     * @return codError
     */
    public int getCodError() {
        return codError;
    }


    /**
     * Sets the codError value for this BusquedaAlternateSearchResponse.
     * 
     * @param codError
     */
    public void setCodError(int codError) {
        this.codError = codError;
    }


    /**
     * Gets the descripcionError value for this BusquedaAlternateSearchResponse.
     * 
     * @return descripcionError
     */
    public java.lang.String getDescripcionError() {
        return descripcionError;
    }


    /**
     * Sets the descripcionError value for this BusquedaAlternateSearchResponse.
     * 
     * @param descripcionError
     */
    public void setDescripcionError(java.lang.String descripcionError) {
        this.descripcionError = descripcionError;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BusquedaAlternateSearchResponse)) return false;
        BusquedaAlternateSearchResponse other = (BusquedaAlternateSearchResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.busquedaAlternateSearchResult==null && other.getBusquedaAlternateSearchResult()==null) || 
             (this.busquedaAlternateSearchResult!=null &&
              this.busquedaAlternateSearchResult.equals(other.getBusquedaAlternateSearchResult()))) &&
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
        if (getBusquedaAlternateSearchResult() != null) {
            _hashCode += getBusquedaAlternateSearchResult().hashCode();
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
        new org.apache.axis.description.TypeDesc(BusquedaAlternateSearchResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://localhost/", ">BusquedaAlternateSearchResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("busquedaAlternateSearchResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://localhost/", "BusquedaAlternateSearchResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://localhost/", ">>BusquedaAlternateSearchResponse>BusquedaAlternateSearchResult"));
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
