/**
 * Request.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.equivida.www.services.clienteequivida.mpersona_juridica;

public class Request  implements java.io.Serializable {
    private int id_persona;

    private int cod_tipo_soc;

    private int cod_sector_mercado;

    private int cod_actividad;

    private java.lang.String txt_nombre_contacto;

    private java.util.Date fec_contitucion;

    private java.lang.String txt_cuidad_const;

    private int cnt_duracion_empresa;

    private int imp_capital_social;

    private java.lang.String txt_sector;

    private java.lang.String txt_urbanizacion;

    private java.lang.String txt_edificio;

    private int cod_tipo_empresa;

    private java.lang.String txt_nombre_gerente;

    private int cnt_tiempo_mercado;

    private int imp_prom_ing_mensual;

    private java.lang.String txt_nombres_rep_legal;

    private java.lang.String txt_apellidos_rep_legal;

    private int cod_tipo_doc_rep_legal;

    private java.lang.String nro_doc_rep_legal;

    private java.lang.String sn_pep;

    private java.lang.String sn_relacion_laboral_pep;

    private java.lang.String txt_nombre_institucion_pep;

    private int cod_ingreso_pm;

    public Request() {
    }

    public Request(
           int id_persona,
           int cod_tipo_soc,
           int cod_sector_mercado,
           int cod_actividad,
           java.lang.String txt_nombre_contacto,
           java.util.Date fec_contitucion,
           java.lang.String txt_cuidad_const,
           int cnt_duracion_empresa,
           int imp_capital_social,
           java.lang.String txt_sector,
           java.lang.String txt_urbanizacion,
           java.lang.String txt_edificio,
           int cod_tipo_empresa,
           java.lang.String txt_nombre_gerente,
           int cnt_tiempo_mercado,
           int imp_prom_ing_mensual,
           java.lang.String txt_nombres_rep_legal,
           java.lang.String txt_apellidos_rep_legal,
           int cod_tipo_doc_rep_legal,
           java.lang.String nro_doc_rep_legal,
           java.lang.String sn_pep,
           java.lang.String sn_relacion_laboral_pep,
           java.lang.String txt_nombre_institucion_pep,
           int cod_ingreso_pm) {
           this.id_persona = id_persona;
           this.cod_tipo_soc = cod_tipo_soc;
           this.cod_sector_mercado = cod_sector_mercado;
           this.cod_actividad = cod_actividad;
           this.txt_nombre_contacto = txt_nombre_contacto;
           this.fec_contitucion = fec_contitucion;
           this.txt_cuidad_const = txt_cuidad_const;
           this.cnt_duracion_empresa = cnt_duracion_empresa;
           this.imp_capital_social = imp_capital_social;
           this.txt_sector = txt_sector;
           this.txt_urbanizacion = txt_urbanizacion;
           this.txt_edificio = txt_edificio;
           this.cod_tipo_empresa = cod_tipo_empresa;
           this.txt_nombre_gerente = txt_nombre_gerente;
           this.cnt_tiempo_mercado = cnt_tiempo_mercado;
           this.imp_prom_ing_mensual = imp_prom_ing_mensual;
           this.txt_nombres_rep_legal = txt_nombres_rep_legal;
           this.txt_apellidos_rep_legal = txt_apellidos_rep_legal;
           this.cod_tipo_doc_rep_legal = cod_tipo_doc_rep_legal;
           this.nro_doc_rep_legal = nro_doc_rep_legal;
           this.sn_pep = sn_pep;
           this.sn_relacion_laboral_pep = sn_relacion_laboral_pep;
           this.txt_nombre_institucion_pep = txt_nombre_institucion_pep;
           this.cod_ingreso_pm = cod_ingreso_pm;
    }


    /**
     * Gets the id_persona value for this Request.
     * 
     * @return id_persona
     */
    public int getId_persona() {
        return id_persona;
    }


    /**
     * Sets the id_persona value for this Request.
     * 
     * @param id_persona
     */
    public void setId_persona(int id_persona) {
        this.id_persona = id_persona;
    }


    /**
     * Gets the cod_tipo_soc value for this Request.
     * 
     * @return cod_tipo_soc
     */
    public int getCod_tipo_soc() {
        return cod_tipo_soc;
    }


    /**
     * Sets the cod_tipo_soc value for this Request.
     * 
     * @param cod_tipo_soc
     */
    public void setCod_tipo_soc(int cod_tipo_soc) {
        this.cod_tipo_soc = cod_tipo_soc;
    }


    /**
     * Gets the cod_sector_mercado value for this Request.
     * 
     * @return cod_sector_mercado
     */
    public int getCod_sector_mercado() {
        return cod_sector_mercado;
    }


    /**
     * Sets the cod_sector_mercado value for this Request.
     * 
     * @param cod_sector_mercado
     */
    public void setCod_sector_mercado(int cod_sector_mercado) {
        this.cod_sector_mercado = cod_sector_mercado;
    }


    /**
     * Gets the cod_actividad value for this Request.
     * 
     * @return cod_actividad
     */
    public int getCod_actividad() {
        return cod_actividad;
    }


    /**
     * Sets the cod_actividad value for this Request.
     * 
     * @param cod_actividad
     */
    public void setCod_actividad(int cod_actividad) {
        this.cod_actividad = cod_actividad;
    }


    /**
     * Gets the txt_nombre_contacto value for this Request.
     * 
     * @return txt_nombre_contacto
     */
    public java.lang.String getTxt_nombre_contacto() {
        return txt_nombre_contacto;
    }


    /**
     * Sets the txt_nombre_contacto value for this Request.
     * 
     * @param txt_nombre_contacto
     */
    public void setTxt_nombre_contacto(java.lang.String txt_nombre_contacto) {
        this.txt_nombre_contacto = txt_nombre_contacto;
    }


    /**
     * Gets the fec_contitucion value for this Request.
     * 
     * @return fec_contitucion
     */
    public java.util.Date getFec_contitucion() {
        return fec_contitucion;
    }


    /**
     * Sets the fec_contitucion value for this Request.
     * 
     * @param fec_contitucion
     */
    public void setFec_contitucion(java.util.Date fec_contitucion) {
        this.fec_contitucion = fec_contitucion;
    }


    /**
     * Gets the txt_cuidad_const value for this Request.
     * 
     * @return txt_cuidad_const
     */
    public java.lang.String getTxt_cuidad_const() {
        return txt_cuidad_const;
    }


    /**
     * Sets the txt_cuidad_const value for this Request.
     * 
     * @param txt_cuidad_const
     */
    public void setTxt_cuidad_const(java.lang.String txt_cuidad_const) {
        this.txt_cuidad_const = txt_cuidad_const;
    }


    /**
     * Gets the cnt_duracion_empresa value for this Request.
     * 
     * @return cnt_duracion_empresa
     */
    public int getCnt_duracion_empresa() {
        return cnt_duracion_empresa;
    }


    /**
     * Sets the cnt_duracion_empresa value for this Request.
     * 
     * @param cnt_duracion_empresa
     */
    public void setCnt_duracion_empresa(int cnt_duracion_empresa) {
        this.cnt_duracion_empresa = cnt_duracion_empresa;
    }


    /**
     * Gets the imp_capital_social value for this Request.
     * 
     * @return imp_capital_social
     */
    public int getImp_capital_social() {
        return imp_capital_social;
    }


    /**
     * Sets the imp_capital_social value for this Request.
     * 
     * @param imp_capital_social
     */
    public void setImp_capital_social(int imp_capital_social) {
        this.imp_capital_social = imp_capital_social;
    }


    /**
     * Gets the txt_sector value for this Request.
     * 
     * @return txt_sector
     */
    public java.lang.String getTxt_sector() {
        return txt_sector;
    }


    /**
     * Sets the txt_sector value for this Request.
     * 
     * @param txt_sector
     */
    public void setTxt_sector(java.lang.String txt_sector) {
        this.txt_sector = txt_sector;
    }


    /**
     * Gets the txt_urbanizacion value for this Request.
     * 
     * @return txt_urbanizacion
     */
    public java.lang.String getTxt_urbanizacion() {
        return txt_urbanizacion;
    }


    /**
     * Sets the txt_urbanizacion value for this Request.
     * 
     * @param txt_urbanizacion
     */
    public void setTxt_urbanizacion(java.lang.String txt_urbanizacion) {
        this.txt_urbanizacion = txt_urbanizacion;
    }


    /**
     * Gets the txt_edificio value for this Request.
     * 
     * @return txt_edificio
     */
    public java.lang.String getTxt_edificio() {
        return txt_edificio;
    }


    /**
     * Sets the txt_edificio value for this Request.
     * 
     * @param txt_edificio
     */
    public void setTxt_edificio(java.lang.String txt_edificio) {
        this.txt_edificio = txt_edificio;
    }


    /**
     * Gets the cod_tipo_empresa value for this Request.
     * 
     * @return cod_tipo_empresa
     */
    public int getCod_tipo_empresa() {
        return cod_tipo_empresa;
    }


    /**
     * Sets the cod_tipo_empresa value for this Request.
     * 
     * @param cod_tipo_empresa
     */
    public void setCod_tipo_empresa(int cod_tipo_empresa) {
        this.cod_tipo_empresa = cod_tipo_empresa;
    }


    /**
     * Gets the txt_nombre_gerente value for this Request.
     * 
     * @return txt_nombre_gerente
     */
    public java.lang.String getTxt_nombre_gerente() {
        return txt_nombre_gerente;
    }


    /**
     * Sets the txt_nombre_gerente value for this Request.
     * 
     * @param txt_nombre_gerente
     */
    public void setTxt_nombre_gerente(java.lang.String txt_nombre_gerente) {
        this.txt_nombre_gerente = txt_nombre_gerente;
    }


    /**
     * Gets the cnt_tiempo_mercado value for this Request.
     * 
     * @return cnt_tiempo_mercado
     */
    public int getCnt_tiempo_mercado() {
        return cnt_tiempo_mercado;
    }


    /**
     * Sets the cnt_tiempo_mercado value for this Request.
     * 
     * @param cnt_tiempo_mercado
     */
    public void setCnt_tiempo_mercado(int cnt_tiempo_mercado) {
        this.cnt_tiempo_mercado = cnt_tiempo_mercado;
    }


    /**
     * Gets the imp_prom_ing_mensual value for this Request.
     * 
     * @return imp_prom_ing_mensual
     */
    public int getImp_prom_ing_mensual() {
        return imp_prom_ing_mensual;
    }


    /**
     * Sets the imp_prom_ing_mensual value for this Request.
     * 
     * @param imp_prom_ing_mensual
     */
    public void setImp_prom_ing_mensual(int imp_prom_ing_mensual) {
        this.imp_prom_ing_mensual = imp_prom_ing_mensual;
    }


    /**
     * Gets the txt_nombres_rep_legal value for this Request.
     * 
     * @return txt_nombres_rep_legal
     */
    public java.lang.String getTxt_nombres_rep_legal() {
        return txt_nombres_rep_legal;
    }


    /**
     * Sets the txt_nombres_rep_legal value for this Request.
     * 
     * @param txt_nombres_rep_legal
     */
    public void setTxt_nombres_rep_legal(java.lang.String txt_nombres_rep_legal) {
        this.txt_nombres_rep_legal = txt_nombres_rep_legal;
    }


    /**
     * Gets the txt_apellidos_rep_legal value for this Request.
     * 
     * @return txt_apellidos_rep_legal
     */
    public java.lang.String getTxt_apellidos_rep_legal() {
        return txt_apellidos_rep_legal;
    }


    /**
     * Sets the txt_apellidos_rep_legal value for this Request.
     * 
     * @param txt_apellidos_rep_legal
     */
    public void setTxt_apellidos_rep_legal(java.lang.String txt_apellidos_rep_legal) {
        this.txt_apellidos_rep_legal = txt_apellidos_rep_legal;
    }


    /**
     * Gets the cod_tipo_doc_rep_legal value for this Request.
     * 
     * @return cod_tipo_doc_rep_legal
     */
    public int getCod_tipo_doc_rep_legal() {
        return cod_tipo_doc_rep_legal;
    }


    /**
     * Sets the cod_tipo_doc_rep_legal value for this Request.
     * 
     * @param cod_tipo_doc_rep_legal
     */
    public void setCod_tipo_doc_rep_legal(int cod_tipo_doc_rep_legal) {
        this.cod_tipo_doc_rep_legal = cod_tipo_doc_rep_legal;
    }


    /**
     * Gets the nro_doc_rep_legal value for this Request.
     * 
     * @return nro_doc_rep_legal
     */
    public java.lang.String getNro_doc_rep_legal() {
        return nro_doc_rep_legal;
    }


    /**
     * Sets the nro_doc_rep_legal value for this Request.
     * 
     * @param nro_doc_rep_legal
     */
    public void setNro_doc_rep_legal(java.lang.String nro_doc_rep_legal) {
        this.nro_doc_rep_legal = nro_doc_rep_legal;
    }


    /**
     * Gets the sn_pep value for this Request.
     * 
     * @return sn_pep
     */
    public java.lang.String getSn_pep() {
        return sn_pep;
    }


    /**
     * Sets the sn_pep value for this Request.
     * 
     * @param sn_pep
     */
    public void setSn_pep(java.lang.String sn_pep) {
        this.sn_pep = sn_pep;
    }


    /**
     * Gets the sn_relacion_laboral_pep value for this Request.
     * 
     * @return sn_relacion_laboral_pep
     */
    public java.lang.String getSn_relacion_laboral_pep() {
        return sn_relacion_laboral_pep;
    }


    /**
     * Sets the sn_relacion_laboral_pep value for this Request.
     * 
     * @param sn_relacion_laboral_pep
     */
    public void setSn_relacion_laboral_pep(java.lang.String sn_relacion_laboral_pep) {
        this.sn_relacion_laboral_pep = sn_relacion_laboral_pep;
    }


    /**
     * Gets the txt_nombre_institucion_pep value for this Request.
     * 
     * @return txt_nombre_institucion_pep
     */
    public java.lang.String getTxt_nombre_institucion_pep() {
        return txt_nombre_institucion_pep;
    }


    /**
     * Sets the txt_nombre_institucion_pep value for this Request.
     * 
     * @param txt_nombre_institucion_pep
     */
    public void setTxt_nombre_institucion_pep(java.lang.String txt_nombre_institucion_pep) {
        this.txt_nombre_institucion_pep = txt_nombre_institucion_pep;
    }


    /**
     * Gets the cod_ingreso_pm value for this Request.
     * 
     * @return cod_ingreso_pm
     */
    public int getCod_ingreso_pm() {
        return cod_ingreso_pm;
    }


    /**
     * Sets the cod_ingreso_pm value for this Request.
     * 
     * @param cod_ingreso_pm
     */
    public void setCod_ingreso_pm(int cod_ingreso_pm) {
        this.cod_ingreso_pm = cod_ingreso_pm;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Request)) return false;
        Request other = (Request) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.id_persona == other.getId_persona() &&
            this.cod_tipo_soc == other.getCod_tipo_soc() &&
            this.cod_sector_mercado == other.getCod_sector_mercado() &&
            this.cod_actividad == other.getCod_actividad() &&
            ((this.txt_nombre_contacto==null && other.getTxt_nombre_contacto()==null) || 
             (this.txt_nombre_contacto!=null &&
              this.txt_nombre_contacto.equals(other.getTxt_nombre_contacto()))) &&
            ((this.fec_contitucion==null && other.getFec_contitucion()==null) || 
             (this.fec_contitucion!=null &&
              this.fec_contitucion.equals(other.getFec_contitucion()))) &&
            ((this.txt_cuidad_const==null && other.getTxt_cuidad_const()==null) || 
             (this.txt_cuidad_const!=null &&
              this.txt_cuidad_const.equals(other.getTxt_cuidad_const()))) &&
            this.cnt_duracion_empresa == other.getCnt_duracion_empresa() &&
            this.imp_capital_social == other.getImp_capital_social() &&
            ((this.txt_sector==null && other.getTxt_sector()==null) || 
             (this.txt_sector!=null &&
              this.txt_sector.equals(other.getTxt_sector()))) &&
            ((this.txt_urbanizacion==null && other.getTxt_urbanizacion()==null) || 
             (this.txt_urbanizacion!=null &&
              this.txt_urbanizacion.equals(other.getTxt_urbanizacion()))) &&
            ((this.txt_edificio==null && other.getTxt_edificio()==null) || 
             (this.txt_edificio!=null &&
              this.txt_edificio.equals(other.getTxt_edificio()))) &&
            this.cod_tipo_empresa == other.getCod_tipo_empresa() &&
            ((this.txt_nombre_gerente==null && other.getTxt_nombre_gerente()==null) || 
             (this.txt_nombre_gerente!=null &&
              this.txt_nombre_gerente.equals(other.getTxt_nombre_gerente()))) &&
            this.cnt_tiempo_mercado == other.getCnt_tiempo_mercado() &&
            this.imp_prom_ing_mensual == other.getImp_prom_ing_mensual() &&
            ((this.txt_nombres_rep_legal==null && other.getTxt_nombres_rep_legal()==null) || 
             (this.txt_nombres_rep_legal!=null &&
              this.txt_nombres_rep_legal.equals(other.getTxt_nombres_rep_legal()))) &&
            ((this.txt_apellidos_rep_legal==null && other.getTxt_apellidos_rep_legal()==null) || 
             (this.txt_apellidos_rep_legal!=null &&
              this.txt_apellidos_rep_legal.equals(other.getTxt_apellidos_rep_legal()))) &&
            this.cod_tipo_doc_rep_legal == other.getCod_tipo_doc_rep_legal() &&
            ((this.nro_doc_rep_legal==null && other.getNro_doc_rep_legal()==null) || 
             (this.nro_doc_rep_legal!=null &&
              this.nro_doc_rep_legal.equals(other.getNro_doc_rep_legal()))) &&
            ((this.sn_pep==null && other.getSn_pep()==null) || 
             (this.sn_pep!=null &&
              this.sn_pep.equals(other.getSn_pep()))) &&
            ((this.sn_relacion_laboral_pep==null && other.getSn_relacion_laboral_pep()==null) || 
             (this.sn_relacion_laboral_pep!=null &&
              this.sn_relacion_laboral_pep.equals(other.getSn_relacion_laboral_pep()))) &&
            ((this.txt_nombre_institucion_pep==null && other.getTxt_nombre_institucion_pep()==null) || 
             (this.txt_nombre_institucion_pep!=null &&
              this.txt_nombre_institucion_pep.equals(other.getTxt_nombre_institucion_pep()))) &&
            this.cod_ingreso_pm == other.getCod_ingreso_pm();
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
        _hashCode += getId_persona();
        _hashCode += getCod_tipo_soc();
        _hashCode += getCod_sector_mercado();
        _hashCode += getCod_actividad();
        if (getTxt_nombre_contacto() != null) {
            _hashCode += getTxt_nombre_contacto().hashCode();
        }
        if (getFec_contitucion() != null) {
            _hashCode += getFec_contitucion().hashCode();
        }
        if (getTxt_cuidad_const() != null) {
            _hashCode += getTxt_cuidad_const().hashCode();
        }
        _hashCode += getCnt_duracion_empresa();
        _hashCode += getImp_capital_social();
        if (getTxt_sector() != null) {
            _hashCode += getTxt_sector().hashCode();
        }
        if (getTxt_urbanizacion() != null) {
            _hashCode += getTxt_urbanizacion().hashCode();
        }
        if (getTxt_edificio() != null) {
            _hashCode += getTxt_edificio().hashCode();
        }
        _hashCode += getCod_tipo_empresa();
        if (getTxt_nombre_gerente() != null) {
            _hashCode += getTxt_nombre_gerente().hashCode();
        }
        _hashCode += getCnt_tiempo_mercado();
        _hashCode += getImp_prom_ing_mensual();
        if (getTxt_nombres_rep_legal() != null) {
            _hashCode += getTxt_nombres_rep_legal().hashCode();
        }
        if (getTxt_apellidos_rep_legal() != null) {
            _hashCode += getTxt_apellidos_rep_legal().hashCode();
        }
        _hashCode += getCod_tipo_doc_rep_legal();
        if (getNro_doc_rep_legal() != null) {
            _hashCode += getNro_doc_rep_legal().hashCode();
        }
        if (getSn_pep() != null) {
            _hashCode += getSn_pep().hashCode();
        }
        if (getSn_relacion_laboral_pep() != null) {
            _hashCode += getSn_relacion_laboral_pep().hashCode();
        }
        if (getTxt_nombre_institucion_pep() != null) {
            _hashCode += getTxt_nombre_institucion_pep().hashCode();
        }
        _hashCode += getCod_ingreso_pm();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Request.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.equivida.com/services/clienteequivida/mpersona_juridica", ">request"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id_persona");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.equivida.com/services/clienteequivida/mpersona_juridica", "id_persona"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cod_tipo_soc");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.equivida.com/services/clienteequivida/mpersona_juridica", "cod_tipo_soc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cod_sector_mercado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.equivida.com/services/clienteequivida/mpersona_juridica", "cod_sector_mercado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cod_actividad");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.equivida.com/services/clienteequivida/mpersona_juridica", "cod_actividad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("txt_nombre_contacto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.equivida.com/services/clienteequivida/mpersona_juridica", "txt_nombre_contacto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fec_contitucion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.equivida.com/services/clienteequivida/mpersona_juridica", "fec_contitucion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("txt_cuidad_const");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.equivida.com/services/clienteequivida/mpersona_juridica", "txt_cuidad_const"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cnt_duracion_empresa");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.equivida.com/services/clienteequivida/mpersona_juridica", "cnt_duracion_empresa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("imp_capital_social");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.equivida.com/services/clienteequivida/mpersona_juridica", "imp_capital_social"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("txt_sector");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.equivida.com/services/clienteequivida/mpersona_juridica", "txt_sector"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("txt_urbanizacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.equivida.com/services/clienteequivida/mpersona_juridica", "txt_urbanizacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("txt_edificio");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.equivida.com/services/clienteequivida/mpersona_juridica", "txt_edificio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cod_tipo_empresa");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.equivida.com/services/clienteequivida/mpersona_juridica", "cod_tipo_empresa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("txt_nombre_gerente");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.equivida.com/services/clienteequivida/mpersona_juridica", "txt_nombre_gerente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cnt_tiempo_mercado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.equivida.com/services/clienteequivida/mpersona_juridica", "cnt_tiempo_mercado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("imp_prom_ing_mensual");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.equivida.com/services/clienteequivida/mpersona_juridica", "imp_prom_ing_mensual"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("txt_nombres_rep_legal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.equivida.com/services/clienteequivida/mpersona_juridica", "txt_nombres_rep_legal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("txt_apellidos_rep_legal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.equivida.com/services/clienteequivida/mpersona_juridica", "txt_apellidos_rep_legal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cod_tipo_doc_rep_legal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.equivida.com/services/clienteequivida/mpersona_juridica", "cod_tipo_doc_rep_legal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nro_doc_rep_legal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.equivida.com/services/clienteequivida/mpersona_juridica", "nro_doc_rep_legal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sn_pep");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.equivida.com/services/clienteequivida/mpersona_juridica", "sn_pep"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sn_relacion_laboral_pep");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.equivida.com/services/clienteequivida/mpersona_juridica", "sn_relacion_laboral_pep"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("txt_nombre_institucion_pep");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.equivida.com/services/clienteequivida/mpersona_juridica", "txt_nombre_institucion_pep"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cod_ingreso_pm");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.equivida.com/services/clienteequivida/mpersona_juridica", "cod_ingreso_pm"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
