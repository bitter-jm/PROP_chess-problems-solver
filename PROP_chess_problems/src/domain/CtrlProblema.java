package domain;

import stubs.CtrlDB;
import domain.Ficha;
import domain.Movimiento;

/**
 * Controlador de Problemas
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
	
	public void guardarProblema() { //DONE
		if (this.prob != null) {	
			DB.saveProblema(this.prob);
		}
	}
	
	public boolean eliminarProblema() { //DONE
		if (this.prob.getVecesJugado() > 0) return false;
		this.DB.deleteProblema(this.prob);
		this.prob = null;
		return true;
	}
	
	public void crearProblema() { //DONE
		this.prob = new Problema();	
	}
	
	public boolean hayProblemaCargado() {
		if (this.prob == null) return false;
		else return true;
	}
	
	public boolean estaValidado() {
		if (this.prob != null) {
			return this.prob.getValidado();
		} 
		return false;
	}
	
	public boolean validarProblema() { //DONE
		if (this.prob == null) return false;
		if (this.prob.getValidado()) return false;
		boolean valid = this.prob.validarProblema();
		if (valid) this.guardarProblema();
		return valid;
	}
	
	public void anadirFicha(Ficha f, int i, int j) { //DONE
		if (this.prob != null) {
			if (this.prob.getValidado()) return;
			this.prob.anadirFicha(f, i, j);
		}
	}
	
	public void retirarFicha(int i, int j) { //DONE
		if (this.prob != null) {
			if (this.prob.getValidado()) return;
			this.prob.retirarFicha(i, j);
		}
	}
	
	public void moverFicha(Movimiento mov) { //DONE
		if (this.prob != null) {	
			if (this.prob.getValidado()) return;
			this.prob.moverFicha(mov);
		}
	}
	
	public boolean setMaxMovimientos(int maxMovimientos) { //DONE
		if (this.prob == null || maxMovimientos <= 0) return false;
		if (this.prob.getValidado()) return false;
		this.prob.setMaxMovimientos(maxMovimientos);
		return true;
	}
	
	public boolean setColorAGanar(boolean color) { //DONE
		if (this.prob == null) return false;
		if (this.prob.getValidado()) return false;
		this.prob.setColorAGanar(color);;
		return true;
	}
	
	public boolean setNombre(String nombre) { //DONE
		if (this.prob != null && DB.existsProblema(nombre)) return false;
		if (this.prob.getValidado()) return false;
		this.prob.setNombre(nombre);
		return true;		
	}
	
	public void setFEN(String fen) { //DONE
		if (this.prob != null) {
			if (this.prob.getValidado()) return;
			this.prob.setFEN_Tablero(fen);
		}
	}
	
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

