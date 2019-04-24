package domain;

import domain.CtrlPartida;
import domain.CtrlDominioProblema;

public class CtrlDominio {
	private CtrlDominioProblema CDprob; 
	private CtrlPartida CDpart;
	
	public CtrlDominio() {
		CDprob= new CtrlDominioProblema();
		CDpart= new CtrlPartida();
	}
}
