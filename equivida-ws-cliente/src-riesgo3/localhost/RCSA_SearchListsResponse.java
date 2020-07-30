/**
 * RCSA_SearchListsResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package localhost;

public class RCSA_SearchListsResponse  implements java.io.Serializable {
    private localhost.RCSA_SearchListsResponseRCSA_SearchListsResult RCSA_SearchListsResult;

    private java.lang.String primaryKey;

    private int codError;

    private java.lang.String descripcionError;

    public RCSA_SearchListsResponse() {
    }

    public RCSA_SearchListsResponse(
           localhost.RCSA_SearchListsResponseRCSA_SearchListsResult RCSA_SearchListsResult,
           java.lang.String primaryKey,
           int codError,
           java.lang.String descripcionError) {
           this.RCSA_SearchListsResult = RCSA_SearchListsResult;
           this.primaryKey = primaryKey;
           this.codError = codError;
           this.descripcionError = descripcionError;
    }


    /**
     * Gets the RCSA_SearchListsResult value for this RCSA_SearchListsResponse.
     * 
     * @return RCSA_SearchListsResult
     */
    public localhost.RCSA_SearchListsResponseRCSA_SearchListsResult getRCSA_SearchListsResult() {
        return RCSA_SearchListsResult;
    }


    /**
     * Sets the RCSA_SearchListsResult value for this RCSA_SearchListsResponse.
     * 
     * @param RCSA_SearchListsResult
     */
    public void setRCSA_SearchListsResult(localhost.RCSA_SearchListsResponseRCSA_SearchListsResult RCSA_SearchListsResult) {
        this.RCSA_SearchListsResult = RCSA_SearchListsResult;
    }


    /**
     * Gets the primaryKey value for this RCSA_SearchListsResponse.
     * 
     * @return primaryKey
     */
    public java.lang.String getPrimaryKey() {
        return primaryKey;
    }


    /**
     * Sets the primaryKey value for this RCSA_SearchListsResponse.
     * 
     * @param primaryKey
     */
    public void setPrimaryKey(java.lang.String primaryKey) {
        this.primaryKey = primaryKey;
    }


    /**
     * Gets the codError value for this RCSA_SearchListsResponse.
     * 
     * @return codError
     */
    public int getCodError() {
        return codError;
    }


    /**
     * Sets the codError value for this RCSA_SearchListsResponse.
     * 
     * @param codError
     */
    public void setCodError(int codError) {
        this.codError = codError;
    }


    /**
     * Gets the descripcionError value for this RCSA_SearchListsResponse.
     * 
     * @return descripcionError
     */
    public java.lang.String getDescripcionError() {
        return descripcionError;
    }


    /**
     * Sets the descripcionError value for this RCSA_SearchListsResponse.
     * 
     * @param descripcionError
     */
    public void setDescripcionError(java.lang.String descripcionError) {
        this.descripcionError = descripcionError;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RCSA_SearchListsResponse)) return false;
        RCSA_SearchListsResponse other = (RCSA_SearchListsResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.RCSA_SearchListsResult==null && other.getRCSA_SearchListsResult()==null) || 
             (this.RCSA_SearchListsResult!=null &&
              this.RCSA_SearchListsResult.equals(other.getRCSA_SearchListsResult()))) &&
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
        if (getRCSA_SearchListsResult() != null) {
            _hashCode += getRCSA_SearchListsResult().hashCode();
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
        new org.apache.axis.description.TypeDesc(RCSA_SearchListsResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://localhost/", ">RCSA_SearchListsResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("RCSA_SearchListsResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://localhost/", "RCSA_SearchListsResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://localhost/", ">>RCSA_SearchListsResponse>RCSA_SearchListsResult"));
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
