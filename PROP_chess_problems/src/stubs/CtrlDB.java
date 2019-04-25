package stubs;
import domain.Problema;
import domain.Jugador;
import domain.M1;
import domain.Persona;
import domain.Problema;

/**
 * Stub del controlador de datos
 * @author Joan Marc Pastor
 *
 */
public class CtrlDB {
	
	private static CtrlDB single_instance = null;

	public Jugador getJugador(String nombre) {
		if (nombre.equals("MAQUINA1")) return new M1();
		return new Persona(nombre, "1234");
	}
	
	public Problema getProblema(String nombre) {
		return new Problema("Problema_1", 2, "1N1b4/6nr/R5n1/2Ppk2r/K2p2qR/8/2N1PQ2/B6B", false, true);
		//return new Problema("Problema_1", 2, "8/8/8/8/3p4/5K2/3kNN2/2R5 w - - 0 1", false, true);
	}
	
	public void saveProblema(Problema p) {
		// Save or update p
		System.out.println("Problem has been saved successfully");
	}
	
	public void deleteProblema(Problema p) {
		//Elimina p
		System.out.println("Problem has been deleted successfully");
	}
	public Problema createProblema(String nombre, int numjugadas, String fen, boolean ganar) {
		return new Problema (nombre, numjugadas, fen, ganar);
	}
	public boolean existsProblema(String nombre) {
		return false;
	}
	
	public static CtrlDB getInstance() { 
        if (single_instance == null) 
            single_instance = new CtrlDB(); 
        return single_instance; 
    }
	
}
