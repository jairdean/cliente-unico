/**
 * ProcesarResultadosScanfilesResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package localhost;

public class ProcesarResultadosScanfilesResponse  implements java.io.Serializable {
    private boolean procesarResultadosScanfilesResult;

    private int codigoError;

    private java.lang.String descError;

    public ProcesarResultadosScanfilesResponse() {
    }

    public ProcesarResultadosScanfilesResponse(
           boolean procesarResultadosScanfilesResult,
           int codigoError,
           java.lang.String descError) {
           this.procesarResultadosScanfilesResult = procesarResultadosScanfilesResult;
           this.codigoError = codigoError;
           this.descError = descError;
    }


    /**
     * Gets the procesarResultadosScanfilesResult value for this ProcesarResultadosScanfilesResponse.
     * 
     * @return procesarResultadosScanfilesResult
     */
    public boolean isProcesarResultadosScanfilesResult() {
        return procesarResultadosScanfilesResult;
    }


    /**
     * Sets the procesarResultadosScanfilesResult value for this ProcesarResultadosScanfilesResponse.
     * 
     * @param procesarResultadosScanfilesResult
     */
    public void setProcesarResultadosScanfilesResult(boolean procesarResultadosScanfilesResult) {
        this.procesarResultadosScanfilesResult = procesarResultadosScanfilesResult;
    }


    /**
     * Gets the codigoError value for this ProcesarResultadosScanfilesResponse.
     * 
     * @return codigoError
     */
    public int getCodigoError() {
        return codigoError;
    }


    /**
     * Sets the codigoError value for this ProcesarResultadosScanfilesResponse.
     * 
     * @param codigoError
     */
    public void setCodigoError(int codigoError) {
        this.codigoError = codigoError;
    }


    /**
     * Gets the descError value for this ProcesarResultadosScanfilesResponse.
     * 
     * @return descError
     */
    public java.lang.String getDescError() {
        return descError;
    }


    /**
     * Sets the descError value for this ProcesarResultadosScanfilesResponse.
     * 
     * @param descError
     */
    public void setDescError(java.lang.String descError) {
        this.descError = descError;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ProcesarResultadosScanfilesResponse)) return false;
        ProcesarResultadosScanfilesResponse other = (ProcesarResultadosScanfilesResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.procesarResultadosScanfilesResult == other.isProcesarResultadosScanfilesResult() &&
            this.codigoError == other.getCodigoError() &&
            ((this.descError==null && other.getDescError()==null) || 
             (this.descError!=null &&
              this.descError.equals(other.getDescError())));
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
        _hashCode += (isProcesarResultadosScanfilesResult() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += getCodigoError();
        if (getDescError() != null) {
            _hashCode += getDescError().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ProcesarResultadosScanfilesResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://localhost/", ">ProcesarResultadosScanfilesResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("procesarResultadosScanfilesResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://localhost/", "ProcesarResultadosScanfilesResult"));
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
        elemField.setFieldName("descError");
        elemField.setXmlName(new javax.xml.namespace.QName("http://localhost/", "descError"));
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
