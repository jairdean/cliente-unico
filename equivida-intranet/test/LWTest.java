import com.equivida.dto.GeocodeAddressLW;
import com.equivida.intranet.ctrl.PersonaNaturalCtrl;



public class LWTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
	System.out.println("aqui");
	GeocodeAddressLW g=	new PersonaNaturalCtrl().encontrarGeocode("Quito", "Julio Zaldumbide", "Miravalle");

	System.out.println(g);
	 
	}

}
