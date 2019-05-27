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
	
	/**
	 * Evalua una lista de problemas proporcionados entre dos Maquinas
	 * @param problemas List<String> de nombres de problemas a evaluar
	 * @param maquina1 String con el nombre de la primera maquina. Posibles valores: "MAQUINA1" o "MAQUINA2"
	 * @param maquina2 String con el nombre de la segunda maquina. Posibles valores: "MAQUINA1" o "MAQUINA2"
	 * @return List<String> con el ganador de cada problema
	 */
	public String[][] evaluarProblemas(List<String> problemas, String maquina1, String maquina2) {
		Jugador m1 = ctrlDB.getJugador(maquina1);
		Jugador m2 = ctrlDB.getJugador(maquina2);
		String[][] ganadores = new String[problemas.size()][2];
		int i = 0;
		for (String problema : problemas) {
			Problema p = ctrlDB.getProblema(problema);
			Partida partida = new Partida(m1, m2, p, false, true);
			ganadores[i][0] = problema; // nombreProblema
			ganadores[i][1] = this.ganador; // ganador
			i++;
		}
		return ganadores;
	}
	
	/**
	 * Asigna ganador al problema que se esta evaluando actualmente (No llamar desde presentacion!)
	 * @param ganador String con el ganador. ("Jugador 1" o "Jugador 2")
	 */
	public void setGanador(String ganador) {
		this.ganador = ganador;
	}

}
