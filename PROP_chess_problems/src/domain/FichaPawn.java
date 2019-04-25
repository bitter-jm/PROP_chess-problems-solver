package domain;

import java.util.List;
import java.util.ArrayList;

/**
 * Representa una ficha Pawn
 * @author Joan Marc Pastor
 */
public class FichaPawn extends Ficha {
	
	/**
	 * Crea un objeto del tipo FichaPawn
	 * @param color String indicando de que color es
	 */
	public FichaPawn(String color) {
		this.color = color;
		this.ficha = "p";
	}
	
	@Override
	public List<Movimiento> posiblesMovimientos(int i, int j, Ficha[][] casillas) {
		List<Movimiento> movimientos = new ArrayList<Movimiento>();
		if (this.color == "BLANCAS") {
			if (i == 0) return new ArrayList<Movimiento>();
			if (casillas[i-1][j] == null) {
				movimientos.add(new Movimiento(i, j, i-1, j, this));
			}
			if (j>0 && casillas[i-1][j-1] != null && casillas[i-1][j-1].color.equals("NEGRAS")) {
				movimientos.add(new Movimiento(i, j, i-1, j-1, this));
			}
			if (j<7 && casillas[i-1][j+1] != null && casillas[i-1][j+1].color.equals("NEGRAS")) {
				movimientos.add(new Movimiento(i, j, i-1, j+1, this));
			}
		}
		else { //NEGRA
			if (i == 7) return new ArrayList<Movimiento>();
			if (casillas[i+1][j] == null) {
				movimientos.add(new Movimiento(i, j, i+1, j, this));
			}
			if (j>0 && casillas[i+1][j-1] != null && casillas[i+1][j-1].color.equals("BLANCAS")) {
				movimientos.add(new Movimiento(i, j, i+1, j-1, this));
			}
			if (j<7 && casillas[i+1][j+1] != null && casillas[i+1][j+1].color.equals("BLANCAS")) {
				movimientos.add(new Movimiento(i, j, i+1, j+1, this));
			} 
		}
		return movimientos;
	}

}
