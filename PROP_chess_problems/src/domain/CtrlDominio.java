package domain;

import domain.CtrlDominioPartida;
import domain.CtrlDominioProblema;

public class CtrlDominio {
	private CtrlDominioProblema CDprob; 
	private CtrlDominioPartida CDpart;
	
	public CtrlDominio() {
		CDprob= new CtrlDominioProblema();
		CDpart= new CtrlDominioPartida();
	}
}
