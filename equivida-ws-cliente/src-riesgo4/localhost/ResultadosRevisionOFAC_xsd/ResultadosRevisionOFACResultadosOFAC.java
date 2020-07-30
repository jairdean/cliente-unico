/**
 * ResultadosRevisionOFACResultadosOFAC.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package localhost.ResultadosRevisionOFAC_xsd;

public class ResultadosRevisionOFACResultadosOFAC  implements java.io.Serializable {
    private java.lang.String score;

    private java.lang.String file;

    private java.lang.String fileDate;

    private java.lang.String wireContext;

    private java.lang.String entityName;

    private java.lang.String bestName;

    private java.lang.String denyReason;

    private java.lang.String listing;

    public ResultadosRevisionOFACResultadosOFAC() {
    }

    public ResultadosRevisionOFACResultadosOFAC(
           java.lang.String score,
           java.lang.String file,
           java.lang.String fileDate,
           java.lang.String wireContext,
           java.lang.String entityName,
           java.lang.String bestName,
           java.lang.String denyReason,
           java.lang.String listing) {
           this.score = score;
           this.file = file;
           this.fileDate = fileDate;
           this.wireContext = wireContext;
           this.entityName = entityName;
           this.bestName = bestName;
           this.denyReason = denyReason;
           this.listing = listing;
    }


    /**
     * Gets the score value for this ResultadosRevisionOFACResultadosOFAC.
     * 
     * @return score
     */
    public java.lang.String getScore() {
        return score;
    }


    /**
     * Sets the score value for this ResultadosRevisionOFACResultadosOFAC.
     * 
     * @param score
     */
    public void setScore(java.lang.String score) {
        this.score = score;
    }


    /**
     * Gets the file value for this ResultadosRevisionOFACResultadosOFAC.
     * 
     * @return file
     */
    public java.lang.String getFile() {
        return file;
    }


    /**
     * Sets the file value for this ResultadosRevisionOFACResultadosOFAC.
     * 
     * @param file
     */
    public void setFile(java.lang.String file) {
        this.file = file;
    }


    /**
     * Gets the fileDate value for this ResultadosRevisionOFACResultadosOFAC.
     * 
     * @return fileDate
     */
    public java.lang.String getFileDate() {
        return fileDate;
    }


    /**
     * Sets the fileDate value for this ResultadosRevisionOFACResultadosOFAC.
     * 
     * @param fileDate
     */
    public void setFileDate(java.lang.String fileDate) {
        this.fileDate = fileDate;
    }


    /**
     * Gets the wireContext value for this ResultadosRevisionOFACResultadosOFAC.
     * 
     * @return wireContext
     */
    public java.lang.String getWireContext() {
        return wireContext;
    }


    /**
     * Sets the wireContext value for this ResultadosRevisionOFACResultadosOFAC.
     * 
     * @param wireContext
     */
    public void setWireContext(java.lang.String wireContext) {
        this.wireContext = wireContext;
    }


    /**
     * Gets the entityName value for this ResultadosRevisionOFACResultadosOFAC.
     * 
     * @return entityName
     */
    public java.lang.String getEntityName() {
        return entityName;
    }


    /**
     * Sets the entityName value for this ResultadosRevisionOFACResultadosOFAC.
     * 
     * @param entityName
     */
    public void setEntityName(java.lang.String entityName) {
        this.entityName = entityName;
    }


    /**
     * Gets the bestName value for this ResultadosRevisionOFACResultadosOFAC.
     * 
     * @return bestName
     */
    public java.lang.String getBestName() {
        return bestName;
    }


    /**
     * Sets the bestName value for this ResultadosRevisionOFACResultadosOFAC.
     * 
     * @param bestName
     */
    public void setBestName(java.lang.String bestName) {
        this.bestName = bestName;
    }


    /**
     * Gets the denyReason value for this ResultadosRevisionOFACResultadosOFAC.
     * 
     * @return denyReason
     */
    public java.lang.String getDenyReason() {
        return denyReason;
    }


    /**
     * Sets the denyReason value for this ResultadosRevisionOFACResultadosOFAC.
     * 
     * @param denyReason
     */
    public void setDenyReason(java.lang.String denyReason) {
        this.denyReason = denyReason;
    }


    /**
     * Gets the listing value for this ResultadosRevisionOFACResultadosOFAC.
     * 
     * @return listing
     */
    public java.lang.String getListing() {
        return listing;
    }


    /**
     * Sets the listing value for this ResultadosRevisionOFACResultadosOFAC.
     * 
     * @param listing
     */
    public void setListing(java.lang.String listing) {
        this.listing = listing;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadosRevisionOFACResultadosOFAC)) return false;
        ResultadosRevisionOFACResultadosOFAC other = (ResultadosRevisionOFACResultadosOFAC) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.score==null && other.getScore()==null) || 
             (this.score!=null &&
              this.score.equals(other.getScore()))) &&
            ((this.file==null && other.getFile()==null) || 
             (this.file!=null &&
              this.file.equals(other.getFile()))) &&
            ((this.fileDate==null && other.getFileDate()==null) || 
             (this.fileDate!=null &&
              this.fileDate.equals(other.getFileDate()))) &&
            ((this.wireContext==null && other.getWireContext()==null) || 
             (this.wireContext!=null &&
              this.wireContext.equals(other.getWireContext()))) &&
            ((this.entityName==null && other.getEntityName()==null) || 
             (this.entityName!=null &&
              this.entityName.equals(other.getEntityName()))) &&
            ((this.bestName==null && other.getBestName()==null) || 
             (this.bestName!=null &&
              this.bestName.equals(other.getBestName()))) &&
            ((this.denyReason==null && other.getDenyReason()==null) || 
             (this.denyReason!=null &&
              this.denyReason.equals(other.getDenyReason()))) &&
            ((this.listing==null && other.getListing()==null) || 
             (this.listing!=null &&
              this.listing.equals(other.getListing())));
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
        if (getScore() != null) {
            _hashCode += getScore().hashCode();
        }
        if (getFile() != null) {
            _hashCode += getFile().hashCode();
        }
        if (getFileDate() != null) {
            _hashCode += getFileDate().hashCode();
        }
        if (getWireContext() != null) {
            _hashCode += getWireContext().hashCode();
        }
        if (getEntityName() != null) {
            _hashCode += getEntityName().hashCode();
        }
        if (getBestName() != null) {
            _hashCode += getBestName().hashCode();
        }
        if (getDenyReason() != null) {
            _hashCode += getDenyReason().hashCode();
        }
        if (getListing() != null) {
            _hashCode += getListing().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultadosRevisionOFACResultadosOFAC.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://localhost/ResultadosRevisionOFAC.xsd", ">>ResultadosRevisionOFAC>ResultadosOFAC"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("score");
        elemField.setXmlName(new javax.xml.namespace.QName("http://localhost/ResultadosRevisionOFAC.xsd", "Score"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("file");
        elemField.setXmlName(new javax.xml.namespace.QName("http://localhost/ResultadosRevisionOFAC.xsd", "File"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fileDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://localhost/ResultadosRevisionOFAC.xsd", "FileDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("wireContext");
        elemField.setXmlName(new javax.xml.namespace.QName("http://localhost/ResultadosRevisionOFAC.xsd", "WireContext"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("entityName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://localhost/ResultadosRevisionOFAC.xsd", "EntityName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bestName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://localhost/ResultadosRevisionOFAC.xsd", "BestName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("denyReason");
        elemField.setXmlName(new javax.xml.namespace.QName("http://localhost/ResultadosRevisionOFAC.xsd", "DenyReason"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listing");
        elemField.setXmlName(new javax.xml.namespace.QName("http://localhost/ResultadosRevisionOFAC.xsd", "Listing"));
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
