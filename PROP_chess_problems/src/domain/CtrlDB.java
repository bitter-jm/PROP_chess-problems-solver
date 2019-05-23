package domain;

import domain.Problema;
import domain.Jugador;
import domain.Ranking;
import domain.M1;
import domain.M2;
import domain.Persona;

import data.CtrlData;
import domain.CtrlPersona;

import java.util.List;

/**
 * Controlador con la funcinoalidad de crear objetos de la capa de dominio a partir de la capa de datos
 */
public class CtrlDB {
	
	private static CtrlDB single_instance = null;
	private CtrlData ctrlData = CtrlData.getInstance();

	/**
	 * Obtiene la instancia unica de la clase
	 * @return instancia CtrlDB
	 */
	public static CtrlDB getInstance() { 
		if (single_instance == null) 
			single_instance = new CtrlDB(); 
		return single_instance; 
	}
	
	private CtrlDB() {}
	
	/**
	 * Devuelve el objeto de Persona si las credenciales son correctas
	 * @param nombre String del nombre de la persona
	 * @param contrasena String de la contrasena de la persona
	 * @return Persona si la combinacion nombre contrasena es correcta. Null en caso contrario.
	 */
	public Persona login(String nombre, String contrasena) {
		if (ctrlData.loginValido(nombre, contrasena)) {
			return new Persona(nombre, contrasena);
		}
		return null;
	}
	
	/**
	 * Registra un nuevo usuario y devuelve el objeto persona de esta si se pudo registrar con exito.
	 * @param nombre String del nombre de la persona
	 * @param contrasena String de la contrasena de la persona
	 * @return Persona si <em>persona</em> no existe en el sistema. Null en caso contrario.
	 */
	public Persona createPersona(String nombre, String contrasena) {
		if (ctrlData.createPersona(nombre, contrasena)) return new Persona(nombre, contrasena);
		else return null;
	}
		
	/**
	 * Crea objetos Jugadores a partir del nombre proporcionado
	 * @param nombre Nombre del Jugador
	 * @return Objeto Jugador concorde al nombre de usuario proporcionado
	 */
	public Jugador getJugador(String nombre) { //DONE
		if (nombre.equals("MAQUINA1")) return new M1();
		if (nombre.equals("MAQUINA2")) return new M2();
		if (nombre.equals("INVITADO")) return new Persona("Invitado");
		return new Persona(nombre);
	}

	/**
	 * Obtiene el lobjeto problema a partir del nombre del problema
	 * @param nombreProblema String conteniendo el nombre del problema
	 * @return Objeto Problelma que coincide con el nombre
	 */
	public Problema getProblema(String nombreProblema) {
		String p = ctrlData.getProblema(nombreProblema);
		String[] probData = p.split("\n");
		String nombre = probData[0];
		Integer maxmov = Integer.valueOf(probData[1]);
		String FEN = probData[2];
		boolean color = false;
		if (probData[3].equals("true")) color = true;
		boolean valid = false;
		if (probData[4].equals("true")) valid = true;
		Integer vecesJugado = Integer.valueOf(probData[5]);
		String dificultad = null;
		if (!probData[7].equals("NULL")) dificultad = probData[7];
		return new Problema(nombre, maxmov, FEN, color, valid, vecesJugado, dificultad);
	}
	
	/**
	 * Guarda un objeto Problelma a la base de Datos
	 * @param p Problema a ser guardado
	 */
	public void saveProblema(Problema p) {
		CtrlPersona ctrlPe = CtrlPersona.getInstance();
		String autor;
		try {
			autor = ctrlPe.getNombrePersona();
		} catch(Exception e) {
			autor = "NO REGISTRADO";
			System.out.println("AVISO: Guardando problema pero usuario no registrado. Guardando como NO REGISTRADO");
		}
		
		String dificultad = "NULL";
		if (p.getDificultadString() != null) dificultad = p.getDificultadString();
		
		String data = p.getNombre()+"\n"+
				p.getMaxMovimientos()+"\n"+
				p.getFEN_Tablero()+"\n"+
				p.getColorAGanar()+"\n"+
				p.getValidado()+"\n"+
				p.getVecesJugado()+"\n"+
				autor+"\n"+
				dificultad;
		ctrlData.saveProblema(data);
	}
	
	/**
	 * elimina un objeto Problema de la base de datos
	 * @param p Objeto Problema a ser eliminado
	 */
	public void deleteProblema(Problema p) {
		ctrlData.deleteProblema(p.getNombre());
	}
	
	/**
	 * Informa si un objeto Problema existe en la base de datos
	 * @param p Objeto problema a consultar
	 * @return True en caso de que exita. False en caso contrario.
	 */
	public boolean existsProblema(Problema p) {
		return ctrlData.existsProblema(p.getNombre());
	}
	
	/**
	 * Informa si un nombre de problema existe en la base de datos
	 * @param p String con el nombre de problema a consultar
	 * @return True en caso de que exita. False en caso contrario.
	 */
	public boolean existsProblema(String p) {
		return ctrlData.existsProblema(p);
	}
	
	/**
	 * Incrementa en uno las veces jugadas del problema especificado en la base de datos
	 * @param nombreProblema String con el nombre del problema
	 */
	public void incrementarVecesJugado(String nombreProblema) {
		ctrlData.incrementarVecesJugado(nombreProblema);
	}
	
	/**
	 * Obtiene el objeto Ranking del problema especificado
	 * @param nombreProblema String con el nombre del problema
	 * @return Objeto Ranking inicializado relacionado con el problema especificado
	 */
	public Ranking getRankingProblema(String nombreProblema) {
		Ranking r = new Ranking(nombreProblema);
		String dataRanking = ctrlData.getRanking(nombreProblema);
		if (dataRanking == null || dataRanking.isEmpty()) return r;
		String[] lines = dataRanking.split("\n");
		for (String line : lines) {
			String[] info = line.split("\\?");
			r.anadirAlRanking(info[0], Integer.parseInt(info[1]));
		}
		return r;
	}
	
	/**
	 * Guarda un objeto Ranking en la base de datos
	 * @param r Objeto Ranking a guardar
	 */
	public void saveRankingProblema(Ranking r) {
		List<String> lineas = r.getAllRanking();
		String out = "";
		boolean primero = true;
		for (String linea : lineas) {
			if (primero) primero = false;
			else out += "\n";
			out += linea;
		}
		ctrlData.saveRanking(out, r.getNombreProblema());
	}
	
}

