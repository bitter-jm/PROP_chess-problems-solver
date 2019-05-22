package domain;

import java.util.*;
import domain.CtrlDB;
import domain.Jugador;

public class CtrlEvaluador {
	
	private static CtrlEvaluador single_instance = null;
	private CtrlPartida ctrlPa = CtrlPartida.getInstance();
	private CtrlDB ctrlDB = CtrlDB.getInstance();
	String ganador;
	
	public static CtrlEvaluador getInstance() { 
		if (single_instance == null) 
			single_instance = new CtrlEvaluador(); 
		return single_instance; 
	}
	
	private CtrlEvaluador() {}
	
	// maquina1 y maquina2 pueden ser "MAQUINA1" o "MAQUINA2"
	public List<String> evaluarProblemas(List<String> problemas, String maquina1, String maquina2) {
		Jugador m1 = ctrlDB.getJugador(maquina1);
		Jugador m2 = ctrlDB.getJugador(maquina2);
		List<String> ganadores = new ArrayList<String>();
		for (String problema : problemas) {
			Problema p = ctrlDB.getProblema(problema);
			Partida partida = new Partida(m1, m2, p, false, true);
			ganadores.add(this.ganador);
			
		}
		return ganadores;
	}
	
	public void setGanador(String ganador) {
		this.ganador = ganador;
	}

}
