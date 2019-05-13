package domain;

import domain.Problema;
import domain.Jugador;
import domain.M1;
import domain.Persona;

import data.CtrlData;
import domain.CtrlPersona;

/**
 * Controlador con la funcinoalidad de crear objetos de la capa de dominio a partir de la capa de datos
 */
public class CtrlDB {
	// DOMAIN DOMAIN DOMAIN DOMAIN DOMAIN DOMAIN DOMAIN DOMAIN DOMAIN DOMAIN 
	
	private static CtrlDB single_instance = null;
	private CtrlData ctrlData = CtrlData.getInstance();

	public static CtrlDB getInstance() { 
		if (single_instance == null) 
			single_instance = new CtrlDB(); 
		return single_instance; 
	}
	
	public Persona login(String nombre, String contrasena) {
		if (ctrlData.loginValido(nombre, contrasena)) {
			return new Persona(nombre, contrasena);
		}
		return null;
	}
	
	public Persona createPersona(String nombre, String contrasena) {
		if (ctrlData.createPersona(nombre, contrasena)) return new Persona(nombre, contrasena);
		else return null;
	}
		
	public Jugador getJugador(String nombre) { //DONE
		if (nombre.equals("MAQUINA1")) return new M1();
		if (nombre.equals("MAQUINA2")) return new M2();
		if (nombre.equals("INVITADO")) return new Persona("Invitado");
		return new Persona(nombre);
	}

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
		return new Problema(nombre, maxmov, FEN, color, valid, vecesJugado);
	}
	
	public void saveProblema(Problema p) {
		CtrlPersona ctrlPe = CtrlPersona.getInstance();
		String data = p.getNombre()+"\n"+
				p.getMaxMovimientos()+"\n"+
				p.getFEN_Tablero()+"\n"+
				p.getColorAGanar()+"\n"+
				p.getValidado()+"\n"+
				p.getVecesJugado()+"\n"+
				ctrlPe.getNombrePersona();
		ctrlData.saveProblema(data);
	}
	
	public void deleteProblema(Problema p) {
		ctrlData.deleteProblema(p.getNombre());
	}
	
	public boolean existsProblema(Problema p) {
		return ctrlData.existsProblema(p.getNombre());
	}
	
	public void incrementarVecesJugado(String nombreProblema) {
		ctrlData.incrementarVecesJugado(nombreProblema);
	}
	
}
