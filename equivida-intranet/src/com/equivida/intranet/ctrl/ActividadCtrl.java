package com.equivida.intranet.ctrl;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.equivida.intranet.util.BaseCtrl;
import com.equivida.servicio.ActividadServicio;

@ManagedBean(name = "actividadCtrl")
@ViewScoped
public class ActividadCtrl extends BaseCtrl {

	private static final long serialVersionUID = -5881190305440050683L;

	@EJB(mappedName = "ActividadServicio/local")
	private ActividadServicio actividadServicio;

	private String prueba;

	public String getPrueba() {
		if (prueba == null) {
			actividadServicio.findByPk(new Integer(2));
		}
		return prueba;
	}

	public void setPrueba(String prueba) {
		this.prueba = prueba;
	}

}
