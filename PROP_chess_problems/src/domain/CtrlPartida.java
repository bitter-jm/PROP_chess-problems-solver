package domain;

import domain.CtrlDB;
import domain.Movimiento;
import domain.Partida;
import domain.Jugador;

/**
 * Controlador de Partidas
 */
public class CtrlPartida {
	
	private static CtrlPartida single_instance = null;
	private Partida part;
	
	/**
	 * Instancia un objeto partida con los jugadores y el problema
	 * @param j1 del tipo String representa el Jugador1
	 * @param j2 del tipo String representa el Jugador2
	 * @param problema del tipo String representa la situacion inicial del juego
	 */
	public void empezarPartida(String j1, String j2, String problema) {
		CtrlDB ctrlDB = CtrlDB.getInstance();
		Jugador j1o = ctrlDB.getJugador(j1);
		Jugador j2o = ctrlDB.getJugador(j2);
		Problema p = ctrlDB.getProblema(problema);
		this.part = new Partida(j1o, j2o, p);
	}
	
	/**
	 * Detecta una jugada por parte de la persona y actualiza el estado de la partida
	 * @param m del tipo Movimiento representa la jugada de la persona 
	 * @return true si el movimiento era valido y se ha realizado con exito, false en caso contrario
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
		if (this.part == null) return true;
		return this.part.partidaAcabada();
	}
	
	public void tableroModificado(String FEN, String turnoDe, String colorDeQuienHaMovido) { //TODO
		// Informa a presentación del cambio de estado en el tablero de partida
		String colorSiguienteTurno = "BLANCAS";
		if (colorDeQuienHaMovido.equals("BLANCAS")) colorSiguienteTurno = "NEGEAS";
		System.out.println("Informar estado tablero a presentacion... Siguiente turno de: " + turnoDe + " ("+colorSiguienteTurno+")");
	}
	
	public void partidaFinaliza(String nombreGanador, String colorGanador, int puntuacion, String nombreProblema) { // TODO
		// Informa a presentación sobre la finalización y ganadores
		// Incrementa vecesJugado del problema
		// Crea linea nueva en Ranking del problema
		System.out.println("Informar a presentacion de partida acabada. Gana: "+nombreGanador+" ("+colorGanador+")");
	}
	
	/**
	 * Obtiene la instacia del singleton CtrlPartida
	 * @return Objeto CtrlPartida
	 */
	public static CtrlPartida getInstance() { 
        if (single_instance == null) 
            single_instance = new CtrlPartida(); 
        return single_instance; 
    }

	
}
