package domain;

import domain.Ficha;

public class MovimientoCompleto extends Movimiento {
	
	public Ficha fichaMatada;
	
	public MovimientoCompleto(int ii, int ij, int fi, int fj, Ficha ficha, Ficha fichaMatada) {
		super(ii, ij, fi, fj, ficha);
		this.fichaMatada = fichaMatada;
	}
	
	public MovimientoCompleto(Movimiento m, Ficha fichaMatada) {
		super(m.inicioI, m.inicioJ, m.finalI, m.finalJ, m.ficha);
		this.fichaMatada = fichaMatada;
	}

}
