package data;

import java.util.List;
import java.util.ArrayList;
import domain.CtrlPersona;

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
	 * @return String[][] donde i es cada problema y j un atributo del problema i. Los atributos son los siguientes:
	 * j = 0: nombre, 1: numeroJugadas, 2: color, 3: dificultad, 4: vecesJugado
	 */
	public String[][] getProblemasJugables() { // TODO
		List<String> problemas = dbPr.getProblemasJugables();
		String[][] mat = new String[problemas.size()][5];
		int i = 0;
		for (String problema : problemas) {
			String[] data = problema.split("\n");
			String color = "BLANCAS";
			if (data[3].equals("true")) color = "NEGRAS";
			mat[i][0] = data[0]; //nombre
			mat[i][1] = data[1]; //numeroJugadas
			mat[i][2] = color; //color
			mat[i][3] = data[7]; //dificultad
			mat[i][4] = data[5]; //vecesJugado
			i++;
		}
		return mat;
	}
	
	/**
	 * Obtiene los problemas jugables de todo el sistema en orden de dificultad
	 * @return String[][] donde <em>i</em> es cada problema ordenado por dificultad y <em>j</em> un atributo del problema i. Los atributos son los siguientes:
	 * j = 0: nombre, 1: numeroJugadas, 2: color, 3: dificultad, 4: vecesJugado
	 */
	public String[][] getProblemasJugablesOrdenadosPorDificultad() { // TODO
		List<String> problemas = dbPr.getProblemasJugables();
		String[][] mat = new String[problemas.size()][5];
		int i = 0;
		for (String problema : problemas) {
			String[] data = problema.split("\n");
			if ("EASY".equals(data[7])) {				
				String color = "BLANCAS";
				if (data[3].equals("true")) color = "NEGRAS";
				mat[i][0] = data[0]; //nombre
				mat[i][1] = data[1]; //numeroJugadas
				mat[i][2] = color; //color
				mat[i][3] = data[7]; //dificultad
				mat[i][4] = data[5]; //vecesJugado
				i++;
			}
		}
		for (String problema : problemas) {
			String[] data = problema.split("\n");
			if ("MEDIUM".equals(data[7])) {				
				String color = "BLANCAS";
				if (data[3].equals("true")) color = "NEGRAS";
				mat[i][0] = data[0]; //nombre
				mat[i][1] = data[1]; //numeroJugadas
				mat[i][2] = color; //color
				mat[i][3] = data[7]; //dificultad
				mat[i][4] = data[5]; //vecesJugado
				i++;
			}
		}
		for (String problema : problemas) {
			String[] data = problema.split("\n");
			if ("HARD".equals(data[7])) {				
				String color = "BLANCAS";
				if (data[3].equals("true")) color = "NEGRAS";
				mat[i][0] = data[0]; //nombre
				mat[i][1] = data[1]; //numeroJugadas
				mat[i][2] = color; //color
				mat[i][3] = data[7]; //dificultad
				mat[i][4] = data[5]; //vecesJugado
				i++;
			}
		}
		return mat;
	}
	
	/**
	 * Obtiene los problemas jugables de todo el sistema ordenado por movimientos máximos
	 * @return String[][] donde i es cada problema y j un atributo del problema i. Los atributos son los siguientes:
	 * j = 0: nombre, 1: numeroJugadas, 2: color, 3: dificultad, 4: vecesJugado
	 */
	public String[][] getProblemasJugablesOrdenadosPorMovimientos() { // TODO
		List<String> problemas = dbPr.getProblemasJugables();
		
		int maxNumJugadas = 1;
		for (String problema : problemas) {
			String[] data = problema.split("\n");
			if (Integer.valueOf(data[1]) > maxNumJugadas) {
				maxNumJugadas = Integer.valueOf(data[1]);
			}
		}
		
		String[][] mat = new String[problemas.size()][5];
		int i = 0;
		
		for (int j = 0; j < maxNumJugadas; ++j) {
			for (String problema : problemas) {
				String[] data = problema.split("\n");
				if (Integer.valueOf(data[1]).equals(j)) {
					String color = "BLANCAS";
					if (data[3].equals("true")) color = "NEGRAS";
					mat[i][0] = data[0]; //nombre
					mat[i][1] = data[1]; //numeroJugadas
					mat[i][2] = color; //color
					mat[i][3] = data[7]; //dificultad
					mat[i][4] = data[5]; //vecesJugado
					i++;
				}
			}
		}
		return mat;
	}
	
	/**
	 * Obtiene los problemas de la persona registrada en el sistema
	 * @return String[][] donde i es cada problema y j un atributo del problema i. Los atributos son los siguientes:
	 * j = 0: nombre, 1: numeroJugadas, 2: color, 3: valid, 4: vecesJugado
	 */
	public String[][] getMisProblemas() { // TODO
		CtrlPersona ctrlP = CtrlPersona.getInstance();
		String nombrePersona = "";
		if (ctrlP.isLoggedIn()) nombrePersona = ctrlP.getNombrePersona();
		List<String> problemas = dbPr.getMisProblemas(nombrePersona);
		String[][] mat = new String[problemas.size()][5];
		int i = 0;
		for (String problema : problemas) {
			String[] data = problema.split("\n");
			String color = "BLANCAS";
			if (data[3].equals("true")) color = "NEGRAS";
			String valid = "NO VALIDO";
			if (data[4].equals("true")) valid = "VALIDO";
			mat[i][0] = data[0]; //nombre
			mat[i][1] = data[1]; //numeroJugadas
			mat[i][2] = color; //color
			mat[i][3] = valid; //valido
			mat[i][4] = data[5]; //vecesJugado
			i++;
		}
		return mat;
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
	 * Obtiene la informacion de un ranking para un problema
	 * @param nombreProblema String con el nombre del problema a obtener el ranking
	 * @return String[][] donde i es cada linea de ranking y j un atributo de la linea i. Los atributos son los siguientes:
	 * j = 0: nombre, 1: puntuacion
	 */
	public String[][] getMatrixRanking(String nombreProblema) {
		try {
		String ranking = dbR.getRankingProblema(nombreProblema);
		String[] lines = ranking.split("\n");
		String mat[][] = new String[lines.length][2];
		int i = 0;
		for (String line : lines) {
			String[] elems = line.split("\\?");
			mat[i][0] = elems[0]; // nombre
			mat[i][1] = elems[1]; // puntuacion
			i++;
		}
		return mat;
		} catch (Exception e) { // No hay rankings aun para el problema buscado
			System.out.println("INFO: No hay rankings guardados para el problema buscado");
			return new String[0][0];
		}
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
