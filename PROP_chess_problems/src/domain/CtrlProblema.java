package domain;

import java.util.Scanner;

import stubs.CtrlDB;
import domain.Ficha;
import domain.Movimiento;

/**
 * Controlador de Problemas
 * @author Joan Marc Pastor
 */
public class CtrlProblema {

	private static CtrlProblema single_instance = null;
	private Problema prob;
<<<<<<< HEAD
	
	public void cargarProblema(String nombre) {
		CtrlDB DB = CtrlDB.getInstance();
		this.prob = DB.getProblema(nombre);
		while (this.prob == null) {
			System.out.println("El problema no existe. \n Introduce el nombre de problema nuevo");
			Scanner sc = new Scanner(System.in);
			 String nom= sc.nextLine();
			 this.prob = DB.getProblema(nom);
		}
	}
	
	public void crearProblema(String nombre, Integer NumeroJugadas, String Fen, boolean ganar) {
		CtrlDB DB = CtrlDB.getInstance();
		this.prob = DB.createProblema(nombre, NumeroJugadas, Fen, ganar);
		
	}
	
	public void validarProblema(String nombre) {
		CtrlDB DB = CtrlDB.getInstance();
		this.prob = DB.getProblema(nombre);
		while (this.prob == null) {
			System.out.println("El problema no existe. \n Introduce el nombre de problema nuevo");
			Scanner sc = new Scanner(System.in);
			 String nom= sc.nextLine();
			 this.prob = DB.getProblema(nom);
		}
		String fen =prob.getFEN_Tablero();
		boolean colorGanar = prob.getColorAGanar();
		this.prob.esValido(fen, colorGanar);
	
	}
	
	
	public void editarProblema(String nombre) {
		//la implementamos?
		//CtrlDB DB = CtrlDB.c
		//ESCRIBO UN CREATE PROBLEMA EN EL STRUB DE BD?
				/*Si no valido pedir nuevos DE JUGADAS QUE NO SEA ZERO DE COLOR BLANCAS O NEGRAS 
				 * SUDA DE MIRAR EL FEN*/
		
		
	}
	/**
	 * Elimina el problema de la base de datos si este no ha sido jugado anteriormente
	 * @return true si se ha podido borrar. False si no se ha borrado.
	 */
	public boolean eliminarProblema() {
		if (this.prob.getVecesJugado() > 0) return false;
		CtrlDB DB = CtrlDB.getInstance();
		DB.deleteProblema(this.prob);
=======
	private CtrlDB DB = CtrlDB.getInstance();

	/**
	 * Carga el problema con nombre <em>nombre</em> desde la capa de datos
	 * @param nombre String indicando el nombre del problema
	 * @return True si se ha encontrado un problema guardado con ese nombre. False en caso contrario
	 */
	public boolean cargarProblema(String nombre) { 
		this.prob = this.DB.getProblema(nombre);
		if (this.prob == null) return false;
>>>>>>> branch 'master' of https://github.com/joan3pastor/PROP_chess-problems.git
		return true;
	}
	
	/**
	 * Guarda en memoria no volatil el objeto Problema activo
	 */
	public void guardarProblema() { 
		if (this.prob != null) {			
			DB.saveProblema(this.prob);
		}
	}
	
	/**
	 * Elimina de memoria no volatil el objeto Problema activo
	 * @return
	 */
	public boolean eliminarProblema() { 
		if (this.prob.getVecesJugado() > 0) return false;
		this.DB.deleteProblema(this.prob);
		this.prob = null;
		return true;
	}
	
	/**
	 * Crea un nuevo objeto Problema con contenido vacio
	 */
	public void crearProblema() {
		this.prob = new Problema();	
	}
	
	/**
	 * Valida el problema activo
	 * @return True si el problema es valido. False en caso contrario
	 */
	public boolean validarProblema() { 
		if (this.prob == null) return false;
		return this.prob.validarProblema();
	}
	
	/**
	 * Anade una <em>Ficha</em> al tablero del problema
	 * @param f Ficha por anadir
	 * @param i Coordenada i de la <em>Ficha</em>
	 * @param j Coordenada j de la <em>Ficha</em>
	 */
	public void anadirFicha(Ficha f, int i, int j) { 
		if (this.prob != null) {
			this.prob.anadirFicha(f, i, j);
		}
	}
	
	/**
	 * Limpia una casilla del tablero del problema
	 * @param i Coordenada i de la <em>Ficha</em>
	 * @param j Coordenada i de la <em>Ficha</em>
	 */
	public void retirarFicha(int i, int j) { 
		if (this.prob != null) {
			this.prob.retirarFicha(i, j);
		}
	}
	
	/**
	 * Mueve una <em>Ficha</em> del tablero del problema libremente (Sin restricciones)
	 * @param mov <em>Movimiento</em> indicando el movimiento a realizar
	 */
	public void moverFicha(Movimiento mov) { 
		if (this.prob != null) {			
			this.prob.moverFicha(mov);
		}
	}
	
	/**
	 * Especifica los movimientos maximos permitidos para intentar hacer mate
	 * @param maxMovimientos Integer con los movimientos maximos
	 * @return True si es un valor valido. False en caso contrario
	 */
	public boolean setMaxMovimientos(int maxMovimientos) { 
		if (this.prob == null || maxMovimientos <= 0) return false;
		this.prob.setMaxMovimientos(maxMovimientos);
		return true;
	}
	
	/**
	 * Especifica que color empieza y realiza mate
	 * @param color False -> Blancas, True -> Negras
	 */
	public void setColorAGanar(boolean color) { 
		if (this.prob != null) {			
			this.prob.setColorAGanar(color);
		}
	}
	
	/**
	 * Especifica el nombre del problema
	 * @param nombre String indicando el nombre. Nombre ha de ser unico entre todos los problemas
	 * @return True si se ha modificado bien el nombre. False si ya existe un problema con ese nombre o no hay problema activo
	 */
	public boolean setNombre(String nombre) { 
		if (this.prob != null && DB.existsProblema(nombre)) return false;
		this.prob.setNombre(nombre);
		return true;		
	}
	
	/**
	 * Actualiza el estado del tablero del problema segun la anotacion FEN
	 * @param fen String en formato FEN
	 */
	public void setFEN(String fen) { 
		if (this.prob != null) {
			this.prob.setFEN_Tablero(fen);
		}
	}
	
	/**
	 * Imprime el estado del tablero del problema por consola (Para debugar)
	 */
	public void imprimirEstadoProblemaConsola() {
		if (this.prob != null) {
			this.prob.imprimirEstadoProblemaConsola();
		}
	}
	
	/**
	 * Obtiene la instacia del singleton CtrlProblema
	 * @return Objeto CtrlProblema
	 */
	public static CtrlProblema getInstance() { 
        if (single_instance == null) 
            single_instance = new CtrlProblema(); 
        return single_instance;
    }
	
}
