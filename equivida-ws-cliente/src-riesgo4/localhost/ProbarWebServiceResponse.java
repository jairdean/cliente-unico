/**
 * ProbarWebServiceResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package localhost;

public class ProbarWebServiceResponse  implements java.io.Serializable {
    private java.lang.String probarWebServiceResult;

    public ProbarWebServiceResponse() {
    }

    public ProbarWebServiceResponse(
           java.lang.String probarWebServiceResult) {
           this.probarWebServiceResult = probarWebServiceResult;
    }


    /**
     * Gets the probarWebServiceResult value for this ProbarWebServiceResponse.
     * 
     * @return probarWebServiceResult
     */
    public java.lang.String getProbarWebServiceResult() {
        return probarWebServiceResult;
    }


    /**
     * Sets the probarWebServiceResult value for this ProbarWebServiceResponse.
     * 
     * @param probarWebServiceResult
     */
    public void setProbarWebServiceResult(java.lang.String probarWebServiceResult) {
        this.probarWebServiceResult = probarWebServiceResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ProbarWebServiceResponse)) return false;
        ProbarWebServiceResponse other = (ProbarWebServiceResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.probarWebServiceResult==null && other.getProbarWebServiceResult()==null) || 
             (this.probarWebServiceResult!=null &&
              this.probarWebServiceResult.equals(other.getProbarWebServiceResult())));
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
        if (getProbarWebServiceResult() != null) {
            _hashCode += getProbarWebServiceResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ProbarWebServiceResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://localhost/", ">ProbarWebServiceResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("probarWebServiceResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://localhost/", "ProbarWebServiceResult"));
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
