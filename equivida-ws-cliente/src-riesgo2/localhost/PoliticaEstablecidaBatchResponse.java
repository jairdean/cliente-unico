/**
 * PoliticaEstablecidaBatchResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package localhost;

public class PoliticaEstablecidaBatchResponse  implements java.io.Serializable {
    private java.lang.String politicaEstablecidaBatchResult;

    private int codError;

    private java.lang.String descripcionError;

    public PoliticaEstablecidaBatchResponse() {
    }

    public PoliticaEstablecidaBatchResponse(
           java.lang.String politicaEstablecidaBatchResult,
           int codError,
           java.lang.String descripcionError) {
           this.politicaEstablecidaBatchResult = politicaEstablecidaBatchResult;
           this.codError = codError;
           this.descripcionError = descripcionError;
    }


    /**
     * Gets the politicaEstablecidaBatchResult value for this PoliticaEstablecidaBatchResponse.
     * 
     * @return politicaEstablecidaBatchResult
     */
    public java.lang.String getPoliticaEstablecidaBatchResult() {
        return politicaEstablecidaBatchResult;
    }


    /**
     * Sets the politicaEstablecidaBatchResult value for this PoliticaEstablecidaBatchResponse.
     * 
     * @param politicaEstablecidaBatchResult
     */
    public void setPoliticaEstablecidaBatchResult(java.lang.String politicaEstablecidaBatchResult) {
        this.politicaEstablecidaBatchResult = politicaEstablecidaBatchResult;
    }


    /**
     * Gets the codError value for this PoliticaEstablecidaBatchResponse.
     * 
     * @return codError
     */
    public int getCodError() {
        return codError;
    }


    /**
     * Sets the codError value for this PoliticaEstablecidaBatchResponse.
     * 
     * @param codError
     */
    public void setCodError(int codError) {
        this.codError = codError;
    }


    /**
     * Gets the descripcionError value for this PoliticaEstablecidaBatchResponse.
     * 
     * @return descripcionError
     */
    public java.lang.String getDescripcionError() {
        return descripcionError;
    }


    /**
     * Sets the descripcionError value for this PoliticaEstablecidaBatchResponse.
     * 
     * @param descripcionError
     */
    public void setDescripcionError(java.lang.String descripcionError) {
        this.descripcionError = descripcionError;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PoliticaEstablecidaBatchResponse)) return false;
        PoliticaEstablecidaBatchResponse other = (PoliticaEstablecidaBatchResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.politicaEstablecidaBatchResult==null && other.getPoliticaEstablecidaBatchResult()==null) || 
             (this.politicaEstablecidaBatchResult!=null &&
              this.politicaEstablecidaBatchResult.equals(other.getPoliticaEstablecidaBatchResult()))) &&
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
        if (getPoliticaEstablecidaBatchResult() != null) {
            _hashCode += getPoliticaEstablecidaBatchResult().hashCode();
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
        new org.apache.axis.description.TypeDesc(PoliticaEstablecidaBatchResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://localhost/", ">PoliticaEstablecidaBatchResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("politicaEstablecidaBatchResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://localhost/", "PoliticaEstablecidaBatchResult"));
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
