/**
 * ScanFiles.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package localhost;

public class ScanFiles  implements java.io.Serializable {
    private java.lang.String idPolitica;

    private java.lang.String archivoInput;

    private java.lang.String archivoOutput;

    private java.lang.String formatoArchivoInput;

    private java.lang.String userName;

    private java.lang.String perfilUsuario;

    private java.lang.String archivoFormatos;

    private java.lang.String licenciaUsuario;

    private java.lang.String servidorBdd;

    private java.lang.String nombreBdd;

    private java.lang.String usuarioBdd;

    private java.lang.String passwordBdd;

    private java.lang.String nombreTablaBdd;

    private java.lang.Object[] columnasTablaBdd;

    private java.lang.String origenDatos;

    private boolean poseeCabecera;

    private java.lang.String tipoOrigenInf;

    private int codError;

    private java.lang.String descripcionError;

    public ScanFiles() {
    }

    public ScanFiles(
           java.lang.String idPolitica,
           java.lang.String archivoInput,
           java.lang.String archivoOutput,
           java.lang.String formatoArchivoInput,
           java.lang.String userName,
           java.lang.String perfilUsuario,
           java.lang.String archivoFormatos,
           java.lang.String licenciaUsuario,
           java.lang.String servidorBdd,
           java.lang.String nombreBdd,
           java.lang.String usuarioBdd,
           java.lang.String passwordBdd,
           java.lang.String nombreTablaBdd,
           java.lang.Object[] columnasTablaBdd,
           java.lang.String origenDatos,
           boolean poseeCabecera,
           java.lang.String tipoOrigenInf,
           int codError,
           java.lang.String descripcionError) {
           this.idPolitica = idPolitica;
           this.archivoInput = archivoInput;
           this.archivoOutput = archivoOutput;
           this.formatoArchivoInput = formatoArchivoInput;
           this.userName = userName;
           this.perfilUsuario = perfilUsuario;
           this.archivoFormatos = archivoFormatos;
           this.licenciaUsuario = licenciaUsuario;
           this.servidorBdd = servidorBdd;
           this.nombreBdd = nombreBdd;
           this.usuarioBdd = usuarioBdd;
           this.passwordBdd = passwordBdd;
           this.nombreTablaBdd = nombreTablaBdd;
           this.columnasTablaBdd = columnasTablaBdd;
           this.origenDatos = origenDatos;
           this.poseeCabecera = poseeCabecera;
           this.tipoOrigenInf = tipoOrigenInf;
           this.codError = codError;
           this.descripcionError = descripcionError;
    }


    /**
     * Gets the idPolitica value for this ScanFiles.
     * 
     * @return idPolitica
     */
    public java.lang.String getIdPolitica() {
        return idPolitica;
    }


    /**
     * Sets the idPolitica value for this ScanFiles.
     * 
     * @param idPolitica
     */
    public void setIdPolitica(java.lang.String idPolitica) {
        this.idPolitica = idPolitica;
    }


    /**
     * Gets the archivoInput value for this ScanFiles.
     * 
     * @return archivoInput
     */
    public java.lang.String getArchivoInput() {
        return archivoInput;
    }


    /**
     * Sets the archivoInput value for this ScanFiles.
     * 
     * @param archivoInput
     */
    public void setArchivoInput(java.lang.String archivoInput) {
        this.archivoInput = archivoInput;
    }


    /**
     * Gets the archivoOutput value for this ScanFiles.
     * 
     * @return archivoOutput
     */
    public java.lang.String getArchivoOutput() {
        return archivoOutput;
    }


    /**
     * Sets the archivoOutput value for this ScanFiles.
     * 
     * @param archivoOutput
     */
    public void setArchivoOutput(java.lang.String archivoOutput) {
        this.archivoOutput = archivoOutput;
    }


    /**
     * Gets the formatoArchivoInput value for this ScanFiles.
     * 
     * @return formatoArchivoInput
     */
    public java.lang.String getFormatoArchivoInput() {
        return formatoArchivoInput;
    }


    /**
     * Sets the formatoArchivoInput value for this ScanFiles.
     * 
     * @param formatoArchivoInput
     */
    public void setFormatoArchivoInput(java.lang.String formatoArchivoInput) {
        this.formatoArchivoInput = formatoArchivoInput;
    }


    /**
     * Gets the userName value for this ScanFiles.
     * 
     * @return userName
     */
    public java.lang.String getUserName() {
        return userName;
    }


    /**
     * Sets the userName value for this ScanFiles.
     * 
     * @param userName
     */
    public void setUserName(java.lang.String userName) {
        this.userName = userName;
    }


    /**
     * Gets the perfilUsuario value for this ScanFiles.
     * 
     * @return perfilUsuario
     */
    public java.lang.String getPerfilUsuario() {
        return perfilUsuario;
    }


    /**
     * Sets the perfilUsuario value for this ScanFiles.
     * 
     * @param perfilUsuario
     */
    public void setPerfilUsuario(java.lang.String perfilUsuario) {
        this.perfilUsuario = perfilUsuario;
    }


    /**
     * Gets the archivoFormatos value for this ScanFiles.
     * 
     * @return archivoFormatos
     */
    public java.lang.String getArchivoFormatos() {
        return archivoFormatos;
    }


    /**
     * Sets the archivoFormatos value for this ScanFiles.
     * 
     * @param archivoFormatos
     */
    public void setArchivoFormatos(java.lang.String archivoFormatos) {
        this.archivoFormatos = archivoFormatos;
    }


    /**
     * Gets the licenciaUsuario value for this ScanFiles.
     * 
     * @return licenciaUsuario
     */
    public java.lang.String getLicenciaUsuario() {
        return licenciaUsuario;
    }


    /**
     * Sets the licenciaUsuario value for this ScanFiles.
     * 
     * @param licenciaUsuario
     */
    public void setLicenciaUsuario(java.lang.String licenciaUsuario) {
        this.licenciaUsuario = licenciaUsuario;
    }


    /**
     * Gets the servidorBdd value for this ScanFiles.
     * 
     * @return servidorBdd
     */
    public java.lang.String getServidorBdd() {
        return servidorBdd;
    }


    /**
     * Sets the servidorBdd value for this ScanFiles.
     * 
     * @param servidorBdd
     */
    public void setServidorBdd(java.lang.String servidorBdd) {
        this.servidorBdd = servidorBdd;
    }


    /**
     * Gets the nombreBdd value for this ScanFiles.
     * 
     * @return nombreBdd
     */
    public java.lang.String getNombreBdd() {
        return nombreBdd;
    }


    /**
     * Sets the nombreBdd value for this ScanFiles.
     * 
     * @param nombreBdd
     */
    public void setNombreBdd(java.lang.String nombreBdd) {
        this.nombreBdd = nombreBdd;
    }


    /**
     * Gets the usuarioBdd value for this ScanFiles.
     * 
     * @return usuarioBdd
     */
    public java.lang.String getUsuarioBdd() {
        return usuarioBdd;
    }


    /**
     * Sets the usuarioBdd value for this ScanFiles.
     * 
     * @param usuarioBdd
     */
    public void setUsuarioBdd(java.lang.String usuarioBdd) {
        this.usuarioBdd = usuarioBdd;
    }


    /**
     * Gets the passwordBdd value for this ScanFiles.
     * 
     * @return passwordBdd
     */
    public java.lang.String getPasswordBdd() {
        return passwordBdd;
    }


    /**
     * Sets the passwordBdd value for this ScanFiles.
     * 
     * @param passwordBdd
     */
    public void setPasswordBdd(java.lang.String passwordBdd) {
        this.passwordBdd = passwordBdd;
    }


    /**
     * Gets the nombreTablaBdd value for this ScanFiles.
     * 
     * @return nombreTablaBdd
     */
    public java.lang.String getNombreTablaBdd() {
        return nombreTablaBdd;
    }


    /**
     * Sets the nombreTablaBdd value for this ScanFiles.
     * 
     * @param nombreTablaBdd
     */
    public void setNombreTablaBdd(java.lang.String nombreTablaBdd) {
        this.nombreTablaBdd = nombreTablaBdd;
    }


    /**
     * Gets the columnasTablaBdd value for this ScanFiles.
     * 
     * @return columnasTablaBdd
     */
    public java.lang.Object[] getColumnasTablaBdd() {
        return columnasTablaBdd;
    }


    /**
     * Sets the columnasTablaBdd value for this ScanFiles.
     * 
     * @param columnasTablaBdd
     */
    public void setColumnasTablaBdd(java.lang.Object[] columnasTablaBdd) {
        this.columnasTablaBdd = columnasTablaBdd;
    }


    /**
     * Gets the origenDatos value for this ScanFiles.
     * 
     * @return origenDatos
     */
    public java.lang.String getOrigenDatos() {
        return origenDatos;
    }


    /**
     * Sets the origenDatos value for this ScanFiles.
     * 
     * @param origenDatos
     */
    public void setOrigenDatos(java.lang.String origenDatos) {
        this.origenDatos = origenDatos;
    }


    /**
     * Gets the poseeCabecera value for this ScanFiles.
     * 
     * @return poseeCabecera
     */
    public boolean isPoseeCabecera() {
        return poseeCabecera;
    }


    /**
     * Sets the poseeCabecera value for this ScanFiles.
     * 
     * @param poseeCabecera
     */
    public void setPoseeCabecera(boolean poseeCabecera) {
        this.poseeCabecera = poseeCabecera;
    }


    /**
     * Gets the tipoOrigenInf value for this ScanFiles.
     * 
     * @return tipoOrigenInf
     */
    public java.lang.String getTipoOrigenInf() {
        return tipoOrigenInf;
    }


    /**
     * Sets the tipoOrigenInf value for this ScanFiles.
     * 
     * @param tipoOrigenInf
     */
    public void setTipoOrigenInf(java.lang.String tipoOrigenInf) {
        this.tipoOrigenInf = tipoOrigenInf;
    }


    /**
     * Gets the codError value for this ScanFiles.
     * 
     * @return codError
     */
    public int getCodError() {
        return codError;
    }


    /**
     * Sets the codError value for this ScanFiles.
     * 
     * @param codError
     */
    public void setCodError(int codError) {
        this.codError = codError;
    }


    /**
     * Gets the descripcionError value for this ScanFiles.
     * 
     * @return descripcionError
     */
    public java.lang.String getDescripcionError() {
        return descripcionError;
    }


    /**
     * Sets the descripcionError value for this ScanFiles.
     * 
     * @param descripcionError
     */
    public void setDescripcionError(java.lang.String descripcionError) {
        this.descripcionError = descripcionError;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ScanFiles)) return false;
        ScanFiles other = (ScanFiles) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.idPolitica==null && other.getIdPolitica()==null) || 
             (this.idPolitica!=null &&
              this.idPolitica.equals(other.getIdPolitica()))) &&
            ((this.archivoInput==null && other.getArchivoInput()==null) || 
             (this.archivoInput!=null &&
              this.archivoInput.equals(other.getArchivoInput()))) &&
            ((this.archivoOutput==null && other.getArchivoOutput()==null) || 
             (this.archivoOutput!=null &&
              this.archivoOutput.equals(other.getArchivoOutput()))) &&
            ((this.formatoArchivoInput==null && other.getFormatoArchivoInput()==null) || 
             (this.formatoArchivoInput!=null &&
              this.formatoArchivoInput.equals(other.getFormatoArchivoInput()))) &&
            ((this.userName==null && other.getUserName()==null) || 
             (this.userName!=null &&
              this.userName.equals(other.getUserName()))) &&
            ((this.perfilUsuario==null && other.getPerfilUsuario()==null) || 
             (this.perfilUsuario!=null &&
              this.perfilUsuario.equals(other.getPerfilUsuario()))) &&
            ((this.archivoFormatos==null && other.getArchivoFormatos()==null) || 
             (this.archivoFormatos!=null &&
              this.archivoFormatos.equals(other.getArchivoFormatos()))) &&
            ((this.licenciaUsuario==null && other.getLicenciaUsuario()==null) || 
             (this.licenciaUsuario!=null &&
              this.licenciaUsuario.equals(other.getLicenciaUsuario()))) &&
            ((this.servidorBdd==null && other.getServidorBdd()==null) || 
             (this.servidorBdd!=null &&
              this.servidorBdd.equals(other.getServidorBdd()))) &&
            ((this.nombreBdd==null && other.getNombreBdd()==null) || 
             (this.nombreBdd!=null &&
              this.nombreBdd.equals(other.getNombreBdd()))) &&
            ((this.usuarioBdd==null && other.getUsuarioBdd()==null) || 
             (this.usuarioBdd!=null &&
              this.usuarioBdd.equals(other.getUsuarioBdd()))) &&
            ((this.passwordBdd==null && other.getPasswordBdd()==null) || 
             (this.passwordBdd!=null &&
              this.passwordBdd.equals(other.getPasswordBdd()))) &&
            ((this.nombreTablaBdd==null && other.getNombreTablaBdd()==null) || 
             (this.nombreTablaBdd!=null &&
              this.nombreTablaBdd.equals(other.getNombreTablaBdd()))) &&
            ((this.columnasTablaBdd==null && other.getColumnasTablaBdd()==null) || 
             (this.columnasTablaBdd!=null &&
              java.util.Arrays.equals(this.columnasTablaBdd, other.getColumnasTablaBdd()))) &&
            ((this.origenDatos==null && other.getOrigenDatos()==null) || 
             (this.origenDatos!=null &&
              this.origenDatos.equals(other.getOrigenDatos()))) &&
            this.poseeCabecera == other.isPoseeCabecera() &&
            ((this.tipoOrigenInf==null && other.getTipoOrigenInf()==null) || 
             (this.tipoOrigenInf!=null &&
              this.tipoOrigenInf.equals(other.getTipoOrigenInf()))) &&
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
        if (getIdPolitica() != null) {
            _hashCode += getIdPolitica().hashCode();
        }
        if (getArchivoInput() != null) {
            _hashCode += getArchivoInput().hashCode();
        }
        if (getArchivoOutput() != null) {
            _hashCode += getArchivoOutput().hashCode();
        }
        if (getFormatoArchivoInput() != null) {
            _hashCode += getFormatoArchivoInput().hashCode();
        }
        if (getUserName() != null) {
            _hashCode += getUserName().hashCode();
        }
        if (getPerfilUsuario() != null) {
            _hashCode += getPerfilUsuario().hashCode();
        }
        if (getArchivoFormatos() != null) {
            _hashCode += getArchivoFormatos().hashCode();
        }
        if (getLicenciaUsuario() != null) {
            _hashCode += getLicenciaUsuario().hashCode();
        }
        if (getServidorBdd() != null) {
            _hashCode += getServidorBdd().hashCode();
        }
        if (getNombreBdd() != null) {
            _hashCode += getNombreBdd().hashCode();
        }
        if (getUsuarioBdd() != null) {
            _hashCode += getUsuarioBdd().hashCode();
        }
        if (getPasswordBdd() != null) {
            _hashCode += getPasswordBdd().hashCode();
        }
        if (getNombreTablaBdd() != null) {
            _hashCode += getNombreTablaBdd().hashCode();
        }
        if (getColumnasTablaBdd() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getColumnasTablaBdd());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getColumnasTablaBdd(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getOrigenDatos() != null) {
            _hashCode += getOrigenDatos().hashCode();
        }
        _hashCode += (isPoseeCabecera() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getTipoOrigenInf() != null) {
            _hashCode += getTipoOrigenInf().hashCode();
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
        new org.apache.axis.description.TypeDesc(ScanFiles.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://localhost/", ">ScanFiles"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idPolitica");
        elemField.setXmlName(new javax.xml.namespace.QName("http://localhost/", "idPolitica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("archivoInput");
        elemField.setXmlName(new javax.xml.namespace.QName("http://localhost/", "archivoInput"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("archivoOutput");
        elemField.setXmlName(new javax.xml.namespace.QName("http://localhost/", "archivoOutput"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("formatoArchivoInput");
        elemField.setXmlName(new javax.xml.namespace.QName("http://localhost/", "formatoArchivoInput"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://localhost/", "userName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("perfilUsuario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://localhost/", "perfilUsuario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("archivoFormatos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://localhost/", "archivoFormatos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("licenciaUsuario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://localhost/", "licenciaUsuario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("servidorBdd");
        elemField.setXmlName(new javax.xml.namespace.QName("http://localhost/", "servidorBdd"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreBdd");
        elemField.setXmlName(new javax.xml.namespace.QName("http://localhost/", "nombreBdd"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("usuarioBdd");
        elemField.setXmlName(new javax.xml.namespace.QName("http://localhost/", "usuarioBdd"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("passwordBdd");
        elemField.setXmlName(new javax.xml.namespace.QName("http://localhost/", "passwordBdd"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreTablaBdd");
        elemField.setXmlName(new javax.xml.namespace.QName("http://localhost/", "nombreTablaBdd"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("columnasTablaBdd");
        elemField.setXmlName(new javax.xml.namespace.QName("http://localhost/", "columnasTablaBdd"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://localhost/", "anyType"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("origenDatos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://localhost/", "origenDatos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("poseeCabecera");
        elemField.setXmlName(new javax.xml.namespace.QName("http://localhost/", "poseeCabecera"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoOrigenInf");
        elemField.setXmlName(new javax.xml.namespace.QName("http://localhost/", "tipoOrigenInf"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
