package stubs;

import domain.Jugador;
import domain.M1;
import domain.Persona;
import domain.Problema;

public class CtrlDB {
	
	private static CtrlDB single_instance = null;

	public Jugador getJugador(String nombre) {
		if (nombre.equals("MAQUINA1")) return new M1();
		return new Persona(nombre, "abcd");
	}
	
	public Problema getproblema(String nombre) {
		return new Problema("Problema_1", 2, "8/8/8/8/3p4/5K2/3kNN2/2R5 w - - 0 1", false);
	}
	
	public static CtrlDB getInstance() { 
        if (single_instance == null) 
            single_instance = new CtrlDB(); 
        return single_instance; 
    }
	
}
