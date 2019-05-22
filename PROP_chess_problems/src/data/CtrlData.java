package data;

import java.util.List;
import java.util.ArrayList;

/**
 * Controlador para consultar datos permamentes del programa
 */
public class CtrlData {
	// DATA DATA DATA DATA DATA DATA DATA DATA DATA DATA DATA DATA DATA DATA DATA DATA 
	
	private DBPersona dbPe = DBPersona.getInstance();
	private DBProblema dbPr = DBProblema.getInstance();
	private DBRanking dbR = DBRanking.getInstance();
	private static CtrlData single_instance = null;

	public static CtrlData getInstance() { 
		if (single_instance == null) 
			single_instance = new CtrlData(); 
		return single_instance; 
	}
	
	private CtrlData() {}
	
	public boolean createPersona(String nombre, String contrasena) {
		if (!dbPe.existsPersona(nombre)) {
			dbPe.createPersona(nombre, contrasena);
			return true;
		}
		return false;
	}

	public boolean existsNombrePersona(String nombre) {
		return dbPe.existsPersona(nombre);
	}
	
	public boolean loginValido(String nombre, String contrasena) {
		return dbPe.checkLogIn(nombre, contrasena);
	}
	
	public void saveProblema(String p) { 
		String nombre = p.split("\n")[0];
		if (!dbPr.existsProblema(nombre)) {
			dbPr.createProblema(p);
		} else {
			dbPr.updateProblema(p);
		}
	}
	
	public void deleteProblema(String nombreProblema) { 
		dbPr.deleteProblema(nombreProblema);
	}
	
	public boolean existsProblema(String nombre) { 
		return dbPr.existsProblema(nombre);
	}
	
	public List<String> getProblemasJugables() { 
		return dbPr.getProblemasJugables();
	}
	
	public List<String> getMisProblemas(String nombrePersona) {
		return dbPr.getMisProblemas(nombrePersona);
	}
	
	public String getProblema(String nombreProblema) {
		return dbPr.getProblema(nombreProblema);
	}
	
	public void incrementarVecesJugado(String nombreProblema) {
		dbPr.incrementarVecesJugado(nombreProblema);
	}
	
	public String getRanking(String nombreProblema) {
		return dbR.getRankingProblema(nombreProblema);
	}
	
	public void saveRanking(String dataRanking, String nombreProblema) {
		if (dbR.existsRanking(nombreProblema)) dbR.updateRanking(dataRanking, nombreProblema);
		else dbR.createRanking(dataRanking, nombreProblema);
	}
	
	public boolean existsRanking(String nombreProblema) {
		return dbR.existsRanking(nombreProblema);
	}	
	
}
