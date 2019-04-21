package domain;

import stubs.CtrlDB;
import domain.Movimiento;
import domain.Partida;
import domain.Jugador;

/**
 * Controlador de Partidas
 * @author
 */
public class CtrlPartida {
	
	private static CtrlPartida single_instance = null;
	private Partida part;

	public void empezarPartida(String j1, String j2, String problema) {
		CtrlDB ctrlDB = CtrlDB.getInstance();
		Jugador j1o = ctrlDB.getJugador(j1);
		Jugador j2o = ctrlDB.getJugador(j2);
		Problema p = ctrlDB.getproblema("problema1");
		this.part = new Partida(j1o, j2o, p);
	}
	
	public boolean personaRealizaMovimiento(Movimiento m) {
		return this.part.jugarPersona(m);
	}
		
	public boolean partidaFinalizada() { //DONE
		return this.part.partidaAcabada();
	}
	
	public void cancelarPartida() { //DONE
		this.part = null;
	}
	
	public static CtrlPartida getInstance() { 
        if (single_instance == null) 
            single_instance = new CtrlPartida(); 
        return single_instance; 
    }
	
}
