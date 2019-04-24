package domain;

import stubs.CtrlDB;
import domain.Movimiento;
import domain.Partida;
import domain.Jugador;

/**
 * Controlador de Partidas
 * @author
 */
public class CtrlDominioPartida {
	
	private static CtrlDominioPartida single_instance = null;
	private Partida part;
	/**
	 * Instancia un objeto partida con los jugadores y el problema
	 * @param j1 del tipo String representa el Jugador1
	 * @param j2 del tipo String representa el Jugador2
	 * @param problema del tipo String representa la situación inicial del juego
	 */
	public void empezarPartida(String j1, String j2, String problema) {
		CtrlDB ctrlDB = CtrlDB.getInstance();
		Jugador j1o = ctrlDB.getJugador(j1);
		Jugador j2o = ctrlDB.getJugador(j2);
		Problema p = ctrlDB.getproblema("problema1");
		this.part = new Partida(j1o, j2o, p);
	}
	/**
	 * Detecta una jugada por parte de la persona y actualiza el estado de la partida
	 * @param m del tipo Movimiento representa la jugada de la persona 
	 * @return true si el movimiento era válido y se ha realizado con éxito, false en caso contrario
	 */
	public boolean personaRealizaMovimiento(Movimiento m) {
		return this.part.jugarPersona(m);
	}
	/**
	 * Cancela una partida
	 */
	public void cancelarPartida() { //DONE
		this.part = null;
	}
	/**
	 * Comprueba si la partida ha finalizado	
	 * @return true si ha terminado correctamente, false en caso contrario
	 */
	public boolean partidaFinalizada() { //DONE
		return this.part.partidaAcabada();
	}
	/**
	 * 
	 * @return
	 */
	public static CtrlDominioPartida getInstance() { 
        if (single_instance == null) 
            single_instance = new CtrlDominioPartida(); 
        return single_instance; 
    }
	
}
