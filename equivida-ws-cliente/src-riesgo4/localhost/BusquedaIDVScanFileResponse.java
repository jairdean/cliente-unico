/**
 * BusquedaIDVScanFileResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package localhost;

public class BusquedaIDVScanFileResponse  implements java.io.Serializable {
    private localhost.BusquedaIDVScanFileResponseBusquedaIDVScanFileResult busquedaIDVScanFileResult;

    private int codError;

    private java.lang.String descripcionError;

    public BusquedaIDVScanFileResponse() {
    }

    public BusquedaIDVScanFileResponse(
           localhost.BusquedaIDVScanFileResponseBusquedaIDVScanFileResult busquedaIDVScanFileResult,
           int codError,
           java.lang.String descripcionError) {
           this.busquedaIDVScanFileResult = busquedaIDVScanFileResult;
           this.codError = codError;
           this.descripcionError = descripcionError;
    }


    /**
     * Gets the busquedaIDVScanFileResult value for this BusquedaIDVScanFileResponse.
     * 
     * @return busquedaIDVScanFileResult
     */
    public localhost.BusquedaIDVScanFileResponseBusquedaIDVScanFileResult getBusquedaIDVScanFileResult() {
        return busquedaIDVScanFileResult;
    }


    /**
     * Sets the busquedaIDVScanFileResult value for this BusquedaIDVScanFileResponse.
     * 
     * @param busquedaIDVScanFileResult
     */
    public void setBusquedaIDVScanFileResult(localhost.BusquedaIDVScanFileResponseBusquedaIDVScanFileResult busquedaIDVScanFileResult) {
        this.busquedaIDVScanFileResult = busquedaIDVScanFileResult;
    }


    /**
     * Gets the codError value for this BusquedaIDVScanFileResponse.
     * 
     * @return codError
     */
    public int getCodError() {
        return codError;
    }


    /**
     * Sets the codError value for this BusquedaIDVScanFileResponse.
     * 
     * @param codError
     */
    public void setCodError(int codError) {
        this.codError = codError;
    }


    /**
     * Gets the descripcionError value for this BusquedaIDVScanFileResponse.
     * 
     * @return descripcionError
     */
    public java.lang.String getDescripcionError() {
        return descripcionError;
    }


    /**
     * Sets the descripcionError value for this BusquedaIDVScanFileResponse.
     * 
     * @param descripcionError
     */
    public void setDescripcionError(java.lang.String descripcionError) {
        this.descripcionError = descripcionError;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BusquedaIDVScanFileResponse)) return false;
        BusquedaIDVScanFileResponse other = (BusquedaIDVScanFileResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.busquedaIDVScanFileResult==null && other.getBusquedaIDVScanFileResult()==null) || 
             (this.busquedaIDVScanFileResult!=null &&
              this.busquedaIDVScanFileResult.equals(other.getBusquedaIDVScanFileResult()))) &&
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
        if (getBusquedaIDVScanFileResult() != null) {
            _hashCode += getBusquedaIDVScanFileResult().hashCode();
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
        new org.apache.axis.description.TypeDesc(BusquedaIDVScanFileResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://localhost/", ">BusquedaIDVScanFileResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("busquedaIDVScanFileResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://localhost/", "BusquedaIDVScanFileResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://localhost/", ">>BusquedaIDVScanFileResponse>BusquedaIDVScanFileResult"));
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
