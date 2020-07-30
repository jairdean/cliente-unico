/**
 * ResultadoScanFile.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package localhost.ResultadoScanFile_xsd;

public class ResultadoScanFile  implements java.io.Serializable {
    private localhost.ResultadoScanFile_xsd.ResultadoScanFileResultadosSFviaIDV resultadosSFviaIDV;

    private localhost.ResultadoScanFile_xsd.ResultadoScanFileResultadosScanFile resultadosScanFile;

    private localhost.ResultadoScanFile_xsd.ResultadoScanFileResultados resultados;

    private localhost.ResultadoScanFile_xsd.ResultadoScanFileBusqueda busqueda;

    public ResultadoScanFile() {
    }

    public ResultadoScanFile(
           localhost.ResultadoScanFile_xsd.ResultadoScanFileResultadosSFviaIDV resultadosSFviaIDV,
           localhost.ResultadoScanFile_xsd.ResultadoScanFileResultadosScanFile resultadosScanFile,
           localhost.ResultadoScanFile_xsd.ResultadoScanFileResultados resultados,
           localhost.ResultadoScanFile_xsd.ResultadoScanFileBusqueda busqueda) {
           this.resultadosSFviaIDV = resultadosSFviaIDV;
           this.resultadosScanFile = resultadosScanFile;
           this.resultados = resultados;
           this.busqueda = busqueda;
    }


    /**
     * Gets the resultadosSFviaIDV value for this ResultadoScanFile.
     * 
     * @return resultadosSFviaIDV
     */
    public localhost.ResultadoScanFile_xsd.ResultadoScanFileResultadosSFviaIDV getResultadosSFviaIDV() {
        return resultadosSFviaIDV;
    }


    /**
     * Sets the resultadosSFviaIDV value for this ResultadoScanFile.
     * 
     * @param resultadosSFviaIDV
     */
    public void setResultadosSFviaIDV(localhost.ResultadoScanFile_xsd.ResultadoScanFileResultadosSFviaIDV resultadosSFviaIDV) {
        this.resultadosSFviaIDV = resultadosSFviaIDV;
    }


    /**
     * Gets the resultadosScanFile value for this ResultadoScanFile.
     * 
     * @return resultadosScanFile
     */
    public localhost.ResultadoScanFile_xsd.ResultadoScanFileResultadosScanFile getResultadosScanFile() {
        return resultadosScanFile;
    }


    /**
     * Sets the resultadosScanFile value for this ResultadoScanFile.
     * 
     * @param resultadosScanFile
     */
    public void setResultadosScanFile(localhost.ResultadoScanFile_xsd.ResultadoScanFileResultadosScanFile resultadosScanFile) {
        this.resultadosScanFile = resultadosScanFile;
    }


    /**
     * Gets the resultados value for this ResultadoScanFile.
     * 
     * @return resultados
     */
    public localhost.ResultadoScanFile_xsd.ResultadoScanFileResultados getResultados() {
        return resultados;
    }


    /**
     * Sets the resultados value for this ResultadoScanFile.
     * 
     * @param resultados
     */
    public void setResultados(localhost.ResultadoScanFile_xsd.ResultadoScanFileResultados resultados) {
        this.resultados = resultados;
    }


    /**
     * Gets the busqueda value for this ResultadoScanFile.
     * 
     * @return busqueda
     */
    public localhost.ResultadoScanFile_xsd.ResultadoScanFileBusqueda getBusqueda() {
        return busqueda;
    }


    /**
     * Sets the busqueda value for this ResultadoScanFile.
     * 
     * @param busqueda
     */
    public void setBusqueda(localhost.ResultadoScanFile_xsd.ResultadoScanFileBusqueda busqueda) {
        this.busqueda = busqueda;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoScanFile)) return false;
        ResultadoScanFile other = (ResultadoScanFile) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultadosSFviaIDV==null && other.getResultadosSFviaIDV()==null) || 
             (this.resultadosSFviaIDV!=null &&
              this.resultadosSFviaIDV.equals(other.getResultadosSFviaIDV()))) &&
            ((this.resultadosScanFile==null && other.getResultadosScanFile()==null) || 
             (this.resultadosScanFile!=null &&
              this.resultadosScanFile.equals(other.getResultadosScanFile()))) &&
            ((this.resultados==null && other.getResultados()==null) || 
             (this.resultados!=null &&
              this.resultados.equals(other.getResultados()))) &&
            ((this.busqueda==null && other.getBusqueda()==null) || 
             (this.busqueda!=null &&
              this.busqueda.equals(other.getBusqueda())));
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
        if (getResultadosSFviaIDV() != null) {
            _hashCode += getResultadosSFviaIDV().hashCode();
        }
        if (getResultadosScanFile() != null) {
            _hashCode += getResultadosScanFile().hashCode();
        }
        if (getResultados() != null) {
            _hashCode += getResultados().hashCode();
        }
        if (getBusqueda() != null) {
            _hashCode += getBusqueda().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultadoScanFile.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://localhost/ResultadoScanFile.xsd", ">ResultadoScanFile"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadosSFviaIDV");
        elemField.setXmlName(new javax.xml.namespace.QName("http://localhost/ResultadoScanFile.xsd", "ResultadosSFviaIDV"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://localhost/ResultadoScanFile.xsd", ">>ResultadoScanFile>ResultadosSFviaIDV"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadosScanFile");
        elemField.setXmlName(new javax.xml.namespace.QName("http://localhost/ResultadoScanFile.xsd", "ResultadosScanFile"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://localhost/ResultadoScanFile.xsd", ">>ResultadoScanFile>ResultadosScanFile"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultados");
        elemField.setXmlName(new javax.xml.namespace.QName("http://localhost/ResultadoScanFile.xsd", "Resultados"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://localhost/ResultadoScanFile.xsd", ">>ResultadoScanFile>Resultados"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("busqueda");
        elemField.setXmlName(new javax.xml.namespace.QName("http://localhost/ResultadoScanFile.xsd", "Busqueda"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://localhost/ResultadoScanFile.xsd", ">>ResultadoScanFile>Busqueda"));
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
