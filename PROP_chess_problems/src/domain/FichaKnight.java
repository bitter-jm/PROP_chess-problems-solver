package domain;

import java.util.List;
import java.util.ArrayList;

/**
 * Representa una ficha Knight
 */
public class FichaKnight extends Ficha {

	/**
	 * Crea un objeto del tipo FichaKnight
	 * @param color String indicando de que color es
	 */
	public FichaKnight(String color) {
		this.color = color;
		this.ficha = "n";
	}
	
	@Override
	public List<Movimiento> posiblesMovimientos(int i, int j, Ficha[][] casillas) {
		List<Movimiento> movimientos = new ArrayList<Movimiento>();
		
		if (i-2 >= 0 && j+1 <= 7 && (casillas[i-2][j+1]==null || !this.color.equals(casillas[i-2][j+1].color))) 
			movimientos.add(new Movimiento(i, j, i-2, j+1, this));
		if (i-1 >= 0 && j+2 <= 7 && (casillas[i-1][j+2]==null || !this.color.equals(casillas[i-1][j+2].color))) 
			movimientos.add(new Movimiento(i, j, i-1, j+2, this));
		if (i+1 <= 7 && j+2 <= 7 && (casillas[i+1][j+2]==null || !this.color.equals(casillas[i+1][j+2].color))) 
			movimientos.add(new Movimiento(i, j, i+1, j+2, this));
		if (i+2 <= 7 && j+1 <= 7 && (casillas[i+2][j+1]==null || !this.color.equals(casillas[i+2][j+1].color))) 
			movimientos.add(new Movimiento(i, j, i+2, j+1, this));
		if (i+2 <= 7 && j-1 >= 0 && (casillas[i+2][j-1]==null || !this.color.equals(casillas[i+2][j-1].color))) 
			movimientos.add(new Movimiento(i, j, i+2, j-1, this));
		if (i+1 <= 7 && j-2 >= 0 && (casillas[i+1][j-2]==null || !this.color.equals(casillas[i+1][j-2].color))) 
			movimientos.add(new Movimiento(i, j, i+1, j-2, this));
		if (i-1 >= 0 && j-2 >= 0 && (casillas[i-1][j-2]==null || !this.color.equals(casillas[i-1][j-2].color))) 
			movimientos.add(new Movimiento(i, j, i-1, j-2, this));
		if (i-2 >= 0 && j-1 >= 0 && (casillas[i-2][j-1]==null || !this.color.equals(casillas[i-2][j-1].color))) 
			movimientos.add(new Movimiento(i, j, i-2, j-1, this));
	
		return movimientos;
	}
	
}
