package domain;

import domain.Ficha;

/** 
 * Representa un movimiento de una instancia Ficha en unas coordenadas a otras
 * @author Joan Marc Pastor
 */
public class Movimiento {
	
	public int inicioI;
	public int inicioJ;
	public int finalI;
	public int finalJ;
	public Ficha ficha;
	
	public Movimiento(int ii, int ij, int fi, int fj, Ficha ficha) {
		this.inicioI = ii;
		this.inicioJ = ij;
		this.finalI = fi;
		this.finalJ = fj;
		this.ficha = ficha;
	}
	
	@Override
    public boolean equals(Object obj) { 
		Movimiento m = (Movimiento) obj;
		if (!this.ficha.equals(m.ficha)) return false;
		if (this.inicioI != m.inicioI) return false;
		if (this.inicioJ != m.inicioJ) return false;
		if (this.finalI != m.finalI) return false;
		if (this.finalJ != m.finalJ) return false;
		return true;
    }
	
}
