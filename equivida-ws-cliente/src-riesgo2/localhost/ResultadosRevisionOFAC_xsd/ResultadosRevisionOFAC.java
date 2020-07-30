/**
 * ResultadosRevisionOFAC.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package localhost.ResultadosRevisionOFAC_xsd;

public class ResultadosRevisionOFAC  implements java.io.Serializable {
    private localhost.ResultadosRevisionOFAC_xsd.ResultadosRevisionOFACResultadosOFAC resultadosOFAC;

    public ResultadosRevisionOFAC() {
    }

    public ResultadosRevisionOFAC(
           localhost.ResultadosRevisionOFAC_xsd.ResultadosRevisionOFACResultadosOFAC resultadosOFAC) {
           this.resultadosOFAC = resultadosOFAC;
    }


    /**
     * Gets the resultadosOFAC value for this ResultadosRevisionOFAC.
     * 
     * @return resultadosOFAC
     */
    public localhost.ResultadosRevisionOFAC_xsd.ResultadosRevisionOFACResultadosOFAC getResultadosOFAC() {
        return resultadosOFAC;
    }


    /**
     * Sets the resultadosOFAC value for this ResultadosRevisionOFAC.
     * 
     * @param resultadosOFAC
     */
    public void setResultadosOFAC(localhost.ResultadosRevisionOFAC_xsd.ResultadosRevisionOFACResultadosOFAC resultadosOFAC) {
        this.resultadosOFAC = resultadosOFAC;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadosRevisionOFAC)) return false;
        ResultadosRevisionOFAC other = (ResultadosRevisionOFAC) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultadosOFAC==null && other.getResultadosOFAC()==null) || 
             (this.resultadosOFAC!=null &&
              this.resultadosOFAC.equals(other.getResultadosOFAC())));
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
        if (getResultadosOFAC() != null) {
            _hashCode += getResultadosOFAC().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultadosRevisionOFAC.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://localhost/ResultadosRevisionOFAC.xsd", ">ResultadosRevisionOFAC"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadosOFAC");
        elemField.setXmlName(new javax.xml.namespace.QName("http://localhost/ResultadosRevisionOFAC.xsd", "ResultadosOFAC"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://localhost/ResultadosRevisionOFAC.xsd", ">>ResultadosRevisionOFAC>ResultadosOFAC"));
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
