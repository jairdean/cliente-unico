package com.equivida.dao.ejb;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Query;

import com.equivida.dao.BeneficiarioDao;
import com.equivida.dao.BeneficiarioPolizaDao;
import com.equivida.modelo.Beneficiario;
import com.equivida.modelo.BeneficiarioPoliza;
import com.equivida.modelo.Interviniente;
import com.equivida.modelo.TipoParentescoRelacion;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Stateless(name = "BeneficiarioPolizaDao")
public class BeneficiarioPolizaDaoEjb extends
		GenericDaoEjb<BeneficiarioPoliza, Integer> implements
		BeneficiarioPolizaDao {

	@EJB
	private BeneficiarioDao beneficiarioDao;

	public BeneficiarioPolizaDaoEjb() {
		super(BeneficiarioPoliza.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<BeneficiarioPoliza> obtenerPorSecPersona(
			Integer secPersona) {
		List<Object> objList;
		Query query = em
				.createNativeQuery("select beneficiar0_.SEC_BENEFICIARIO_POLIZA, beneficiar0_.SEC_BENEFICIARIO, beneficiar0_.SEC_INTERVINIENTE, beneficiar0_.PCT_BENEFICIO, beneficiar0_.TIPO_BENEFICIARIO, beneficiar0_.COD_TIPO_PARENTESCO from EQUIVIDA.BENEFICIARIO_POLIZA beneficiar0_, EQUIVIDA.INTERVINIENTE intervinie1_ where beneficiar0_.SEC_INTERVINIENTE=intervinie1_.SEC_INTERVINIENTE and 1=1 and intervinie1_.SEC_PERSONA="
						+ secPersona.toString());

		objList = query.getResultList();

		List<BeneficiarioPoliza> listaFinal = new ArrayList<BeneficiarioPoliza>();

		for (Iterator<Object> iterator = objList.iterator(); iterator.hasNext();) {
			Object[] obj = (Object[]) iterator.next();
			BeneficiarioPoliza bp = new BeneficiarioPoliza();
			bp.setSecBeneficiarioPoliza(Integer.parseInt(obj[0].toString()));

			Beneficiario b = beneficiarioDao.get(Integer.parseInt(obj[1]
					.toString()));
			bp.setBeneficiario(b);

			Interviniente i = new Interviniente(Integer.parseInt(obj[2]
					.toString()));

			bp.setInterviniente(i);

			bp.setPctBeneficio(new BigDecimal(obj[3].toString()));
			bp.setTipoBeneficiario(obj[4].toString().charAt(0));

			TipoParentescoRelacion tpr = new TipoParentescoRelacion(
					Short.parseShort(obj[5].toString()));
			bp.setTipoParentescoRelacion(tpr);

			listaFinal.add(bp);
		}
		return listaFinal;
	}
}
