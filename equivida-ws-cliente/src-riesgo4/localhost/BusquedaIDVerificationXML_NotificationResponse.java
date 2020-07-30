/**
 * BusquedaIDVerificationXML_NotificationResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package localhost;

public class BusquedaIDVerificationXML_NotificationResponse  implements java.io.Serializable {
    private localhost.BusquedaIDVerificationXML_NotificationResponseBusquedaIDVerificationXML_NotificationResult busquedaIDVerificationXML_NotificationResult;

    public BusquedaIDVerificationXML_NotificationResponse() {
    }

    public BusquedaIDVerificationXML_NotificationResponse(
           localhost.BusquedaIDVerificationXML_NotificationResponseBusquedaIDVerificationXML_NotificationResult busquedaIDVerificationXML_NotificationResult) {
           this.busquedaIDVerificationXML_NotificationResult = busquedaIDVerificationXML_NotificationResult;
    }


    /**
     * Gets the busquedaIDVerificationXML_NotificationResult value for this BusquedaIDVerificationXML_NotificationResponse.
     * 
     * @return busquedaIDVerificationXML_NotificationResult
     */
    public localhost.BusquedaIDVerificationXML_NotificationResponseBusquedaIDVerificationXML_NotificationResult getBusquedaIDVerificationXML_NotificationResult() {
        return busquedaIDVerificationXML_NotificationResult;
    }


    /**
     * Sets the busquedaIDVerificationXML_NotificationResult value for this BusquedaIDVerificationXML_NotificationResponse.
     * 
     * @param busquedaIDVerificationXML_NotificationResult
     */
    public void setBusquedaIDVerificationXML_NotificationResult(localhost.BusquedaIDVerificationXML_NotificationResponseBusquedaIDVerificationXML_NotificationResult busquedaIDVerificationXML_NotificationResult) {
        this.busquedaIDVerificationXML_NotificationResult = busquedaIDVerificationXML_NotificationResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BusquedaIDVerificationXML_NotificationResponse)) return false;
        BusquedaIDVerificationXML_NotificationResponse other = (BusquedaIDVerificationXML_NotificationResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.busquedaIDVerificationXML_NotificationResult==null && other.getBusquedaIDVerificationXML_NotificationResult()==null) || 
             (this.busquedaIDVerificationXML_NotificationResult!=null &&
              this.busquedaIDVerificationXML_NotificationResult.equals(other.getBusquedaIDVerificationXML_NotificationResult())));
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
        if (getBusquedaIDVerificationXML_NotificationResult() != null) {
            _hashCode += getBusquedaIDVerificationXML_NotificationResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BusquedaIDVerificationXML_NotificationResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://localhost/", ">BusquedaIDVerificationXML_NotificationResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("busquedaIDVerificationXML_NotificationResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://localhost/", "BusquedaIDVerificationXML_NotificationResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://localhost/", ">>BusquedaIDVerificationXML_NotificationResponse>BusquedaIDVerificationXML_NotificationResult"));
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
