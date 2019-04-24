package domain;

import domain.CtrlPartida;
import domain.CtrlDominioProblema;

public class CtrlDominio {
	private CtrlProblema CDprob; 
	private CtrlPartida CDpart;
	
	public CtrlDominio() {
		CDprob= new CtrlProblema();
		CDpart= new CtrlPartida();
	}
}
