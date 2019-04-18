package domain;

import domain.Ficha;

/** 
 * Extiende la clase Movimiento guardando la instancia Ficha de la pieza capturada
 * @author Joan Marc Pastor
 */
public class MovimientoCompleto extends Movimiento {
	
	public Ficha fichaMatada;
	
	/**
	 * Crea un objeto MovimientoCompleto
	 * @param ii Integer indicando la coordenada i de inicio
	 * @param ij Integer indicando la coordenada j de inicio
	 * @param fi Integer indicando la coordenada i de destino
	 * @param fj Integer indicando la coordenada j de destino
	 * @param ficha Ficha a ser movida
	 * @param fichaMatada Ficha capturada. Puede ser null
	 */
	public MovimientoCompleto(int ii, int ij, int fi, int fj, Ficha ficha, Ficha fichaMatada) {
		super(ii, ij, fi, fj, ficha);
		this.fichaMatada = fichaMatada;
	}
	
	/**
	 * Crea un objeto MovimientoCompleto a partir de un Movimiento
	 * @param m Movimiento a extender
	 * @param fichaMatada Ficha capturada. Puede ser null
	 */
	public MovimientoCompleto(Movimiento m, Ficha fichaMatada) {
		super(m.inicioI, m.inicioJ, m.finalI, m.finalJ, m.ficha);
		this.fichaMatada = fichaMatada;
	}

}
