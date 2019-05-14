package data;

import java.util.List;
import java.util.ArrayList;

/**
 * Controlador para consultar datos permamentes del programa
 */
public class CtrlData {
	// DATA DATA DATA DATA DATA DATA DATA DATA DATA DATA DATA DATA DATA DATA DATA DATA 
	
	private CtrlDBPersona dbPe = CtrlDBPersona.getInstance();
	private CtrlDBProblema dbPr = CtrlDBProblema.getInstance();
	private CtrlDBRanking dbR = CtrlDBRanking.getInstance();
	private static CtrlData single_instance = null;

	public static CtrlData getInstance() { 
		if (single_instance == null) 
			single_instance = new CtrlData(); 
		return single_instance; 
	}
	
	public boolean createPersona(String nombre, String contrasena) { // CtrlLogin
		if (!dbPe.existsPersona(nombre)) {
			dbPe.createPersona(nombre, contrasena);
			return true;
		}
		return false;
	}

	public boolean existsNombrePersona(String nombre) { // CtrlLogin
		return dbPe.existsPersona(nombre);
	}
	
	public boolean loginValido(String nombre, String contrasena) {
		return dbPe.checkLogIn(nombre, contrasena);
	}
	
	public void saveProblema(String p) { // CtrlProblema
		String nombre = p.split("\n")[0];
		if (!dbPr.existsProblema(nombre)) {
			dbPr.createProblema(p);
		} else {
			dbPr.updateProblema(p);
		}
	}
	
	public void deleteProblema(String nombreProblema) { // CtrlProblema
		dbPr.deleteProblema(nombreProblema);
	}
	
	public boolean existsProblema(String nombre) { // CtrlProblema
		return dbPr.existsProblema(nombre);
	}
	
	public List<String> getProblemasJugables() { // CtrlPresentation
		return dbPr.getProblemasJugables();
	}
	
	public List<String> getMisProblemas(String nombrePersona) { // CtrlPresentation 
		return dbPr.getMisProblemas(nombrePersona);
	}
	
	public String getProblema(String nombreProblema) {
		return dbPr.getProblema(nombreProblema);
	}
	
	public void incrementarVecesJugado(String nombreProblema) {
		dbPr.incrementarVecesJugado(nombreProblema);
	}
	
   // TODO Rankings
	
}