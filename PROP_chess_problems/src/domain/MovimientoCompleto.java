package domain;

public class MovimientoCompleto extends Movimiento {
	
	public String fichaMatada;
	
	public MovimientoCompleto(int ii, int ij, int fi, int fj, String ficha, String fichaMatada) {
		super(ii, ij, fi, fj, ficha);
		this.fichaMatada = fichaMatada;
	}
	
	public MovimientoCompleto(Movimiento m, String fichaMatada) {
		super(m.inicioI, m.inicioJ, m.finalI, m.finalJ, m.ficha);
		this.fichaMatada = fichaMatada;
	}

}
