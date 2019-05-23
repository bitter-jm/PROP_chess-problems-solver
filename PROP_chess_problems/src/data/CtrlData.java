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
	
	/**
	 * Crea una persona el la base de datos
	 * @param nombre String nombre persona
	 * @param contrasena String contrasena
	 * @return True si ese usuario no exisitia y se pudo crear. False en caso contrario.
	 */
	public boolean createPersona(String nombre, String contrasena) {
		if (!dbPe.existsPersona(nombre)) {
			dbPe.createPersona(nombre, contrasena);
			return true;
		}
		return false;
	}

	/**
	 * Consulta si un problema existe
	 * @param nombre String con el nombre del problema a consular
	 * @return True si el problema existe. False en caso contrario.
	 */
	public boolean existsNombrePersona(String nombre) {
		return dbPe.existsPersona(nombre);
	}
	
	/**
	 * Consulta si las credenciales de login son buenas
	 * @param nombre String con nombre de la persona
	 * @param contrasena String de la contrasena
	 * @return True si el par nombre contrasena existe. False en caso contrario.
	 */
	public boolean loginValido(String nombre, String contrasena) {
		return dbPe.checkLogIn(nombre, contrasena);
	}
	
	/**
	 * Guarda un problema en la base de datos
	 * @param p String contenieno la informacion del problema en el siguiente formato:
	 * nombreproblema + "\n" + maxmov + "\n" + FEN + "\n" + colorAGanar + "\n" + valido + "\n" + veces jugado + "\n" + autor + "\n" + dificultad
	 */
	public void saveProblema(String p) { 
		String nombre = p.split("\n")[0];
		if (!dbPr.existsProblema(nombre)) {
			dbPr.createProblema(p);
		} else {
			dbPr.updateProblema(p);
		}
	}
	
	/**
	 * Elimina el problema de la base de datos
	 * @param nombreProblema String con el nombre del problema
	 */
	public void deleteProblema(String nombreProblema) { 
		dbPr.deleteProblema(nombreProblema);
	}
	
	/** 
	 * Consulta si un problema existe en la base de datos
	 * @param nombre String con el nombre del problema
	 * @return True si el problema existe. False en caso contrario.
	 */
	public boolean existsProblema(String nombre) { 
		return dbPr.existsProblema(nombre);
	}
	
	/**
	 * Obtiene los problemas jugables de todo el sistema
	 * @return List<String> con los problemas jugables. Cada elemento de la lista representa un problema en el siguiente formato:
	 * nombreproblema + "\n" + maxmov + "\n" + FEN + "\n" + colorAGanar + "\n" + valido + "\n" + veces jugado + "\n" + autor + "\n" + dificultad
	 */
	public List<String> getProblemasJugables() { 
		return dbPr.getProblemasJugables();
	}
	
	/**
	 * Obtiene los problemas jugables de todo el sistema
	 * @param nombrePersona String con el nombre de la persona
	 * @return List<String> con los problemas creados por la persona. Cada elemento de la lista representa un problema en el siguiente formato:
	 * nombreproblema + "\n" + maxmov + "\n" + FEN + "\n" + colorAGanar + "\n" + valido + "\n" + veces jugado + "\n" + autor + "\n" + dificultad
	 */
	public List<String> getMisProblemas(String nombrePersona) {
		return dbPr.getMisProblemas(nombrePersona);
	}
	
	/**
	 * Obtiene la informacion de un problema guardado en la base de datos
	 * @param nombreProblema String con el nombre del problema a obtener
	 * @return String con la informacion del problema en el siguiente formato:
	 * nombreproblema + "\n" + maxmov + "\n" + FEN + "\n" + colorAGanar + "\n" + valido + "\n" + veces jugado + "\n" + autor + "\n" + dificultad
	 */
	public String getProblema(String nombreProblema) {
		return dbPr.getProblema(nombreProblema);
	}
	
	/**
	 * Incremetne en uno las veces jugadas de un problema guardado en la base de datos
	 * @param nombreProblema String con el nombre de problema a incrementar
	 */
	public void incrementarVecesJugado(String nombreProblema) {
		dbPr.incrementarVecesJugado(nombreProblema);
	}
	
	/**
	 * Obtiene la informacion de un ranking para un problema
	 * @param nombreProblema String con el nombre del problema a obtener el ranking
	 * @return String con la informacion del ranking en el siguiente formato:
	 * nombrePersona1 + "?" + puntuacionPersona1 + "\n" + ...
	 */
	public String getRanking(String nombreProblema) {
		return dbR.getRankingProblema(nombreProblema);
	}
	
	/** 
	 * Guarda la informamcion de un ranking en la base de datos
	 * @param dataRanking Informacion del ranking
	 * @param nombreProblema Problema que implementa
	 */
	public void saveRanking(String dataRanking, String nombreProblema) {
		if (dbR.existsRanking(nombreProblema)) dbR.updateRanking(dataRanking, nombreProblema);
		else dbR.createRanking(dataRanking, nombreProblema);
	}
	
	/**
	 * Consulta si existe un ranking para un problema especifico
	 * @param nombreProblema String con el nombre del problema
	 * @return Truen caso de que el ranking exita. False en caso contrario.
	 */
	public boolean existsRanking(String nombreProblema) {
		return dbR.existsRanking(nombreProblema);
	}	
	
}
