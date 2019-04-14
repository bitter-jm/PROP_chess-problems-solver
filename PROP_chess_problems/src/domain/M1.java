package domain;

import domain.Movimiento;
import domain.Tablero;
import stubs.Maquina;

public class M1 extends Maquina {
	
	private int depth = 4;
	
	public Movimiento realizarMovimiento(String fen) {
		
		Tablero t = new Tablero(fen);
		
		
		return new Movimiento(0,0,0,0, new Ficha());
	}	
	
}
