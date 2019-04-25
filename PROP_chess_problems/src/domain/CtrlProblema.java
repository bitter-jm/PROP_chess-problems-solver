package domain;

import stubs.CtrlDB;

/**
 * Controlador de Problemas
 * @author Joan Marc Pastor
 */
public class CtrlProblema {

	private static CtrlProblema single_instance = null;
	private Problema prob;
	private CtrlDB DB = CtrlDB.getInstance();
	
	public boolean cargarProblema(String nombre) { //DONE
		this.prob = this.DB.getProblema(nombre);
		if (this.prob == null) return false;
		return true;
	}
	
	public void crearProblema(String nombre, Integer NumeroJugadas, String Fen) {
		//CtrlDB DB = CtrlDB.c
		//ESCRIBO UN CREATE PROBLEMA EN EL STRUB DE BD?
				/*Si no valido pedir nuevos DE JUGADAS QUE NO SEA ZERO DE COLOR BLANCAS O NEGRAS 
				 * SUDA DE MIRAR EL FEN*/
		
		
	}
	public void validarProblema() {
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
		this.DB.deleteProblema(this.prob);
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
