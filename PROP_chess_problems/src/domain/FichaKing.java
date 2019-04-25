package domain;

import java.util.List;
import java.util.ArrayList;

/**
 * Representa una ficha King
 */
public class FichaKing extends Ficha {

	/**
	 * Crea un objeto del tipo FichaKing
	 * @param color String indicando de que color es
	 */
	public FichaKing(String color) {
		this.color = color;
		this.ficha = "k";
	}
	
	@Override
	public List<Movimiento> posiblesMovimientos(int i, int j, Ficha[][] casillas) {
		List<Movimiento> movimientos = new ArrayList<Movimiento>();
		
		if (i-1 >= 0 && j-1 >= 0 && (casillas[i-1][j-1] == null || !this.color.equals(casillas[i-1][j-1].color))) {
			movimientos.add(new Movimiento(i, j, i-1, j-1, this));
		}
		if (i-1 >= 0 && (casillas[i-1][j] == null || !this.color.equals(casillas[i-1][j].color))) {
			movimientos.add(new Movimiento(i, j, i-1, j, this));
		}
		if (i-1 >= 0 && j+1 <= 7 && (casillas[i-1][j+1] == null || !this.color.equals(casillas[i-1][j+1].color))) {
			movimientos.add(new Movimiento(i, j, i-1, j+1, this));
		}
		if (j+1 <= 7 && (casillas[i][j+1] == null || !this.color.equals(casillas[i][j+1].color))) {
			movimientos.add(new Movimiento(i, j, i, j+1, this));
		}
		if (i+1 <= 7 && j+1 <= 7 && (casillas[i+1][j+1] == null || !this.color.equals(casillas[i+1][j+1].color))) {
			movimientos.add(new Movimiento(i, j, i+1, j+1, this));
		}
		if (i+1 <= 7 && (casillas[i+1][j] == null || !this.color.equals(casillas[i+1][j].color))) {
			movimientos.add(new Movimiento(i, j, i+1, j, this));
		}
		if (i+1 <= 7 && j-1 >= 0 && (casillas[i+1][j-1] == null || !this.color.equals(casillas[i+1][j-1].color))) {
			movimientos.add(new Movimiento(i, j, i+1, j-1, this));
		}
		if (j-1 >= 0 && (casillas[i][j-1] == null || !this.color.equals(casillas[i][j-1].color))) {
			movimientos.add(new Movimiento(i, j, i, j-1, this));
		}
		
		return movimientos;
	}

}
