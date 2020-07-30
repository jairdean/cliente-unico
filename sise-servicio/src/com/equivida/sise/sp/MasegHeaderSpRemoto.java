package com.equivida.sise.sp;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;

import javax.ejb.Remote;

import com.equivida.sise.rs.MasegHeader;

@Remote
public interface MasegHeaderSpRemoto {

	MasegHeader llamarMasegHeaderSp(BigDecimal idPersona,
			BigDecimal codFiguraAseg, BigDecimal codTipoAseg,
			BigDecimal codImpAseg, BigDecimal codTipoAgente,
			BigDecimal codAgente, Date fechaAlta, Date fechaBaja,
			BigDecimal codigoOcupacion, Integer avisoVto, String codAsegVinc,
			Date fechaUltMod, String codUsuario, String nombFactura,
			BigDecimal tazaFianzas, Integer consorcio, BigDecimal codMoneda,
			BigDecimal impSueldo, BigDecimal codDeporte, String edificio,
			String urbanizacion, String sector, String nombresConyuge,
			String apellidoConyuge, BigDecimal codTipoDocConyuge,
			String numeroDocConyuge, String campoIn1, String campoIn2,
			String campoIn3, String campoIn4, String campoIn5)
			throws SQLException;

}