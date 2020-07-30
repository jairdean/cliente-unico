/**
 * BusquedaIDVerificationConsumoXMLResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package localhost;

public class BusquedaIDVerificationConsumoXMLResponse  implements java.io.Serializable {
    private localhost.BusquedaIDVerificationConsumoXMLResponseBusquedaIDVerificationConsumoXMLResult busquedaIDVerificationConsumoXMLResult;

    public BusquedaIDVerificationConsumoXMLResponse() {
    }

    public BusquedaIDVerificationConsumoXMLResponse(
           localhost.BusquedaIDVerificationConsumoXMLResponseBusquedaIDVerificationConsumoXMLResult busquedaIDVerificationConsumoXMLResult) {
           this.busquedaIDVerificationConsumoXMLResult = busquedaIDVerificationConsumoXMLResult;
    }


    /**
     * Gets the busquedaIDVerificationConsumoXMLResult value for this BusquedaIDVerificationConsumoXMLResponse.
     * 
     * @return busquedaIDVerificationConsumoXMLResult
     */
    public localhost.BusquedaIDVerificationConsumoXMLResponseBusquedaIDVerificationConsumoXMLResult getBusquedaIDVerificationConsumoXMLResult() {
        return busquedaIDVerificationConsumoXMLResult;
    }


    /**
     * Sets the busquedaIDVerificationConsumoXMLResult value for this BusquedaIDVerificationConsumoXMLResponse.
     * 
     * @param busquedaIDVerificationConsumoXMLResult
     */
    public void setBusquedaIDVerificationConsumoXMLResult(localhost.BusquedaIDVerificationConsumoXMLResponseBusquedaIDVerificationConsumoXMLResult busquedaIDVerificationConsumoXMLResult) {
        this.busquedaIDVerificationConsumoXMLResult = busquedaIDVerificationConsumoXMLResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BusquedaIDVerificationConsumoXMLResponse)) return false;
        BusquedaIDVerificationConsumoXMLResponse other = (BusquedaIDVerificationConsumoXMLResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.busquedaIDVerificationConsumoXMLResult==null && other.getBusquedaIDVerificationConsumoXMLResult()==null) || 
             (this.busquedaIDVerificationConsumoXMLResult!=null &&
              this.busquedaIDVerificationConsumoXMLResult.equals(other.getBusquedaIDVerificationConsumoXMLResult())));
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
        if (getBusquedaIDVerificationConsumoXMLResult() != null) {
            _hashCode += getBusquedaIDVerificationConsumoXMLResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BusquedaIDVerificationConsumoXMLResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://localhost/", ">BusquedaIDVerificationConsumoXMLResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("busquedaIDVerificationConsumoXMLResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://localhost/", "BusquedaIDVerificationConsumoXMLResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://localhost/", ">>BusquedaIDVerificationConsumoXMLResponse>BusquedaIDVerificationConsumoXMLResult"));
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
