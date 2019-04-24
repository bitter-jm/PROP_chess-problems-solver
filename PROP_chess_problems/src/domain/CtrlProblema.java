package domain;

import stubs.CtrlDB;

public class CtrlProblema {

	private static CtrlProblema single_instance = null;
	private Problema prob;
	
	public void cargarProblema(String nombre) {
		CtrlDB DB = CtrlDB.getInstance();
		this.prob = DB.getProblema(nombre);
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
