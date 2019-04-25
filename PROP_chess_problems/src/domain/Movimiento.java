package domain;

import domain.Ficha;

/** 
 * Representa un movimiento de una instancia Ficha en unas coordenadas a otras
 */
public class Movimiento {
	
	public int inicioI;
	public int inicioJ;
	public int finalI;
	public int finalJ;
	public Ficha ficha;
	
	/**
	 * Crea un objeto Movimiento
	 * @param ii Integer indicando la coordenada i de inicio
	 * @param ij Integer indicando la coordenada j de inicio
	 * @param fi Integer indicando la coordenada i de destino
	 * @param fj Integer indicando la coordenada j de destino
	 * @param ficha Ficha a ser movida
	 */
	public Movimiento(int ii, int ij, int fi, int fj, Ficha ficha) {
		this.inicioI = ii;
		this.inicioJ = ij;
		this.finalI = fi;
		this.finalJ = fj;
		this.ficha = ficha;
	}
	
	/**
	 * Compara si dos movimientos son equivalentes (Deep Comparison)
	 */
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
