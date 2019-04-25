package domain;

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
	private CtrlDB DB = CtrlDB.getInstance();
	
	public boolean cargarProblema(String nombre) { 
		this.prob = this.DB.getProblema(nombre);
		if (this.prob == null) return false;
		return true;
	}
	
	public void guardarProblema() { 
		if (this.prob != null) {			
			DB.saveProblema(this.prob);
		}
	}
	
	public boolean eliminarProblema() { 
		if (this.prob.getVecesJugado() > 0) return false;
		this.DB.deleteProblema(this.prob);
		this.prob = null;
		return true;
	}
	
	public void crearProblema() {
		this.prob = new Problema();	
	}
	
	public boolean validarProblema() { 
		if (this.prob == null) return false;
		return this.prob.validarProblema();
	}
	
	public void anadirFicha(Ficha f, int i, int j) { 
		if (this.prob != null) {
			this.prob.anadirFicha(f, i, j);
		}
	}
	
	public void retirarFicha(int i, int j) { 
		if (this.prob != null) {
			this.prob.retirarFicha(i, j);
		}
	}
	
	public void moverFicha(Movimiento mov) { 
		if (this.prob != null) {			
			this.prob.moverFicha(mov);
		}
	}
	
	public boolean setMaxMovimientos(int maxMovimientos) { 
		if (this.prob == null || maxMovimientos <= 0) return false;
		this.prob.setMaxMovimientos(maxMovimientos);
		return true;
	}
	
	public boolean setColorAGanar(boolean color) { 
		if (this.prob == null) return false;
		this.prob.setColorAGanar(color);;
		return true;
	}
	
	public boolean setNombre(String nombre) { 
		if (this.prob != null && DB.existsProblema(nombre)) return false;
		this.prob.setNombre(nombre);
		return true;		
	}
	
	public void setFEN(String fen) { 
		if (this.prob != null) {
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
