package data;

import domain.*;

/**
 * Stub del controlador de datos
 */
public class CtrlDB {
	
	private static CtrlDB single_instance = null;

	public static CtrlDB getInstance() { 
		if (single_instance == null) 
			single_instance = new CtrlDB(); 
		return single_instance; 
	}
	
	public Jugador getJugador(String nombre) { // CtrlPartida
		// TODO
	}
	
	public Persona getPersona(String nombre, String contrasena) { // CtrlLogin
		// TODO
	}
	
	public Persona createPersona(String nombre, String contrasena) { // CtrlLogin
		// TODO
	}

	public boolean existsPersona(String nombre) { // CtrlLogin
		// TODO
	}
	
	public void saveProblema(Problema p) { // CtrlProblema
		// TODO
	}
	
	public void deleteProblema(Problema p) { // CtrlProblema
		// TODO
	}
	
	public boolean existsProblema(String nombre) { // CtrlProblema
		// TODO
	}
	
	public Problema[] getProblemasJugables() { // CtrlPresentation
		// TODO
	}
	
	public Problema[] getMisProblemas(String nombrePersona) { // CtrlPresentation 
		// TODO
	}
	
   // TODO Rankings
	
}
