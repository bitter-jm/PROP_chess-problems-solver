package domain;

import domain.CtrlPartida;
import domain.CtrlProblema;

public class CtrlDominio {
	private CtrlProblema CDprob; 
	private CtrlPartida CDpart;
	
	public CtrlDominio() {
		CDprob = CtrlProblema.getInstance();
		CDpart= CtrlPartida.getInstance();
	}
}
