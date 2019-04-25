package domain;

import domain.Movimiento;
import domain.Tablero;
import domain.Maquina;
import java.util.List;
import java.util.Iterator;

/** 
 * Representa la Maquina 2 (No implementada aun)
 * @author -
 */
public class M2 extends Maquina {
	
	/**
	 * Crea un objeto M2
	 */
	public M2() {
		this.nombre = "MAQUINA2";
	}
	
	/**
	 * Calcula cual es el mejor movimiento a realizar
	 * @param fen String con el estado del tablero en formato FEN
	 * @return Devuelve el Movimiento mas optimo 
	 */
	@Override
	public Movimiento realizarMovimiento(String fen) {
		return new Movimiento(1, 1, 1, 1, Ficha.newFicha("p"));
	}
	
}
