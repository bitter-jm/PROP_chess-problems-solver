package domain;
/**
 * Clase de Ctrl de Dominio
 */
import domain.CtrlPartida;
import domain.CtrlProblema;

public class CtrlDominio {
	private CtrlProblema CDprob; 
	private CtrlPartida CDpart;
	/**
	 * Crea los otros dos controladores de dominio: CtrlPartida y CtrlProblema
	 * como clases de agregacion
	 */
	public CtrlDominio() {
		CDprob = CtrlProblema.getInstance();
		CDpart= CtrlPartida.getInstance();
	}
}
