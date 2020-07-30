/**
 * SrQaEmailOutVar1.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ibm.isd.QA_Equivida.SrQaEmail;

public class SrQaEmailOutVar1  implements java.io.Serializable {
    private java.lang.String nameemail_emails;

    private java.lang.String prefixdomain_emails;

    private java.lang.String domain_emails;

    private java.lang.String suffixdomain_emails;

    private java.lang.String countrydomain_emails;

    private java.lang.String email_emails;

    private java.lang.String unhandledpattern_emails;

    private java.lang.Integer status;

    private java.lang.String mensaje;

    public SrQaEmailOutVar1() {
    }

    public SrQaEmailOutVar1(
           java.lang.String nameemail_emails,
           java.lang.String prefixdomain_emails,
           java.lang.String domain_emails,
           java.lang.String suffixdomain_emails,
           java.lang.String countrydomain_emails,
           java.lang.String email_emails,
           java.lang.String unhandledpattern_emails,
           java.lang.Integer status,
           java.lang.String mensaje) {
           this.nameemail_emails = nameemail_emails;
           this.prefixdomain_emails = prefixdomain_emails;
           this.domain_emails = domain_emails;
           this.suffixdomain_emails = suffixdomain_emails;
           this.countrydomain_emails = countrydomain_emails;
           this.email_emails = email_emails;
           this.unhandledpattern_emails = unhandledpattern_emails;
           this.status = status;
           this.mensaje = mensaje;
    }


    /**
     * Gets the nameemail_emails value for this SrQaEmailOutVar1.
     * 
     * @return nameemail_emails
     */
    public java.lang.String getNameemail_emails() {
        return nameemail_emails;
    }


    /**
     * Sets the nameemail_emails value for this SrQaEmailOutVar1.
     * 
     * @param nameemail_emails
     */
    public void setNameemail_emails(java.lang.String nameemail_emails) {
        this.nameemail_emails = nameemail_emails;
    }


    /**
     * Gets the prefixdomain_emails value for this SrQaEmailOutVar1.
     * 
     * @return prefixdomain_emails
     */
    public java.lang.String getPrefixdomain_emails() {
        return prefixdomain_emails;
    }


    /**
     * Sets the prefixdomain_emails value for this SrQaEmailOutVar1.
     * 
     * @param prefixdomain_emails
     */
    public void setPrefixdomain_emails(java.lang.String prefixdomain_emails) {
        this.prefixdomain_emails = prefixdomain_emails;
    }


    /**
     * Gets the domain_emails value for this SrQaEmailOutVar1.
     * 
     * @return domain_emails
     */
    public java.lang.String getDomain_emails() {
        return domain_emails;
    }


    /**
     * Sets the domain_emails value for this SrQaEmailOutVar1.
     * 
     * @param domain_emails
     */
    public void setDomain_emails(java.lang.String domain_emails) {
        this.domain_emails = domain_emails;
    }


    /**
     * Gets the suffixdomain_emails value for this SrQaEmailOutVar1.
     * 
     * @return suffixdomain_emails
     */
    public java.lang.String getSuffixdomain_emails() {
        return suffixdomain_emails;
    }


    /**
     * Sets the suffixdomain_emails value for this SrQaEmailOutVar1.
     * 
     * @param suffixdomain_emails
     */
    public void setSuffixdomain_emails(java.lang.String suffixdomain_emails) {
        this.suffixdomain_emails = suffixdomain_emails;
    }


    /**
     * Gets the countrydomain_emails value for this SrQaEmailOutVar1.
     * 
     * @return countrydomain_emails
     */
    public java.lang.String getCountrydomain_emails() {
        return countrydomain_emails;
    }


    /**
     * Sets the countrydomain_emails value for this SrQaEmailOutVar1.
     * 
     * @param countrydomain_emails
     */
    public void setCountrydomain_emails(java.lang.String countrydomain_emails) {
        this.countrydomain_emails = countrydomain_emails;
    }


    /**
     * Gets the email_emails value for this SrQaEmailOutVar1.
     * 
     * @return email_emails
     */
    public java.lang.String getEmail_emails() {
        return email_emails;
    }


    /**
     * Sets the email_emails value for this SrQaEmailOutVar1.
     * 
     * @param email_emails
     */
    public void setEmail_emails(java.lang.String email_emails) {
        this.email_emails = email_emails;
    }


    /**
     * Gets the unhandledpattern_emails value for this SrQaEmailOutVar1.
     * 
     * @return unhandledpattern_emails
     */
    public java.lang.String getUnhandledpattern_emails() {
        return unhandledpattern_emails;
    }


    /**
     * Sets the unhandledpattern_emails value for this SrQaEmailOutVar1.
     * 
     * @param unhandledpattern_emails
     */
    public void setUnhandledpattern_emails(java.lang.String unhandledpattern_emails) {
        this.unhandledpattern_emails = unhandledpattern_emails;
    }


    /**
     * Gets the status value for this SrQaEmailOutVar1.
     * 
     * @return status
     */
    public java.lang.Integer getStatus() {
        return status;
    }


    /**
     * Sets the status value for this SrQaEmailOutVar1.
     * 
     * @param status
     */
    public void setStatus(java.lang.Integer status) {
        this.status = status;
    }


    /**
     * Gets the mensaje value for this SrQaEmailOutVar1.
     * 
     * @return mensaje
     */
    public java.lang.String getMensaje() {
        return mensaje;
    }


    /**
     * Sets the mensaje value for this SrQaEmailOutVar1.
     * 
     * @param mensaje
     */
    public void setMensaje(java.lang.String mensaje) {
        this.mensaje = mensaje;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SrQaEmailOutVar1)) return false;
        SrQaEmailOutVar1 other = (SrQaEmailOutVar1) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.nameemail_emails==null && other.getNameemail_emails()==null) || 
             (this.nameemail_emails!=null &&
              this.nameemail_emails.equals(other.getNameemail_emails()))) &&
            ((this.prefixdomain_emails==null && other.getPrefixdomain_emails()==null) || 
             (this.prefixdomain_emails!=null &&
              this.prefixdomain_emails.equals(other.getPrefixdomain_emails()))) &&
            ((this.domain_emails==null && other.getDomain_emails()==null) || 
             (this.domain_emails!=null &&
              this.domain_emails.equals(other.getDomain_emails()))) &&
            ((this.suffixdomain_emails==null && other.getSuffixdomain_emails()==null) || 
             (this.suffixdomain_emails!=null &&
              this.suffixdomain_emails.equals(other.getSuffixdomain_emails()))) &&
            ((this.countrydomain_emails==null && other.getCountrydomain_emails()==null) || 
             (this.countrydomain_emails!=null &&
              this.countrydomain_emails.equals(other.getCountrydomain_emails()))) &&
            ((this.email_emails==null && other.getEmail_emails()==null) || 
             (this.email_emails!=null &&
              this.email_emails.equals(other.getEmail_emails()))) &&
            ((this.unhandledpattern_emails==null && other.getUnhandledpattern_emails()==null) || 
             (this.unhandledpattern_emails!=null &&
              this.unhandledpattern_emails.equals(other.getUnhandledpattern_emails()))) &&
            ((this.status==null && other.getStatus()==null) || 
             (this.status!=null &&
              this.status.equals(other.getStatus()))) &&
            ((this.mensaje==null && other.getMensaje()==null) || 
             (this.mensaje!=null &&
              this.mensaje.equals(other.getMensaje())));
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
        if (getNameemail_emails() != null) {
            _hashCode += getNameemail_emails().hashCode();
        }
        if (getPrefixdomain_emails() != null) {
            _hashCode += getPrefixdomain_emails().hashCode();
        }
        if (getDomain_emails() != null) {
            _hashCode += getDomain_emails().hashCode();
        }
        if (getSuffixdomain_emails() != null) {
            _hashCode += getSuffixdomain_emails().hashCode();
        }
        if (getCountrydomain_emails() != null) {
            _hashCode += getCountrydomain_emails().hashCode();
        }
        if (getEmail_emails() != null) {
            _hashCode += getEmail_emails().hashCode();
        }
        if (getUnhandledpattern_emails() != null) {
            _hashCode += getUnhandledpattern_emails().hashCode();
        }
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        if (getMensaje() != null) {
            _hashCode += getMensaje().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SrQaEmailOutVar1.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://SrQaEmail.QA_Equivida.isd.ibm.com", "SrQaEmailOutVar1"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nameemail_emails");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nameemail_emails"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("prefixdomain_emails");
        elemField.setXmlName(new javax.xml.namespace.QName("", "prefixdomain_emails"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("domain_emails");
        elemField.setXmlName(new javax.xml.namespace.QName("", "domain_emails"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("suffixdomain_emails");
        elemField.setXmlName(new javax.xml.namespace.QName("", "suffixdomain_emails"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("countrydomain_emails");
        elemField.setXmlName(new javax.xml.namespace.QName("", "countrydomain_emails"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("email_emails");
        elemField.setXmlName(new javax.xml.namespace.QName("", "email_emails"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("unhandledpattern_emails");
        elemField.setXmlName(new javax.xml.namespace.QName("", "unhandledpattern_emails"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("", "status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mensaje");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mensaje"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
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
