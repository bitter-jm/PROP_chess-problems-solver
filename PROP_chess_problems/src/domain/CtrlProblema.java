package domain;

import stubs.CtrlDB;

public class CtrlProblema {

	private static CtrlProblema single_instance = null;
	private Problema prob;
	
	public void cargarProblema(String nombre) {
		CtrlDB DB = CtrlDB.getInstance();
		this.prob = DB.getProblema(nombre);
		if (this.prob == null) {
			System.out.println("Introduce el nombre de problema nuevo");
			/*Scanner sc = 
			 * nom= scan.getline();
			 *cargarProblema(nom);
			 */
		}
		
	}
	
	public void crearProblema(String nombre, Integer NumeroJugadas, String Fen) {
		//CtrlDB DB = CtrlDB.c
		//ESCRIBO UN CREATE PROBLEMA EN EL STRUB DE BD?
				/*Si no valido pedir nuevos DE JUGADAS QUE NO SEA ZERO DE COLOR BLANCAS O NEGRAS 
				 * SUDA DE MIRAR EL FEN*/
		
		
	}
	public void validarProblema(String nombre) {
		CtrlDB DB = CtrlDB.getInstance();
		this.prob = DB.getProblema(nombre);
		if (this.prob == null) {
			System.out.println("Introduce el nombre de problema nuevo");
			/*Scanner sc = 
			 *nom= scan.getline();
			 *cargarProblema(nom);
			 */
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
