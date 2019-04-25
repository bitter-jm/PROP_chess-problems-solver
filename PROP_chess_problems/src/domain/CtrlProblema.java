package domain;

import java.util.Scanner;

import stubs.CtrlDB;

public class CtrlProblema {

	private static CtrlProblema single_instance = null;
	private Problema prob;
	
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
		return true;
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
