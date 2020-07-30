/**
 * ProbarWebService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package localhost;

public class ProbarWebService  implements java.io.Serializable {
    private java.lang.String rutaSetupDB;

    public ProbarWebService() {
    }

    public ProbarWebService(
           java.lang.String rutaSetupDB) {
           this.rutaSetupDB = rutaSetupDB;
    }


    /**
     * Gets the rutaSetupDB value for this ProbarWebService.
     * 
     * @return rutaSetupDB
     */
    public java.lang.String getRutaSetupDB() {
        return rutaSetupDB;
    }


    /**
     * Sets the rutaSetupDB value for this ProbarWebService.
     * 
     * @param rutaSetupDB
     */
    public void setRutaSetupDB(java.lang.String rutaSetupDB) {
        this.rutaSetupDB = rutaSetupDB;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ProbarWebService)) return false;
        ProbarWebService other = (ProbarWebService) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.rutaSetupDB==null && other.getRutaSetupDB()==null) || 
             (this.rutaSetupDB!=null &&
              this.rutaSetupDB.equals(other.getRutaSetupDB())));
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
        if (getRutaSetupDB() != null) {
            _hashCode += getRutaSetupDB().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ProbarWebService.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://localhost/", ">ProbarWebService"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rutaSetupDB");
        elemField.setXmlName(new javax.xml.namespace.QName("http://localhost/", "rutaSetupDB"));
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
