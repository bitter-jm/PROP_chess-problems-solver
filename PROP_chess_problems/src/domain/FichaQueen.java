package domain;

import java.util.List;
import java.util.ArrayList;

/**
 * Representa una ficha Queen
 */
public class FichaQueen extends Ficha {

	/**
	 * Crea un objeto del tipo FichaQueen
	 * @param color String indicando de que color es
	 */
	public FichaQueen(String color) {
		this.color = color;
		this.ficha = "q";
	}
	
	@Override
	public List<Movimiento> posiblesMovimientos(int i, int j, Ficha[][] casillas) {
		List<Movimiento> movimientos = new ArrayList<Movimiento>();
		
		boolean stop = false;
		int ii = i;
		int jj = j;
		while (ii > 0 && jj > 0 && !stop) {
			ii--; jj--;
			if (casillas[ii][jj] == null) movimientos.add(new Movimiento(i, j, ii, jj, this));
			else if (!this.color.equals(casillas[ii][jj].color)) {
				movimientos.add(new Movimiento(i, j, ii, jj, this));
				stop = true;
			}
			else stop = true;
		}
		stop = false;
		ii = i;
		jj = j;
		while (ii > 0 && jj < 7 && !stop) {
			ii--; jj++;
			if (casillas[ii][jj] == null) movimientos.add(new Movimiento(i, j, ii, jj, this));
			else if (!this.color.equals(casillas[ii][jj].color)) {
				movimientos.add(new Movimiento(i, j, ii, jj, this));
				stop = true;
			}
			else stop = true;
		}
		stop = false;
		ii = i;
		jj = j;
		while (ii < 7 && jj < 7 && !stop) {
			ii++; jj++;
			if (casillas[ii][jj] == null) movimientos.add(new Movimiento(i, j, ii, jj, this));
			else if (!this.color.equals(casillas[ii][jj].color)) {
				movimientos.add(new Movimiento(i, j, ii, jj, this));
				stop = true;
			}
			else stop = true;
		}
		stop = false;
		ii = i;
		jj = j;
		while (ii < 7 && jj > 0 && !stop) {
			ii++; jj--;
			if (casillas[ii][jj] == null) movimientos.add(new Movimiento(i, j, ii, jj, this));
			else if (!this.color.equals(casillas[ii][jj].color)) {
				movimientos.add(new Movimiento(i, j, ii, jj, this));
				stop = true;
			}
			else stop = true;
		}
		
		stop = false;
		ii = i;
		while (ii > 0 && !stop) {
			ii--;
			if (casillas[ii][j] == null) movimientos.add(new Movimiento(i, j, ii, j, this));
			else if (!this.color.equals(casillas[ii][j].color)) {
				movimientos.add(new Movimiento(i, j, ii, j, this));
				stop = true;
			}
			else stop = true;
		}
		stop = false;
		ii = i;
		while (ii < 7 && !stop) {
			ii++;
			if (casillas[ii][j] == null) movimientos.add(new Movimiento(i, j, ii, j, this));
			else if (!this.color.equals(casillas[ii][j].color)) {
				movimientos.add(new Movimiento(i, j, ii, j, this));
				stop = true;
			}
			else stop = true;
		}
		stop = false;
		jj = j;
		while (jj > 0 && !stop) {
			jj--;
			if (casillas[i][jj] == null) movimientos.add(new Movimiento(i, j, i, jj, this));
			else if (!this.color.equals(casillas[i][jj].color)) {
				movimientos.add(new Movimiento(i, j, i, jj, this));
				stop = true;
			}
			else stop = true;
		}
		stop = false;
		jj = j;
		while (jj < 7 && !stop) {
			jj++;
			if (casillas[i][jj] == null) movimientos.add(new Movimiento(i, j, i, jj, this));
			else if (!this.color.equals(casillas[i][jj].color)) {
				movimientos.add(new Movimiento(i, j, i, jj, this));
				stop = true;
			}
			else stop = true;
		}
		
		return movimientos;
	}
	
}
