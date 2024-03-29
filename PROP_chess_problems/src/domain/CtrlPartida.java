package domain;

import domain.CtrlDB;
import domain.CtrlRanking;
import domain.Movimiento;
import domain.Partida;
import domain.Jugador;
import domain.Tablero;

/**
 * Controlador de Partidas
 */
public class CtrlPartida {
	
	private static CtrlPartida single_instance = null;
	private Partida part;
	CtrlDB ctrlDB = CtrlDB.getInstance();
	CtrlRanking ctrlR = CtrlRanking.getInstance();
	
	/**
	 * Instancia un objeto partida con los jugadores y el problema
	 * @param j1 del tipo String representa el Jugador1
	 * @param j2 del tipo String representa el Jugador2
	 * @param problema del tipo String representa la situacion inicial del juego
	 */
	public void empezarPartida(String j1, String j2, String problema) {
		Jugador j1o = ctrlDB.getJugador(j1);
		Jugador j2o = ctrlDB.getJugador(j2);
		Problema p = ctrlDB.getProblema(problema);
		this.part = new Partida(j1o, j2o, p, true, false);
	}
	
	/**
	 * Informa si un movimiento en el estado actual de la partida es valido
	 * @param pieza String con pieza a mover (Formato FEN)
	 * @param iInicio coordenada i de inicio 
	 * @param jInicio coordenada j de inicio 
	 * @param iFinal coordenada i final
	 * @param jFinal coordenada j final
	 * @return True si es un momvimiento valido. False en caso contrario.
	 */
	public boolean consultarValidezMovimiento(String pieza, int iInicio, int jInicio, int iFinal, int jFinal) {
		Tablero t = new Tablero(this.part.getCurrentFEN());
		return t.registrarMovimientoValidando(new Movimiento(iInicio, jInicio, iFinal, jFinal, Ficha.newFicha(pieza)));
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
	 * Realizar movimiento en la partida para un jugador de tipo Persona
	 * @param pieza String con pieza a mover (Formato FEN)
	 * @param iInicio coordenada i de inicio 
	 * @param jInicio coordenada j de inicio 
	 * @param iFinal coordenada i final
	 * @param jFinal coordenada j final
	 * @return True si es un momvimiento valido y se ha movido. False en caso contrario.
	 */
	public boolean personaRealizaMovimiento(String pieza, int iInicio, int jInicio, int iFinal, int jFinal) {
		return this.part.jugarPersona(new Movimiento(iInicio, jInicio, iFinal, jFinal, Ficha.newFicha(pieza)));
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
	
	/**
	 * Informa a presentacion que se ha cambiado el estado del tablero
	 * @param FEN String FEN con el estado del tablero
	 * @param turnoDe String indicando de quien es el siguiente turno
	 * @param colorDeQuienHaMovido String indicando quien ha movido por ultima vez
	 */
	public void tableroModificado(String FEN, String turnoDe, String colorDeQuienHaMovido) { //TODO
		// TODO: Informa a presentacion del cambio de estado en el tablero de partida
		String colorSiguienteTurno = "BLANCAS";
		if (colorDeQuienHaMovido.equals("BLANCAS")) colorSiguienteTurno = "NEGEAS";
		System.out.println("Informar estado tablero a presentacion... Siguiente turno de: " + turnoDe + " ("+colorSiguienteTurno+")");
	}
	
	/**
	 * Crea una entrada en el ranking del problema si la puntuaion es mayor a 0, incrementa las veces jugadas y avisa a presentacion de su finalizacion
	 * @param nombreGanador String con el nombre del ganador
	 * @param colorGanador String con el color del ganador
	 * @param puntuacion Integer con la puntuacion obtenida
	 * @param nombreProblema String con el nombre del problema
	 */
	public void partidaFinaliza(String nombreGanador, String colorGanador, int puntuacion, String nombreProblema) { // TODO
		// TODO: Informa a presentacion sobre la finalizacion y ganadores
		if (puntuacion > 0) { // mirar que sea persona contra maquina tmb
			ctrlR.addToRanking(nombreGanador, nombreProblema, puntuacion);
		}
		CtrlDB ctrlDB = CtrlDB.getInstance();
		ctrlDB.incrementarVecesJugado(nombreProblema);
		System.out.println("Informar a presentacion de partida acabada. Gana: "+nombreGanador+" ("+colorGanador+") con un score de: " + puntuacion);
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
